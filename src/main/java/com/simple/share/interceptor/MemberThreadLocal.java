package com.simple.share.interceptor;

/**
 * 用户会话信息管理器
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  12:05
 */
public class MemberThreadLocal {
    private static ThreadLocal<MemberInfo> memberInfoThreadLocal = new ThreadLocal<>();

    public static MemberInfo getMemberInfo() {
        return memberInfoThreadLocal.get();
    }

    public static void setMemInfo(MemberInfo memInfo) {
        memberInfoThreadLocal.set(memInfo);
    }

    public static void remove() {
        memberInfoThreadLocal.remove();
    }
}
