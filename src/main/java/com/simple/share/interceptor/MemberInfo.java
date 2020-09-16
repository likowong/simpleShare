package com.simple.share.interceptor;

import lombok.Data;

/**
 * 用户信息
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  12:05
 */
@Data
public class MemberInfo {
    private String wxOpenId;
    private String token;
    private String nickName;
    private String personImageUrl;
}
