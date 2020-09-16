package com.simple.share.service;

import com.simple.share.model.MemberAliAccountDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.taobao.MemberAliAccountVo;

/**
 * <p>
 * 支付宝账户 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
public interface MemberAliAccountService extends IService<MemberAliAccountDo> {
    /**
     * @return
     * @Author: luke
     * @Description: 填写体现账户
     * @Date: 2020-09-16 23:48
     * @param:null
     **/
    void addMemberAliAccountVo(MemberAliAccountVo memberAliAccountVo);
    /**
     * @Author: luke
     * @Description: 查询支付宝账户
     * @Date:  2020-09-16 23:55
    * @param:null
     * @return 
     **/
    MemberAliAccountVo getMemberAliAccount();
}
