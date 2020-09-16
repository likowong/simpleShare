package com.simple.share.service;

import com.simple.share.model.MemberTaobaoDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 淘宝用户表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
public interface MemberTaobaoService extends IService<MemberTaobaoDo> {
    /**
     * @return
     * @Author: luke
     * @Description: 生成邀请并返回备案淘口令
     * @Date: 2020-09-16 11:58
     * @param:null
     **/
    String createInviterCodeResultTbCode();

    /**
     * @return
     * @Author: luke
     * @Description: 淘宝客渠道备案
     * @Date: 2020-09-16 12:01
     * @param:null
     **/
    Boolean tbkInfoSave();
    /**
     * @Author: luke
     * @Description: 通过渠道ID查询wxOenId
     * @Date:  2020-09-16 18:28
    * @param:null
     * @return
     **/
    MemberTaobaoDo getMemberTaobaoDo(String relationId);

    /**
     * @Author: luke
     * @Description: 通过OpenID查询渠道Id
     * @Date:  2020-09-16 18:28
     * @param:null
     * @return
     **/
    MemberTaobaoDo getMemberTaobaoDoByOpenId(String openId);
}
