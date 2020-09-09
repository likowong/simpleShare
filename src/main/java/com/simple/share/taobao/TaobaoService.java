package com.simple.share.taobao;

import com.taobao.api.ApiException;

/**
 * 淘宝客Api服务
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-08  16:05
 */
public interface TaobaoService {

    /**
     * @return a
     * @Author: luke
     * @Description: taobao.tbk.activity.info.get(淘宝客 - 推广者 - 官方活动信息获取)
     * @Date: 2020-09-08 17:09
     * @param:str
     **/
    String getActivity(String activityMaterialId) throws ApiException;

    /**
     * @return
     * @Author: luke
     * @Description: 获取物料
     * @Date: 2020-09-09 17:39
     * @param:null
     **/
    String getMaterial(Long materialId, Long pageSize, Long pageNo);
}
