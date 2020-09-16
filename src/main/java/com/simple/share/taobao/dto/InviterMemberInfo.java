package com.simple.share.taobao.dto;

import lombok.Data;

/**
 * 备案用户信息
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  13:04
 */
@Data
public class InviterMemberInfo {
    /**
     * 渠道独有 - 渠道关系ID
     **/
    private String relation_id;
    /**
     * 共享字段 - 渠道/会员原始身份信息
     **/
    private String rtag;
    /**
     * 共享字段 - 渠道/会员昵称
     **/
    private String account_name;
    /**
     * 共享字段 - 渠道/会员姓名
     **/
    private String real_name;

}
