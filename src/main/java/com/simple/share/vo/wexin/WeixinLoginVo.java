package com.simple.share.vo.wexin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  0:01
 */
@ApiModel(value = "weixinLoginVo", description = "微信登录信息")
@Data
public class WeixinLoginVo {
    /**
     * 用户昵称
     **/
    @NotNull(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户昵称", example = "luke")
    private String nickName;
    /**
     * 用户头像
     **/
    @NotNull(message = "用户昵称不能为空")
    @ApiModelProperty(value = "用户头像", example = "http://boy.com")
    private String avatarUrl;
    /**
     * 用户临时登录Code
     **/
    @NotNull(message = "用户临时登录Code不能为空")
    @ApiModelProperty(value = "用户临时登录Code", example = "154sghdg3ss")
    private String code;
    /**
     * 用户城市
     **/
    @ApiModelProperty(value = "用户临时登录Code", example = "154sghdg3ss")
    private String address;
    /**
     * 用户性别
     **/
    @ApiModelProperty(value = "用户临时登录Code", example = "1")
    private String gender;
}
