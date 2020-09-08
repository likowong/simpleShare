package com.simple.share.service;

import com.simple.share.model.MemberDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.wexin.UserInfoVo;
import com.simple.share.vo.wexin.WeixinLoginVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-08
 */
public interface MemberService extends IService<MemberDo> {

    /**
     * @return UserInfoVo
     * @Author: luke
     * @Description: 微信登录
     * @Date: 2020-09-09 0:02
     * @param:weixinLoginVo
     **/
    UserInfoVo login(WeixinLoginVo weixinLoginVo);
}
