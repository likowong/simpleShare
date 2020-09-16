package com.simple.share.model;

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
 * 淘宝用户表
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_member_taobao")
public class MemberTaobaoDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信用户唯一标识openId
     */
    private String openId;

    /**
     * 邀请码
     */
    private String inviterCode;

    /**
     * 邀请地址
     */
    private String inviterUrl;

    /**
     * 渠道Id
     */
    @TableField("relationId")
    private String relationId;

    /**
     * 渠道名称
     */
    private String realName;

    /**
     * 备案日期
     */
    private Date createDate;

    /**
     * 渠道昵称
     */
    private String accountName;


}
