package com.simple.share.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.interceptor.MemberInfo;
import com.simple.share.model.BannerDo;
import com.simple.share.model.MaterialDo;
import com.simple.share.mapper.MaterialMapper;
import com.simple.share.model.MemberDo;
import com.simple.share.service.MaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.service.MemberTaobaoService;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.vo.active.ActiveInfoVo;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqGoodsVo;
import com.simple.share.vo.category.ReqMaterialVo;
import com.simple.share.vo.taobao.ReqCreateTwdVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.exception.ResponseCode;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
@Api(tags = "类目相关")
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialDo> implements MaterialService {
    @Autowired
    private BaseSupport baseSupport;

    @Autowired
    private TaobaoService taobaoService;
    @Autowired
    private MemberTaobaoService memberTaobaoService;

    @Override
    @ApiOperation(value = "获取专题物料列表")
    @ApiImplicitParam(name = "categoryVo", description = "类目对象", resultDataType = CategoryVo.class)
    @IsApiService(isLogin = false)
    public List<CategoryVo> getValidListCategoryVo() {
        // 类目相关
        QueryWrapper<MaterialDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_push", 'y').orderByDesc("sort");
        List<MaterialDo> bannerDos = list(queryWrapper);
        List<CategoryVo> categoryVos = baseSupport.listCopy(bannerDos, CategoryVo.class);
        return categoryVos;
    }

    @Override
    @ApiOperation(value = "获取物料列表")
    @ApiImplicitParam(name = "categoryVo", description = "类目对象", resultDataType = CategoryVo.class)
    @IsApiService(isLogin = false)
    public List<CategoryVo> getListCategoryVo() {
        // 类目相关
        QueryWrapper<MaterialDo> queryWrapper = new QueryWrapper<>();
        List<MaterialDo> bannerDos = list(queryWrapper);
        List<CategoryVo> categoryVos = baseSupport.listCopy(bannerDos, CategoryVo.class);
        return categoryVos;
    }

    @Override
    @ApiOperation(value = "通过物料Id获取物料")
    @ApiImplicitParam(name = "json", description = "物料对象", resultDataType = String.class)
    @IsApiService(isLogin = false)
    @ValidHandler(key = "reqMaterialVo", value = ReqMaterialVo.class)
    public String getListMaterial(ReqMaterialVo reqMaterialVo) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            String cacheData = jedis.get(JSONObject.toJSONString(reqMaterialVo));
            if (StringUtils.isEmpty(cacheData)) {
                String materialData = taobaoService.getMaterial(reqMaterialVo.getMaterialId(), reqMaterialVo.getPageSize(), reqMaterialVo.getPageNo());
                jedis.setex(JSONObject.toJSONString(reqMaterialVo), 60 * 60 * 24, materialData);
                return materialData;
            }
            return cacheData;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }

    @Override
    @ApiOperation(value = "通过商品Id获取商品详情页")
    @ApiImplicitParam(name = "string", description = "商品Id", resultDataType = String.class)
    @IsApiService(isLogin = false)
    public String getGoodsDetail(String goodsId) {
        if (StringUtils.isEmpty(goodsId)) {
            throw new GlobalException(ResponseCode.RES_PARAM_IS_EMPTY, "商品Id为空");
        }
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            String cacheData = jedis.get(goodsId);
            if (StringUtils.isEmpty(cacheData)) {
                String goodsData = taobaoService.getGoodsDetail(goodsId);
                jedis.setex(goodsId, 60 * 60 * 24, goodsData);
                return goodsData;
            }
            return cacheData;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }

    @Override
    @ApiOperation(value = "生成淘口令")
    @ApiImplicitParam(name = "string", description = "商品Id", resultDataType = String.class)
    @IsApiService(isLogin = false)
    @ValidHandler(key = "reqCreateTwdVo", value = ReqCreateTwdVo.class)
    public String createTpw(ReqCreateTwdVo reqCreateTwdVo) {
        return taobaoService.createTpw(reqCreateTwdVo.getText(), reqCreateTwdVo.getUrl());
    }

    @Override
    @ApiOperation(value = "已登录的用户生成淘口令")
    @ApiImplicitParam(name = "string", description = "商品Id", resultDataType = String.class)
    @IsApiService
    @ValidHandler(key = "createTpwWithToken", value = ReqCreateTwdVo.class)
    public String createTpwWithToken(ReqCreateTwdVo reqCreateTwdVo) {
        String relationId = memberTaobaoService.getRelationId();
        if (StringUtils.isEmpty(relationId)) {
            return taobaoService.createTpw(reqCreateTwdVo.getText(), reqCreateTwdVo.getUrl());
        }
        return taobaoService.createTpw(reqCreateTwdVo.getText(), reqCreateTwdVo.getUrl() + "&relationId=" + relationId);
    }

    @Override
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParam(name = "json", description = "物料对象", resultDataType = String.class)
    @IsApiService(isLogin = false)
    @ValidHandler(key = "reqMaterialVo", value = ReqGoodsVo.class)
    public String searchGoods(ReqGoodsVo reqGoodsVo) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            Double goodsHotSource = jedis.zscore("goodsHot", reqGoodsVo.getText());
            if (goodsHotSource == null) {
                jedis.zadd("goodsHot", 1, reqGoodsVo.getText());
            } else {
                jedis.zadd("goodsHot", goodsHotSource + 1, reqGoodsVo.getText());
            }
            String cacheData = jedis.get(JSONObject.toJSONString(reqGoodsVo));
            if (StringUtils.isEmpty(cacheData)) {
                String materialData = taobaoService.searchGoods(reqGoodsVo.getText(), reqGoodsVo.getSort(), reqGoodsVo.getPageSize(), reqGoodsVo.getPageNo());
                jedis.setex(JSONObject.toJSONString(reqGoodsVo), 60 * 60 * 24, materialData);
                return materialData;
            }
            return cacheData;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }

    @Override
    @ApiOperation(value = "搜索热门")
    @ApiImplicitParam(name = "json", description = "搜索热门", resultDataType = Set.class)
    @IsApiService(isLogin = false)
    public Set<String> getHotKey() {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtils.getJedis();
            Set<String> goodsHot = jedis.zrevrange("goodsHot", 0, 5);
            return goodsHot;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }
}
