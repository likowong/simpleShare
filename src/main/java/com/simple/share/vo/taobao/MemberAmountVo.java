package com.simple.share.vo.taobao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人金额对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  19:43
 */
@Data
@ApiModel(value = "memberAmountVo", description = "个人金额对象")
public class MemberAmountVo {
    /**
     * 预计收益
     **/
    @ApiModelProperty(value = "预计收益", example = "12")
    private String price1;
    /**
     * 可体现
     **/
    @ApiModelProperty(value = "可体现", example = "12")
    private String price2;
}
