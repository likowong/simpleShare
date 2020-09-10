package com.simple.share.vo.taobao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 淘口令请求对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-10  15:57
 */
@ApiModel(value = "reqCreateTwdVo", description = "淘口令请求对象")
@Data
public class ReqCreateTwdVo {
    @ApiModelProperty(value = "内容", example = "复制口令到tb或tm领取优惠卷")
    @NotNull(message = "内容不能为空")
    private String text;
    @ApiModelProperty(value = "链接地址", example = "http://www.taobao.com")
    @NotNull(message = "链接地址不能为空")
    private String url;
}
