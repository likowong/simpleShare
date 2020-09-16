package com.simple.share.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_member_amount")
public class MemberAmountDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 1.tb,2.jd,3.pdd
     */
    private String channel;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 微信openId
     */
    @TableField("openId")
    private String openId;

    /**
     * 是否结清 y/n
     */
    private String isPay;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 是否有效：1.预计收入，2.订单失效，3.实际收入
     */
    private String isValid;


}
