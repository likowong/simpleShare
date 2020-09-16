package com.simple.share.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.simple.share.interceptor.MemberInfo;
import com.simple.share.interceptor.MemberThreadLocal;
import com.simple.share.model.MemberCollectionDo;
import com.simple.share.mapper.MemberCollectionMapper;
import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.service.MemberCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.collection.MemberCollectionVo;
import com.simple.share.vo.wexin.WeixinLoginVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import com.spring.simple.development.core.component.mvc.page.ReqPageDTO;
import com.spring.simple.development.core.component.mvc.page.ResPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 商品收藏 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Api(tags = "商品收藏")
@Service
public class MemberCollectionServiceImpl extends ServiceImpl<MemberCollectionMapper, MemberCollectionDo> implements MemberCollectionService {
    @Autowired
    private BaseSupport baseSupport;

    @Override
    @ApiOperation(value = "增加商品收藏")
    @IsApiService
    @ValidHandler(key = "memberCollectionVo", value = MemberCollectionVo.class)
    public void addCollection(MemberCollectionVo memberCollectionVo) {
        MemberCollectionDo memberCollectionDo = baseSupport.objectCopy(memberCollectionVo, MemberCollectionDo.class);
        MemberInfo memberInfo = MemberThreadLocal.getMemberInfo();
        String wxOpenId = memberInfo.getWxOpenId();
        memberCollectionDo.setCreateTime(new Date());
        memberCollectionDo.setOpenId(wxOpenId);
        save(memberCollectionDo);
    }

    @Override
    @ApiOperation(value = "获取商品收藏")
    @ApiImplicitParam(name = "resPageDTO", description = "获取商品收藏对象", resultDataType = ResPageDTO.class)
    @IsApiService
    @ValidHandler(key = "reqPageDTO", value = ReqPageDTO.class)
    public ResPageDTO getPageMemberCollectionDo(ReqPageDTO reqPageDTO) {
        QueryWrapper<MemberCollectionDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");

        IPage<MemberCollectionDo> page = new Page<>(reqPageDTO.getStartPage(), reqPageDTO.getPageSize());
        IPage<MemberCollectionDo> memberCollectionDoIPage = this.baseMapper.selectPage(page, queryWrapper);
        ResPageDTO resPageDTO = new ResPageDTO();
        resPageDTO.setList(memberCollectionDoIPage.getRecords());
        resPageDTO.setPageSize((int) memberCollectionDoIPage.getSize());
        resPageDTO.setPageNum((int) memberCollectionDoIPage.getCurrent());
        resPageDTO.setTotalCount(memberCollectionDoIPage.getPages());
        return resPageDTO;
    }
}
