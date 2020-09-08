package com.simple.share.weixin;

import com.simple.share.weixin.dto.WeixinUserInfo;

/**
 * 微信登录服务
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  20:18
 */
public interface LoginService {
    /**
     * @return WeixinUserInfo
     * @Author: luke
     * @Description: 通过临时登录凭证code获取微信用户信息
     * @Date: 2020-09-08 22:00
     * @param:code
     **/
    WeixinUserInfo getWeixinUserInfo(String code);
}
