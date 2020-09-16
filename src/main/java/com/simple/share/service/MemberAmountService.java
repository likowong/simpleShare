package com.simple.share.service;

import com.simple.share.model.MemberAmountDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.taobao.MemberAmountVo;

/**
 * <p>
 * 淘宝订单表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
public interface MemberAmountService extends IService<MemberAmountDo> {
    /**
     * @return
     * @Author: luke
     * @Description: 查询订单金额
     * @Date: 2020-09-16 18:20
     * @param:null
     **/
    MemberAmountDo getMemberAmountDo(String orderNo);

    /**
     * @return
     * @Author: luke
     * @Description: 获取个人收益
     * @Date: 2020-09-16 19:46
     * @param:null
     **/
    MemberAmountVo getMemberAmountVo();
}
