package com.simple.share.vo.taobao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 支付宝账户
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "memberAliAccountVo", description = "支付宝账户对象")
public class MemberAliAccountVo{

    private Long id;

    /**
     * 支付宝账户
     */
    @NotNull(message = "支付宝账户为空")
    @ApiModelProperty(value = "支付宝账户", example = "12")
    private String aliPayAccount;

    /**
     * 手机号
     */
    @NotNull(message = "手机号为空")
    @ApiModelProperty(value = "手机号", example = "12")
    private String phone;

    /**
     * 姓名
     */
    @NotNull(message = "姓名为空")
    @ApiModelProperty(value = "姓名", example = "12")
    private String name;


}
