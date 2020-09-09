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

    /**
     * 淘宝客-推广者-物料精选获取失败
     */
    public static final int TAOBAO_TBK_DG_OPTIMUS_MATERIAL = 1001;
    public static final String TAOBAO_TBK_DG_OPTIMUS_MATERIAL_CODE = "taobao tbk dg optimus material fail";

    /**
     * 淘宝客活动获取失败
     */
    public static final int TAOBAO_TBK_ACTIVITY_INFO_GET = 1002;
    public static final String TAOBAO_TBK_ACTIVITY_INFO_GET_CODE = "taobao tbk activity info get fail";


    private SimpleShareResponseCode(int status, String code, String message) {
        super(status, code, message);
    }

    public static SimpleShareResponseCode newResponse(int status, String code, String message) {
        return new SimpleShareResponseCode(status, code, message);
    }

    public static final GlobalResponseCode RES_WEiXIN_LOGIN_FAil = new GlobalResponseCode(WEiXIN_LOGIN_FAil, WEiXIN_LOGIN_FAil_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_ACTIVITY_INFO_GET = new GlobalResponseCode(TAOBAO_TBK_ACTIVITY_INFO_GET, TAOBAO_TBK_ACTIVITY_INFO_GET_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_DG_OPTIMUS_MATERIAL = new GlobalResponseCode(TAOBAO_TBK_DG_OPTIMUS_MATERIAL, TAOBAO_TBK_DG_OPTIMUS_MATERIAL_CODE, "%s");

}
