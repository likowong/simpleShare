package com.simple.share.taobao.impl;

import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.taobao.TaobaoService;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.properties.PropertyConfigurer;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import org.springframework.stereotype.Service;

/**
 * 淘宝客API
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  14:32
 */
@Service
public class TaobaoServiceImpl implements TaobaoService {
    /**
     * 淘宝客配置
     **/
    private String url = PropertyConfigurer.getProperty("taobao.url");
    private String appKey = PropertyConfigurer.getProperty("taobao.appkey");
    private String secret = PropertyConfigurer.getProperty("taobao.secret");
    private Long adZoneId = 110823000476L;


    @Override
    public String getMaterial(Long materialId, Long pageSize, Long pageNo) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
            req.setPageSize(pageSize);
            req.setAdzoneId(adZoneId);
            req.setPageNo(pageNo);
            req.setMaterialId(materialId);
            TbkDgOptimusMaterialResponse rsp = client.execute(req);
            return rsp.getBody();
        } catch (Exception e) {
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_DG_OPTIMUS_MATERIAL, "物料精选获取失败");
        }
    }

    @Override
    public String getActivity(String activityMaterialId) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
            req.setAdzoneId(adZoneId);
            req.setActivityMaterialId(activityMaterialId);
            TbkActivityInfoGetResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            return null;
        } catch (Exception e) {
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_ACTIVITY_INFO_GET, "活动获取失败");
        }
    }
}
