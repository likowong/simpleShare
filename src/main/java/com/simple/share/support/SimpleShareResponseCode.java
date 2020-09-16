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


    /**
     * 淘宝客商品详情页获取失败
     */
    public static final int TAOBAO_TBK_ITEM_INFO_GET = 1003;
    public static final String TAOBAO_TBK_ITEM_INFO_GET_CODE = "taobao tbk item info get fail";

    /**
     * 生成淘口令失败
     */
    public static final int TAOBAO_TBK_TPWD_CREATE = 1004;
    public static final String TAOBAO_TBK_TPWD_CREATE_CODE = "taobao tbk tpwd create fail";

    /**
     * 搜索商品失败
     */
    public static final int TAOBAO_TBK_DG_MATERIAL_OPTIONAL = 1005;
    public static final String TAOBAO_TBK_DG_MATERIAL_OPTIONAL_CODE = "taobao tbk dg material optional fail";

    /**
     * 生成备案口令失败
     */
    public static final int TAOBAO_TBK_SC_INVITECODE_GET = 1006;
    public static final String TAOBAO_TBK_SC_INVITECODE_GET_CODE = "taobao tbk sc invitecode get fail";


    /**
     * 私域用户备案信息查询失败
     */
    public static final int TAOBAO_TBK_SC_PUBLISHER_INFO_GET = 1007;
    public static final String TAOBAO_TBK_SC_PUBLISHER_INFO_GET_CODE = "taobao tbk sc publisher info get fail";

    /**
     * 淘宝客-推广者-所有订单查询 查询失败
     */
    public static final int TAOBAO_TBK_ORDER_DETAILS_GET = 1008;
    public static final String TAOBAO_TBK_ORDER_DETAILS_GET_CODE = "taobao tbk order details get fail";


    /**
     * 淘宝客-推广者-所有订单查询 查询失败
     */
    public static final int ADD_ALI_PAY_ACCOUNT = 1009;
    public static final String ADD_ALI_PAY_ACCOUNT_CODE = "add ali pay account fail";




    private SimpleShareResponseCode(int status, String code, String message) {
        super(status, code, message);
    }

    public static SimpleShareResponseCode newResponse(int status, String code, String message) {
        return new SimpleShareResponseCode(status, code, message);
    }

    public static final GlobalResponseCode RES_WEiXIN_LOGIN_FAil = new GlobalResponseCode(WEiXIN_LOGIN_FAil, WEiXIN_LOGIN_FAil_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_ACTIVITY_INFO_GET = new GlobalResponseCode(TAOBAO_TBK_ACTIVITY_INFO_GET, TAOBAO_TBK_ACTIVITY_INFO_GET_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_DG_OPTIMUS_MATERIAL = new GlobalResponseCode(TAOBAO_TBK_DG_OPTIMUS_MATERIAL, TAOBAO_TBK_DG_OPTIMUS_MATERIAL_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_ITEM_INFO_GET = new GlobalResponseCode(TAOBAO_TBK_ITEM_INFO_GET, TAOBAO_TBK_ITEM_INFO_GET_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_TPWD_CREATE = new GlobalResponseCode(TAOBAO_TBK_TPWD_CREATE, TAOBAO_TBK_TPWD_CREATE_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_DG_MATERIAL_OPTIONAL = new GlobalResponseCode(TAOBAO_TBK_DG_MATERIAL_OPTIONAL, TAOBAO_TBK_DG_MATERIAL_OPTIONAL_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_SC_INVITECODE_GET = new GlobalResponseCode(TAOBAO_TBK_SC_INVITECODE_GET, TAOBAO_TBK_SC_INVITECODE_GET_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_SC_PUBLISHER_INFO_GET = new GlobalResponseCode(TAOBAO_TBK_SC_PUBLISHER_INFO_GET, TAOBAO_TBK_SC_PUBLISHER_INFO_GET_CODE, "%s");
    public static final GlobalResponseCode RES_TAOBAO_TBK_ORDER_DETAILS_GET = new GlobalResponseCode(TAOBAO_TBK_ORDER_DETAILS_GET, TAOBAO_TBK_ORDER_DETAILS_GET_CODE, "%s");
    public static final GlobalResponseCode RES_ADD_ALI_PAY_ACCOUNT = new GlobalResponseCode(ADD_ALI_PAY_ACCOUNT, ADD_ALI_PAY_ACCOUNT_CODE, "%s");

}
