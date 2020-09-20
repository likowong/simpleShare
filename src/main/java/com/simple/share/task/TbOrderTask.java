package com.simple.share.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.simple.share.model.MemberAmountDo;
import com.simple.share.model.MemberTaobaoDo;
import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.service.MaterialService;
import com.simple.share.service.MemberAmountService;
import com.simple.share.service.MemberTaobaoService;
import com.simple.share.service.TaobaoOrderService;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.taobao.dto.InviterMemberInfo;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqMaterialVo;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import com.spring.simple.development.support.constant.CommonConstant;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  20:32
 */
@Configuration
@EnableScheduling
public class TbOrderTask {
    @Autowired
    private BaseSupport baseSupport;
    @Autowired
    private TaobaoService taobaoService;
    @Autowired
    private MemberAmountService memberAmountService;
    @Autowired
    private TaobaoOrderService taobaoOrderService;
    @Autowired
    private MemberTaobaoService memberTaobaoService;

    /**
     * @return
     * @Author: luke
     * @Description: 每两个执行一次拉取订单入库 拉取当月和上个月的订单
     * @Date: 2020-09-09 20:37
     * @param:null
     **/
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void pullOrder() {
        String end = DateUtil.formatDateTime(new Date());
        DateTime startTime = DateUtil.offsetHour(new Date(), -2);
        String start = DateUtil.formatDateTime(startTime);
        List<TaobaoOrderDo> taobaoOrderDoList = taobaoService.getTaobaoOrderDoList(start, end);
        if (CollectionUtils.isEmpty(taobaoOrderDoList)) {
            return;
        }
        for (TaobaoOrderDo taobaoOrderDo : taobaoOrderDoList) {
            TaobaoOrderDo dbTaobaoOrderDo = taobaoOrderService.getTaobaoOrderDoByOrderNo(taobaoOrderDo.getTradeId());
            if (dbTaobaoOrderDo == null) {
                // 入库
                taobaoOrderService.save(taobaoOrderDo);
            } else {
                TaobaoOrderDo copyTaobaoOrderDo = baseSupport.objectCopy(taobaoOrderDo, TaobaoOrderDo.class);
                copyTaobaoOrderDo.setId(dbTaobaoOrderDo.getId());
                // 更新
                taobaoOrderService.updateById(copyTaobaoOrderDo);
            }

            MemberAmountDo dbMemberAmountDo = memberAmountService.getMemberAmountDo(taobaoOrderDo.getTradeId());

            // 计算金额
            MemberTaobaoDo memberTaobaoDo = memberTaobaoService.getMemberTaobaoDo(taobaoOrderDo.getRelationId());
            if (memberTaobaoDo == null) {
                continue;
            }
            if (dbMemberAmountDo == null) {
                MemberAmountDo memberAmountDo = new MemberAmountDo();
                memberAmountDo.setIsPay("n");
                memberAmountDo.setAmount(new BigDecimal(dbTaobaoOrderDo.getPubSharePreFee()));
                memberAmountDo.setChannel("tb");
                memberAmountDo.setCreateDate(new Date());
                if (dbTaobaoOrderDo.getTkStatus().equals("12")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE1);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("13")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE2);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("14")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE3);
                }
                memberAmountDo.setOrderNo(dbTaobaoOrderDo.getTradeId());
                memberAmountDo.setRemarks("淘宝订单");
                memberAmountDo.setOpenId(memberTaobaoDo.getOpenId());
                memberAmountService.save(memberAmountDo);
            } else {
                if (dbTaobaoOrderDo.getTkStatus().equals("12")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE1);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("13")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE2);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("14")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE3);
                }
                memberAmountService.updateById(dbMemberAmountDo);
            }
        }
    }


    /**
     * @return
     * @Author: luke
     * @Description: 10执行一次同步订单
     * @Date: 2020-09-09 20:37
     * @param:null
     **/
    @Scheduled(cron = "0 0/10 * * * ?")
    public void changeOrder() {

        List<TaobaoOrderDo> allTaobaoOrderDo = taobaoOrderService.getAllTaobaoOrderDo();
        if (CollectionUtils.isEmpty(allTaobaoOrderDo)) {
            return;
        }
        for (TaobaoOrderDo dbTaobaoOrderDo : allTaobaoOrderDo) {
            String tbPaidTime = dbTaobaoOrderDo.getTbPaidTime();
            DateTime parse = DateUtil.parse(tbPaidTime);
            DateTime startTime = DateUtil.offsetHour(parse, -1);
            DateTime endTime = DateUtil.offsetHour(parse, 1);
            List<TaobaoOrderDo> taobaoOrderDoList = taobaoService.getTaobaoOrderDoList(DateUtil.formatDateTime(startTime), DateUtil.formatDateTime(endTime));
            if (CollectionUtils.isEmpty(taobaoOrderDoList)) {
                continue;
            }
            List<TaobaoOrderDo> onlyTaobaoOrderDo = taobaoOrderDoList.stream().filter(TaobaoOrderDo -> dbTaobaoOrderDo.getTradeId().equals(TaobaoOrderDo.getTradeId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(onlyTaobaoOrderDo)) {
                continue;
            }
            TaobaoOrderDo copyTaobaoOrderDo = baseSupport.objectCopy(onlyTaobaoOrderDo.get(0), TaobaoOrderDo.class);
            copyTaobaoOrderDo.setId(dbTaobaoOrderDo.getId());
            // 更新
            taobaoOrderService.updateById(copyTaobaoOrderDo);
            MemberAmountDo dbMemberAmountDo = memberAmountService.getMemberAmountDo(onlyTaobaoOrderDo.get(0).getTradeId());
            // 计算金额
            MemberTaobaoDo memberTaobaoDo = memberTaobaoService.getMemberTaobaoDo(onlyTaobaoOrderDo.get(0).getRelationId());
            if (memberTaobaoDo == null) {
                continue;
            }
            if (dbMemberAmountDo == null) {
                MemberAmountDo memberAmountDo = new MemberAmountDo();
                memberAmountDo.setIsPay("n");
                memberAmountDo.setAmount(new BigDecimal(dbTaobaoOrderDo.getPubSharePreFee()));
                memberAmountDo.setChannel("tb");
                memberAmountDo.setCreateDate(new Date());
                if (dbTaobaoOrderDo.getTkStatus().equals("12")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE1);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("13")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE2);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("14")) {
                    memberAmountDo.setIsValid(CommonConstant.CODE3);
                }
                memberAmountDo.setOrderNo(dbTaobaoOrderDo.getTradeId());
                memberAmountDo.setRemarks("淘宝订单");
                memberAmountDo.setOpenId(memberTaobaoDo.getOpenId());
                memberAmountService.save(memberAmountDo);
            } else {
                if (dbTaobaoOrderDo.getTkStatus().equals("12")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE1);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("13")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE2);
                }
                if (dbTaobaoOrderDo.getTkStatus().equals("14")) {
                    dbMemberAmountDo.setIsValid(CommonConstant.CODE3);
                }
                memberAmountService.updateById(dbMemberAmountDo);
            }

        }
    }
}
