package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.interceptor.MemberThreadLocal;
import com.simple.share.model.MemberAliAccountDo;
import com.simple.share.mapper.MemberAliAccountMapper;
import com.simple.share.model.MemberTaobaoDo;
import com.simple.share.service.MemberAliAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.taobao.MemberAliAccountVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import com.spring.simple.development.support.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 支付宝账户 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Api(tags = "支付宝账户")
@Service
public class MemberAliAccountServiceImpl extends ServiceImpl<MemberAliAccountMapper, MemberAliAccountDo> implements MemberAliAccountService {
    @Autowired
    private BaseSupport baseSupport;

    @Override
    @ApiOperation(value = "添加或更新支付宝账户")
    @IsApiService
    @ValidHandler(key = "memberAliAccountVo", value = MemberAliAccountVo.class)
    public void addMemberAliAccountVo(MemberAliAccountVo memberAliAccountVo) {

        MemberAliAccountDo memberAliAccountDo = baseSupport.objectCopy(memberAliAccountVo, MemberAliAccountDo.class);
        MemberAliAccountVo memberAliAccount = getMemberAliAccount();
        if (memberAliAccount != null) {
            memberAliAccountDo.setId(memberAliAccount.getId());
        }
        memberAliAccountDo.setOpenId(MemberThreadLocal.getMemberInfo().getWxOpenId());
        saveOrUpdate(memberAliAccountDo);
    }

    @Override
    @ApiOperation(value = "查询支付宝账户")
    @IsApiService
    @ValidHandler(key = "memberAliAccountVo", value = MemberAliAccountVo.class)
    public MemberAliAccountVo getMemberAliAccount() {
        QueryWrapper<MemberAliAccountDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberAliAccountDo::getOpenId, MemberThreadLocal.getMemberInfo().getWxOpenId());
        List<MemberAliAccountDo> memberAliAccountDoList = list(queryWrapper);
        if (CollectionUtils.isEmpty(memberAliAccountDoList)) {
            return null;
        }
        return baseSupport.objectCopy(memberAliAccountDoList.get(0), MemberAliAccountVo.class);
    }


}
