package com.simple.share.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.contant.ShareContent;
import com.simple.share.model.MemberDo;
import com.simple.share.mapper.MemberMapper;
import com.simple.share.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.vo.wexin.UserInfoVo;
import com.simple.share.vo.wexin.WeixinLoginVo;
import com.simple.share.weixin.LoginService;
import com.simple.share.weixin.dto.WeixinUserInfo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.support.constant.CommonConstant;
import com.spring.simple.development.support.utils.AESUtils;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import com.spring.simple.development.support.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-08
 */
@Api(tags = "登录相关")
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberDo> implements MemberService {

    @Autowired
    private LoginService loginService;

    @Override
    @ApiOperation(value = "微信小程序登录")
    @ApiImplicitParam(name = "weixinLoginVo", description = "登录对象", resultDataType = WeixinLoginVo.class)
    @IsApiService(isLogin = false)
    @ValidHandler(key = "weixinLoginVo", value = WeixinLoginVo.class)
    public UserInfoVo login(WeixinLoginVo weixinLoginVo) {
        UserInfoVo userInfoVo = new UserInfoVo();
        WeixinUserInfo weixinUserInfo = loginService.getWeixinUserInfo(weixinLoginVo.getCode());

        // 用户是否存在
        QueryWrapper<MemberDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberDo::getOpenId, weixinUserInfo.getOpenId());
        List<MemberDo> listMemberDo = list(queryWrapper);
        if (CollUtil.isEmpty(listMemberDo)) {
            MemberDo memberDo = new MemberDo();
            memberDo.setNickName(weixinLoginVo.getNickName());
            memberDo.setAvatarUrl(weixinLoginVo.getAvatarUrl());
            memberDo.setAddress(weixinLoginVo.getAddress());
            memberDo.setGender(weixinLoginVo.getGender());
            memberDo.setChannel(CommonConstant.CODE1);
            memberDo.setOpenId(weixinUserInfo.getOpenId());
            save(memberDo);

            userInfoVo.setAvatarUrl(weixinLoginVo.getAvatarUrl());
            userInfoVo.setNickName(weixinLoginVo.getNickName());
        } else {
            MemberDo memberDo = listMemberDo.get(0);
            // 更新用户
            if (!memberDo.getAddress().equals(weixinLoginVo.getAddress()) || !memberDo.getAvatarUrl().equals(weixinLoginVo.getAddress()) ||
            !memberDo.getNickName().equals(weixinLoginVo.getNickName())){
                memberDo.setAddress(weixinLoginVo.getAddress());
                memberDo.setAvatarUrl(weixinLoginVo.getAvatarUrl());
                memberDo.setNickName(weixinLoginVo.getNickName());
                updateById(memberDo);
            }
            userInfoVo.setAvatarUrl(weixinLoginVo.getAvatarUrl());
            userInfoVo.setNickName(weixinLoginVo.getNickName());

        }

        // 生成token
        String token = AESUtils.encrypt(AESUtils.coupon_key, weixinUserInfo.getOpenId() + "-" + RandomUtils.getRandomStr(8));
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            jedis.setex(ShareContent.MEMBER_TOKEN_KEY + weixinUserInfo.getOpenId(), 60 * 60 * 24 * 30, token);
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
        userInfoVo.setToken(token);
        return userInfoVo;
    }
}
