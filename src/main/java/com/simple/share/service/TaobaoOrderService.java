package com.simple.share.service;

import com.simple.share.model.TaobaoOrderDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.taobao.OrderVo;
import com.spring.simple.development.core.component.mvc.page.ReqPageDTO;
import com.spring.simple.development.core.component.mvc.page.ResPageDTO;

import java.util.List;

/**
 * <p>
 * 淘宝订单表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
public interface TaobaoOrderService extends IService<TaobaoOrderDo> {
    /**
     * @return
     * @Author: luke
     * @Description: 获取订单
     * @Date: 2020-09-16 16:37
     * @param:null
     **/
    TaobaoOrderDo getTaobaoOrderDoByOrderNo(String orderNo);


    /**
     * @return
     * @Author: luke
     * @Description: 获取订单列表
     * @Date: 2020-09-16 16:54
     * @param:null
     **/
    ResPageDTO getPageTaobaoOrderDo(OrderVo orderVo);

    /**
     * @return
     * @Author: luke
     * @Description: 根据渠道Id查询订单
     * @Date: 2020-09-16 19:49
     * @param:null
     **/
    List<TaobaoOrderDo> getListTaobaoOrderDo(String relationId, String orderStatus);

    /**
     * @return
     * @Author: luke
     * @Description: 获取所有付款的订单
     * @Date: 2020-09-16 21:20
     * @param:null
     **/
    List<TaobaoOrderDo> getAllTaobaoOrderDo();
}
