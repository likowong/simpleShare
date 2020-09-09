package com.simple.share.task;

import com.alibaba.fastjson.JSONObject;
import com.simple.share.service.MaterialService;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.vo.category.CategoryVo;
import com.simple.share.vo.category.ReqMaterialVo;
import com.spring.simple.development.support.utils.JedisPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * TODO
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  20:32
 */
@Configuration
@EnableScheduling
public class GoodsTask {
    @Autowired
    private TaobaoService taobaoService;
    @Autowired
    private MaterialService materialService;

    /**
     * @return
     * @Author: luke
     * @Description: 凌晨2点执行一次
     * @Date: 2020-09-09 20:37
     * @param:null
     **/
    @Scheduled(cron = "0 0 2 * * ?")
    public void refreshGoods() {
        List<CategoryVo> listCategoryVo = materialService.getListCategoryVo();
        if (CollectionUtils.isEmpty(listCategoryVo)) {
            return;
        }
        for (CategoryVo categoryVo : listCategoryVo) {
            Jedis jedis = null;
            try {
                ReqMaterialVo reqMaterialVo = new ReqMaterialVo();
                reqMaterialVo.setMaterialId(Long.valueOf(categoryVo.getMaterialId()));
                reqMaterialVo.setPageSize(10L);
                for (long i = 1; i < 20; i++) {
                    reqMaterialVo.setPageNo(i);
                    jedis = JedisPoolUtils.getJedis();
                    String materialData = taobaoService.getMaterial(reqMaterialVo.getMaterialId(), reqMaterialVo.getPageSize(), reqMaterialVo.getPageNo());
                    jedis.setex(JSONObject.toJSONString(reqMaterialVo), 60 * 60 * 24, materialData);
                }
            } finally {
                JedisPoolUtils.returnRes(jedis);
            }
        }
    }
}
