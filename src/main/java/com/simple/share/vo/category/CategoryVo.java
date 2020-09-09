package com.simple.share.vo.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 类目渲染对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  16:48
 */
@Data
@ApiModel(value = "categoryVo", description = "类目渲染对象")
public class CategoryVo {
    /**
     * 类目Id
     **/
    @ApiModelProperty(value = "类目Id", example = "聚划算")
    private String materialId;

    /**
     * 类目名
     **/
    @ApiModelProperty(value = "类目名", example = "聚划算")
    private String materialName;
    /**
     * 类目跳转地址
     **/
    @ApiModelProperty(value = "类目icon", example = "http://12.png")
    private String materialUrl;

    /**
     * icon地址
     */
    @ApiModelProperty(value = "icon地址", example = "http://12.png")
    private String iconUrl;

}
