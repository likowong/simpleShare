package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.contant.ShareContent;
import com.simple.share.interceptor.MemberInfo;
import com.simple.share.interceptor.MemberThreadLocal;
import com.simple.share.model.MemberDo;
import com.simple.share.model.MemberTaobaoDo;
import com.simple.share.mapper.MemberTaobaoMapper;
import com.simple.share.service.MemberTaobaoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.taobao.dto.InviterInfo;
import com.simple.share.taobao.dto.InviterMemberInfo;
import com.simple.share.vo.wexin.WeixinLoginVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.exception.ResponseCode;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 淘宝用户表 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Api(tags = "淘宝用户备案相关")
@Service
public class MemberTaobaoServiceImpl extends ServiceImpl<MemberTaobaoMapper, MemberTaobaoDo> implements MemberTaobaoService {

    @Autowired
    private TaobaoService taobaoService;

    @Override
    @ApiOperation(value = "生成邀请并返回备案淘口令")
    @IsApiService
    public String createInviterCodeResultTbCode() {
        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();

        InviterInfo inviterTbCode = taobaoService.createInviterTbCode(memberInfo.getWxOpenId());
        // 用户是否存在
        QueryWrapper<MemberTaobaoDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberTaobaoDo::getOpenId, memberInfo.getWxOpenId());
        List<MemberTaobaoDo> listMemberDo = list(queryWrapper);
        if (CollectionUtils.isEmpty(listMemberDo)) {
            MemberTaobaoDo memberTaobaoDo = new MemberTaobaoDo();
            memberTaobaoDo.setCreateDate(new Date());
            memberTaobaoDo.setOpenId(memberInfo.getWxOpenId());
            memberTaobaoDo.setInviterCode(inviterTbCode.getInviterCode());
            memberTaobaoDo.setInviterUrl(inviterTbCode.getInviterUrl());
            save(memberTaobaoDo);
            return inviterTbCode.getTwdCode();
        } else {
            MemberTaobaoDo memberTaobaoDo = listMemberDo.get(0);
            memberTaobaoDo.setCreateDate(new Date());
            memberTaobaoDo.setOpenId(memberInfo.getWxOpenId());
            memberTaobaoDo.setInviterCode(inviterTbCode.getInviterCode());
            memberTaobaoDo.setInviterUrl(inviterTbCode.getInviterUrl());
            saveOrUpdate(memberTaobaoDo);
            return inviterTbCode.getTwdCode();
        }
    }

    @Override
    @ApiOperation(value = "淘宝客渠道备案")
    @IsApiService
    public Boolean tbkInfoSave() {
        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();
        // 通过openId查询
        QueryWrapper<MemberTaobaoDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberTaobaoDo::getOpenId, memberInfo.getWxOpenId());
        List<MemberTaobaoDo> listMemberDo = list(queryWrapper);
        if (CollectionUtils.isEmpty(listMemberDo)) {
            throw new GlobalException(ResponseCode.RES_PARAM_IS_EMPTY, "你还没有备案哦,请到个人中心进行备案");
        }
        MemberTaobaoDo memberTaobaoDo = listMemberDo.get(0);
        if (!StringUtils.isEmpty(memberTaobaoDo.getRelationId())) {
            return true;
        }
        InviterMemberInfo inviterMemberInfo = taobaoService.getInviterMemberInfo(MemberThreadLocal.getMemberInfo().getWxOpenId());
        if (inviterMemberInfo == null) {
            return false;
        }
        memberTaobaoDo.setAccountName(inviterMemberInfo.getAccount_name());
        memberTaobaoDo.setRealName(inviterMemberInfo.getReal_name());
        memberTaobaoDo.setRelationId(inviterMemberInfo.getRelation_id());
        save(memberTaobaoDo);
        return true;
    }

    @Override
    public MemberTaobaoDo getMemberTaobaoDo(String relationId) {
        QueryWrapper<MemberTaobaoDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberTaobaoDo::getRelationId, relationId);
        List<MemberTaobaoDo> listMemberDo = list(queryWrapper);
        if (CollectionUtils.isEmpty(listMemberDo)) {
            return null;
        }
        return listMemberDo.get(0);
    }

    @Override
    public MemberTaobaoDo getMemberTaobaoDoByOpenId(String openId) {
        QueryWrapper<MemberTaobaoDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberTaobaoDo::getOpenId, openId);
        List<MemberTaobaoDo> listMemberDo = list(queryWrapper);
        if (CollectionUtils.isEmpty(listMemberDo)) {
            return null;
        }
        return listMemberDo.get(0);
    }

    @Override
    public String getRelationId() {
        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            String relationId = jedis.get(ShareContent.MEMBER_RELATION_KEY + memberInfo.getWxOpenId());

            if (!StringUtils.isEmpty(relationId)) {
                return relationId;
            }
            QueryWrapper<MemberTaobaoDo> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(MemberTaobaoDo::getOpenId, memberInfo.getWxOpenId());
            List<MemberTaobaoDo> listMemberDo = list(queryWrapper);
            if (CollectionUtils.isEmpty(listMemberDo)) {
                return null;
            }
            String dBRelationId = listMemberDo.get(0).getRelationId();
            if (StringUtils.isEmpty(dBRelationId)) {
                return null;
            }
            jedis.set(ShareContent.MEMBER_RELATION_KEY + memberInfo.getWxOpenId(), dBRelationId);
            return dBRelationId;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }
}
