package com.simple.share.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.simple.share.contant.ShareContent;
import com.simple.share.model.MemberDo;
import com.simple.share.support.SimpleShareResponseCode;
import com.spring.simple.development.core.annotation.base.NoLogin;
import com.spring.simple.development.core.annotation.base.SimpleInterceptor;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.exception.GlobalResponseCode;
import com.spring.simple.development.support.exception.ResponseCode;
import com.spring.simple.development.support.utils.AESUtils;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author luke
 * @Date 2019年12月30日
 * @Description simple 拦截器
 */
@SimpleInterceptor
public class SimpleShareInterceptor implements HandlerInterceptor {

    public SimpleShareInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            NoLogin noLogin = method.getMethodAnnotation(NoLogin.class);
            if (noLogin != null) {
                return true;
            }
        }
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            throw new GlobalException(ResponseCode.SYS_NO_LOGIN, "token为空");
        }
        try {
            String decrypt = AESUtils.decrypt(AESUtils.coupon_key, token);
            if (StringUtils.isEmpty(decrypt)) {
                throw new GlobalException(ResponseCode.SYS_NO_LOGIN, "token为空");
            }
            token = decrypt;
        } catch (Exception ex) {
            throw new GlobalException(ResponseCode.SYS_NO_LOGIN, "token为空");
        }
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            String cacheMemberInfo = jedis.get(ShareContent.MEMBER_TOKEN_KEY + token);
            if (StringUtils.isEmpty(cacheMemberInfo)) {
                throw new GlobalException(GlobalResponseCode.SYS_NO_LOGIN, "token无效");
            }
            MemberDo memberDo = JSONObject.parseObject(cacheMemberInfo, MemberDo.class);
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setNickName(memberDo.getNickName());
            memberInfo.setPersonImageUrl(memberDo.getAvatarUrl());
            memberInfo.setWxOpenId(memberDo.getOpenId());
            memberInfo.setToken(token);
            MemberThreadLocal.setMemInfo(memberInfo);
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        MemberThreadLocal.remove();
    }
}
