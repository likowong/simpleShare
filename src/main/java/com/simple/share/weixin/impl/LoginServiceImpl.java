package com.simple.share.weixin.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.weixin.LoginService;
import com.simple.share.weixin.dto.WeixinUserInfo;
import com.spring.simple.development.support.constant.CommonConstant;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.properties.PropertyConfigurer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  22:11
 */
@Service
public class LoginServiceImpl implements LoginService {
    /**
     * 微信相关配置
     **/
    private String appId = PropertyConfigurer.getProperty("weixin.miniprogram.appkey");
    private String secret = PropertyConfigurer.getProperty("weixin.miniprogram.secret");
    private String loginUrl = PropertyConfigurer.getProperty("weixin.miniprogram.login.url");
    private String grantType = "authorization_code";

    @Override
    public WeixinUserInfo getWeixinUserInfo(String code) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", appId);
        paramMap.put("secret", secret);
        paramMap.put("js_code", code);
        paramMap.put("grant_type", grantType);
        String json = HttpUtil.get(loginUrl, paramMap);
        WeixinUserInfo weixinUserInfo = JSONObject.parseObject(json, WeixinUserInfo.class);
        if (weixinUserInfo == null) {
            throw new GlobalException(SimpleShareResponseCode.RES_WEiXIN_LOGIN_FAil, "登录失败,请再试一次");
        }
        if (weixinUserInfo.getErrCode() != null && !CommonConstant.CODE0.equals(weixinUserInfo.getErrCode())) {
            throw new GlobalException(SimpleShareResponseCode.RES_WEiXIN_LOGIN_FAil, "登录失败,请再试一次");
        }
        return weixinUserInfo;
    }
}
