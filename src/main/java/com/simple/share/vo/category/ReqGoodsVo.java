package com.simple.share.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 搜素商品对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  17:32
 */
@Data
@ApiModel(value = "reqGoodsVo", description = "搜素商品对象")
public class ReqGoodsVo {
    /**
     * 搜索关键字
     **/
    @ApiModelProperty(value = "类目Id", example = "123213213")
    @NotEmpty(message = "搜索关键字不能为空")
    private String text;
    /**
     * 排序规则
     **/
    @ApiModelProperty(value = "排序规则", example = "1")
    private String sort;
    /**
     * 页大小
     **/
    @ApiModelProperty(value = "页大小", example = "10")
    @NotNull(message = "页大小不能为空")
    private Long pageSize;
    /**
     * 第几页
     **/
    @ApiModelProperty(value = "第几页", example = "1")
    @NotNull(message = "页数为空")
    private Long pageNo;
}
