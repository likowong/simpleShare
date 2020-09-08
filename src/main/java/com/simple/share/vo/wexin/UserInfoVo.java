package com.simple.share.vo.wexin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  23:57
 */
@ApiModel(value = "userInfoVo", description = "微信授权用户信息")
@Data
public class UserInfoVo {
    /**
     * 用户昵称
     **/
    @ApiModelProperty(value = "用户昵称", example = "luke")
    private String nickName;
    /**
     * 用户头像
     **/
    @ApiModelProperty(value = "用户头像", example = "http://boy.com")
    private String avatarUrl;
    /**
     * 用户授权token
     **/
    @ApiModelProperty(value = "token", example = "qdgsh2573fdsg")
    private String token;
}
