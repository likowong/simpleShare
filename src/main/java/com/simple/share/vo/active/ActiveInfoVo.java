package com.simple.share.vo.active;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * banner渲染对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  15:45
 */
@Data
@ApiModel(value = "activeInfoVo", description = "banner渲染对象")
public class ActiveInfoVo {

    /**
     * banner页面地址
     */
    /**
     * 用户昵称
     **/
    @ApiModelProperty(value = "banner页面地址", example = "http://taobao.com")
    private String bannerUrl;

    /**
     * 活动地址
     */
    @ApiModelProperty(value = "活动地址", example = "http://taobao.com")
    private String url;
}
