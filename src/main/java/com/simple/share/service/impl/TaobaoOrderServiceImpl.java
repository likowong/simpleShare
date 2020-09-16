package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simple.share.interceptor.MemberInfo;
import com.simple.share.interceptor.MemberThreadLocal;
import com.simple.share.model.DemoDo;
import com.simple.share.model.MemberTaobaoDo;
import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.mapper.TaobaoOrderMapper;
import com.simple.share.service.MemberAmountService;
import com.simple.share.service.MemberTaobaoService;
import com.simple.share.service.TaobaoOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.vo.category.ReqGoodsVo;
import com.simple.share.vo.taobao.OrderVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.page.ReqPageDTO;
import com.spring.simple.development.core.component.mvc.page.ResPageDTO;
import com.spring.simple.development.support.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 淘宝订单表 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Api(tags = "订单相关")
@Service
public class TaobaoOrderServiceImpl extends ServiceImpl<TaobaoOrderMapper, TaobaoOrderDo> implements TaobaoOrderService {

    @Autowired
    private MemberTaobaoService memberTaobaoService;

    @Override
    public TaobaoOrderDo getTaobaoOrderDoByOrderNo(String orderNo) {

        QueryWrapper<TaobaoOrderDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TaobaoOrderDo::getTradeId, orderNo);
        List<TaobaoOrderDo> orderDoList = list(queryWrapper);
        if (CollectionUtils.isEmpty(orderDoList)) {
            return null;
        }
        return orderDoList.get(0);
    }

    @Override
    @ApiOperation(value = "获取订单列表")
    @ApiImplicitParam(name = "resPageDTO", description = "物料对象", resultDataType = ResPageDTO.class)
    @IsApiService
    @ValidHandler(key = "reqPageDTO", value = OrderVo.class)
    public ResPageDTO getPageTaobaoOrderDo(OrderVo orderVo) {

        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();

        MemberTaobaoDo memberTaobaoDo = memberTaobaoService.getMemberTaobaoDoByOpenId(memberInfo.getWxOpenId());
        if (memberTaobaoDo == null) {
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_PUBLISHER_INFO_GET, "请先备案");
        }
        if (StringUtils.isEmpty(memberTaobaoDo.getRelationId())) {
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_PUBLISHER_INFO_GET, "请先备案");
        }
        QueryWrapper<TaobaoOrderDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("tb_paid_time");
        if (!StringUtils.isEmpty(orderVo.getOrderStatus())) {
            queryWrapper.lambda().eq(TaobaoOrderDo::getTkStatus, orderVo.getOrderStatus()).eq(TaobaoOrderDo::getRelationId, memberTaobaoDo.getRelationId());
        }
        IPage<TaobaoOrderDo> page = new Page<>(orderVo.getStartPage(), orderVo.getPageSize());
        IPage<TaobaoOrderDo> taobaoOrderDoIPage = this.baseMapper.selectPage(page, queryWrapper);
        ResPageDTO resPageDTO = new ResPageDTO();
        resPageDTO.setList(taobaoOrderDoIPage.getRecords());
        resPageDTO.setPageSize((int) taobaoOrderDoIPage.getSize());
        resPageDTO.setPageNum((int) taobaoOrderDoIPage.getCurrent());
        resPageDTO.setTotalCount(taobaoOrderDoIPage.getPages());
        return resPageDTO;
    }

    @Override
    public List<TaobaoOrderDo> getListTaobaoOrderDo(String relationId, String orderStatus) {
        QueryWrapper<TaobaoOrderDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TaobaoOrderDo::getRelationId, relationId).eq(TaobaoOrderDo::getTkStatus, orderStatus);
        List<TaobaoOrderDo> orderDoList = list(queryWrapper);
        if (CollectionUtils.isEmpty(orderDoList)) {
            return null;
        }
        return orderDoList;
    }

    @Override
    public List<TaobaoOrderDo> getAllTaobaoOrderDo() {
        QueryWrapper<TaobaoOrderDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TaobaoOrderDo::getTkStatus, "12");
        List<TaobaoOrderDo> orderDoList = list(queryWrapper);
        if (CollectionUtils.isEmpty(orderDoList)) {
            return null;
        }
        return orderDoList;
    }
}
