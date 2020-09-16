package com.simple.share.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 淘宝订单表
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_taobao_order")
public class TaobaoOrderDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 渠道关系id
     */
    @JSONField(name = "relation_id")
    private String relationId;

    /**
     * 订单在淘宝拍下付款的时间
     */
    @JSONField(name = "tb_paid_time")
    private String tbPaidTime;

    /**
     * 订单付款的时间
     */
    @JSONField(name = "tk_paid_time")
    private String tkPaidTime;

    /**
     * 买家确认收货的付款金额（不包含运费金额）
     */
    @JSONField(name = "alipay_total_price")
    private String alipayTotalPrice;

    /**
     * 结算预估收入=结算金额*提成。以买家确认收货的付款金额为基数，预估您可能获得的收入。因买家退款、您违规推广等原因，可能与您最终收入不一致。最终收入以月结后您实际收到的为准
     */
    @JSONField(name = "pub_share_pre_fee")
    private String pubSharePreFee;

    /**
     * 订单编号
     */
    @JSONField(name = "trade_id")
    private String tradeId;

    /**
     * 订单确认收货后且商家完成佣金支付的时间
     */
    @JSONField(name = "tk_earning_time")
    private String tkEarningTime;

    /**
     * 推广位管理下的推广位名称对应的ID，同时也是pid=mm_1_2_3中的“3”这段数字
     */
    @JSONField(name = "adzone_id")
    private String adzoneId;

    /**
     * 平台出资方，如天猫、淘宝、或聚划算等
     */
    @JSONField(name = "subsidy_type")
    private String subsidyType;

    /**
     * 已付款：指订单已付款，但还未确认收货 已收货：指订单已确认收货，但商家佣金未支付 已结算：指订单已确认收货，且商家佣金已支付成功 已失效：指订单关闭/订单佣金小于0.01元，订单关闭主要有：1）买家超时未付款； 2）买家付款前，买家/卖家取消了订单；3）订单付款后发起售中退款成功；3：订单结算，12：订单付款， 13：订单失效，14：订单成功
     */
    @JSONField(name = "tk_status")
    private String tkStatus;

    /**
     * 商品id
     */
    @JSONField(name = "item_id")
    private String itemId;

    /**
     * 商品链接
     */
    @JSONField(name = "item_link")
    private String itemLink;

    /**
     * 佣金比率
     */
    @JSONField(name = "total_commission_fee")
    private String totalCommissionRate;
    /**
     * 商品图片地址
     */
    @JSONField(name = "item_img")
    private String itemImg;


}
