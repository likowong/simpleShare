package com.simple.share.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 请求物料Vo
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  17:32
 */
@Data
@ApiModel(value = "reqMaterialVo", description = "请求物料Vo")
public class ReqMaterialVo {
    /**
     * 类目Id
     **/
    @ApiModelProperty(value = "类目Id", example = "123213213")
    @NotNull(message = "类目Id不能为空")
    private Long materialId;
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
