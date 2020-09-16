package com.simple.share.taobao.dto;

import lombok.Data;

/**
 * 备案邀请信息
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  12:38
 */
@Data
public class InviterInfo {
    /**
     * 微信用户唯一Id
     **/
    private String openId;
    /**
     * 邀请码
     **/
    private String inviterCode;
    /**
     * 邀请地址
     **/
    private String inviterUrl;
    /**
     * 淘宝令
     **/
    private String twdCode;
}
