package com.simple.share.service;

import com.simple.share.model.MaterialDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqMaterialVo;

import java.util.List;

/**
 * <p>
 * 类目表 服务类
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
public interface MaterialService extends IService<MaterialDo> {
    /**
     * @return CategoryVo
     * @Author: luke
     * @Description: 获取专题类目列表
     * @Date: 2020-09-09 16:51
     **/
    List<CategoryVo> getValidListCategoryVo();

    /**
     * @return CategoryVo
     * @Author: luke
     * @Description: 获取类目列表
     * @Date: 2020-09-09 16:51
     **/
    List<CategoryVo> getListCategoryVo();

    /**
     * @return String
     * @Author: luke
     * @Description: 获取精选商品
     * @Date: 2020-09-09 17:35
     * @param:reqMaterialVo
     **/
    String getListMaterial(ReqMaterialVo reqMaterialVo);
}
