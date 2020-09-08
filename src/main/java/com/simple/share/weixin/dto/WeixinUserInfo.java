package com.simple.share.weixin.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 微信用户信息
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  20:32
 */
@Data
public class WeixinUserInfo {
    /**
     * 用户唯一标识
     **/
    @JSONField(name = "openid")
    private String openId;
    /**
     * 会话密钥
     **/
    @JSONField(name = "session_key")
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
     **/
    @JSONField(name = "unionid")
    private String unionId;
    /**
     * 错误码
     **/
    @JSONField(name = "errcode")
    private String errCode;
}
