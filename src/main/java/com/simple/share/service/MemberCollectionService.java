package com.simple.share.service;

import com.simple.share.model.MemberCollectionDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.collection.MemberCollectionVo;
import com.simple.share.vo.taobao.OrderVo;
import com.spring.simple.development.core.component.mvc.page.ReqPageDTO;
import com.spring.simple.development.core.component.mvc.page.ResPageDTO;

/**
 * <p>
 * 商品收藏 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
public interface MemberCollectionService extends IService<MemberCollectionDo> {

    /**
     * @return
     * @Author: luke
     * @Description: 添加商品收藏
     * @Date: 2020-09-16 21:35
     * @param:null
     **/
    void addCollection(MemberCollectionVo memberCollectionVo);

    /**
     * @return
     * @Author: luke
     * @Description: 获取我的收藏列表
     * @Date: 2020-09-16 21:39
     * @param:null
     **/
    ResPageDTO getPageMemberCollectionDo(ReqPageDTO reqPageDTO);
}
