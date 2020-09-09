package com.simple.share.service;

import com.simple.share.model.BannerDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.active.ActiveInfoVo;

import java.util.List;

/**
 * <p>
 * banner活动表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
public interface BannerService extends IService<BannerDo> {
    /**
     * @Author: luke
     * @Description: 获取活动地址
     * @Date: 2020-09-09 15:48
     * @param:ActiveInfoVo
     **/
    List<ActiveInfoVo> getListBanner();
}
