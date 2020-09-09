package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.model.BannerDo;
import com.simple.share.mapper.BannerMapper;
import com.simple.share.model.MemberDo;
import com.simple.share.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.vo.active.ActiveInfoVo;
import com.simple.share.vo.wexin.WeixinLoginVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * banner活动表 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
@Api(tags = "banner活动页")
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, BannerDo> implements BannerService {
    @Autowired
    private BaseSupport baseSupport;

    @Override
    @ApiOperation(value = "banner活动接口")
    @ApiImplicitParam(name = "activeInfoVo", description = "登录对象", resultDataType = ActiveInfoVo.class)
    @IsApiService(isLogin = false)
    public List<ActiveInfoVo> getListBanner() {
        // 查询活动
        QueryWrapper<BannerDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_push", 'y').orderByDesc("sort");
        List<BannerDo> bannerDos = list(queryWrapper);
        List<ActiveInfoVo> activeInfoVos = baseSupport.listCopy(bannerDos, ActiveInfoVo.class);
        return activeInfoVos;
    }
}
