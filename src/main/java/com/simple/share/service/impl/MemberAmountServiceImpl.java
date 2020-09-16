package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.interceptor.MemberInfo;
import com.simple.share.interceptor.MemberThreadLocal;
import com.simple.share.model.MemberAmountDo;
import com.simple.share.mapper.MemberAmountMapper;
import com.simple.share.model.MemberTaobaoDo;
import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.service.MemberAmountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.service.MemberTaobaoService;
import com.simple.share.service.TaobaoOrderService;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.vo.active.ActiveInfoVo;
import com.simple.share.vo.taobao.MemberAmountVo;
import com.simple.share.vo.wexin.WeixinLoginVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.support.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 账户金额相关
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Api(tags = "账户金额相关")
@Service
public class MemberAmountServiceImpl extends ServiceImpl<MemberAmountMapper, MemberAmountDo> implements MemberAmountService {
    @Autowired
    private MemberTaobaoService memberTaobaoService;
    @Autowired
    private TaobaoOrderService taobaoOrderService;

    @Override
    public MemberAmountDo getMemberAmountDo(String orderNo) {

        QueryWrapper<MemberAmountDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(MemberAmountDo::getOrderNo, orderNo);
        List<MemberAmountDo> memberAmountDos = list(queryWrapper);
        if (CollectionUtils.isEmpty(memberAmountDos)) {
            return null;
        }
        return memberAmountDos.get(0);
    }

    @Override
    @ApiOperation(value = "获取个人金额")
    @ApiImplicitParam(name = "memberAmountVo", description = "个人金额对象", resultDataType = MemberAmountVo.class)
    @IsApiService
    public MemberAmountVo getMemberAmountVo() {
        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();
        MemberAmountVo memberAmountVo = new MemberAmountVo();
        memberAmountVo.setPrice1("0.00");
        memberAmountVo.setPrice2("0.00");
        MemberTaobaoDo memberTaobaoDo = memberTaobaoService.getMemberTaobaoDoByOpenId(memberInfo.getWxOpenId());
        if (memberTaobaoDo == null) {
            return memberAmountVo;
        }
        if (StringUtils.isEmpty(memberTaobaoDo.getRelationId())) {
            return memberAmountVo;
        }
        List<TaobaoOrderDo> listTaobaoOrderDo = taobaoOrderService.getListTaobaoOrderDo(memberTaobaoDo.getRelationId(), "12");
        if (!CollectionUtils.isEmpty(listTaobaoOrderDo)) {
            BigDecimal bigDecimal = new BigDecimal(0.00);
            for (TaobaoOrderDo taobaoOrderDo : listTaobaoOrderDo) {
                bigDecimal = bigDecimal.add(new BigDecimal(taobaoOrderDo.getPubSharePreFee()));
            }
            memberAmountVo.setPrice1(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "");
        }
        List<TaobaoOrderDo> endListTaobaoOrderDo = taobaoOrderService.getListTaobaoOrderDo(memberTaobaoDo.getRelationId(), "14");
        if (!CollectionUtils.isEmpty(endListTaobaoOrderDo)) {
            BigDecimal bigDecimal = new BigDecimal(0.00);
            for (TaobaoOrderDo taobaoOrderDo : endListTaobaoOrderDo) {
                bigDecimal = bigDecimal.add(new BigDecimal(taobaoOrderDo.getPubSharePreFee()));
            }
            memberAmountVo.setPrice2(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "");
        }
        return memberAmountVo;
    }
}
