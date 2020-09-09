package com.simple.share.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.share.model.BannerDo;
import com.simple.share.model.MaterialDo;
import com.simple.share.mapper.MaterialMapper;
import com.simple.share.service.MaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.vo.active.ActiveInfoVo;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqMaterialVo;
import com.spring.simple.development.core.annotation.base.IsApiService;
import com.spring.simple.development.core.annotation.base.ValidHandler;
import com.spring.simple.development.core.annotation.base.swagger.Api;
import com.spring.simple.development.core.annotation.base.swagger.ApiImplicitParam;
import com.spring.simple.development.core.annotation.base.swagger.ApiOperation;
import com.spring.simple.development.core.component.mvc.BaseSupport;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

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
                JSONObject jsonObject = JSON.parseObject(materialData);
                String responseData = jsonObject.getString("tbk_dg_optimus_material_response");
                if (StringUtils.isEmpty(responseData)) {

                }
                jedis.setex(JSONObject.toJSONString(reqMaterialVo), 60 * 60 * 24, materialData);
                return materialData;
            }
            return cacheData;
        } finally {
            JedisPoolUtils.returnRes(jedis);
        }
    }
}
