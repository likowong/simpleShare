package com.simple.share.service;

import com.simple.share.model.MaterialDo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqGoodsVo;
import com.simple.share.vo.category.ReqMaterialVo;
import com.simple.share.vo.taobao.ReqCreateTwdVo;

import java.util.List;
import java.util.Set;

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


    /**
     * @return
     * @Author: luke
     * @Description: 获取商品详情页
     * @Date: 2020-09-10 13:18
     * @param:null
     **/
    String getGoodsDetail(String goodsId);

    /**
     * @return
     * @Author: luke
     * @Description: 生成淘口令
     * @Date: 2020-09-10 16:01
     * @param:null
     **/
    String createTpw(ReqCreateTwdVo reqCreateTwdVo);


    /**
     * @return
     * @Author: luke
     * @Description: 生成淘口令
     * @Date: 2020-09-10 16:01
     * @param:null
     **/
    String createTpwWithToken(ReqCreateTwdVo reqCreateTwdVo);

    /**
     * @return
     * @Author: luke
     * @Description: 搜索商品
     * @Date: 2020-09-10 17:38
     * @param:null
     **/
    String searchGoods(ReqGoodsVo reqGoodsVo);

    /**
     * @Author: luke
     * @Description: 获取热门搜素
     * @Date:  2020-09-10 19:58
    * @param:null
     * @return 
     **/
    Set<String> getHotKey();
}
