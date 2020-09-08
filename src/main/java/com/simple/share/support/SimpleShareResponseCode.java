package com.simple.share.support;

import com.spring.simple.development.support.exception.GlobalResponseCode;

/**
 * 业务错误码
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  22:03
 */
public class SimpleShareResponseCode extends GlobalResponseCode {
    /**
     * 业务错误状态码
     */
    /**
     * 微信登录失败
     */
    public static final int WEiXIN_LOGIN_FAil = 1000;
    public static final String WEiXIN_LOGIN_FAil_CODE = "weixin login fail";


    private SimpleShareResponseCode(int status, String code, String message) {
        super(status, code, message);
    }

    public static SimpleShareResponseCode newResponse(int status, String code, String message) {
        return new SimpleShareResponseCode(status, code, message);
    }

    public static final GlobalResponseCode RES_WEiXIN_LOGIN_FAil = new GlobalResponseCode(WEiXIN_LOGIN_FAil, WEiXIN_LOGIN_FAil_CODE, "%s");

}
