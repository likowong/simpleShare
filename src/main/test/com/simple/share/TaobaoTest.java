package com.simple.share;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import org.junit.Test;

public class TaobaoTest {
    String url = "https://eco.taobao.com/router/rest";
    String appKey = "31238408";
    String secret = "07fb32b10dd0ec573f72544743de57a6";
    private Long adZoneId = 110823000476L;

    /**
     * @return
     * @Author: luke
     * @Description: 搜索
     * @Date: 2020-09-09 15:07
     * @param:null
     **/
    @Test
    public void test() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPlatform(2L);
        req.setSort("total_sales");
        req.setCat("16,18");
        req.setQ("女装");
        req.setMaterialId(2836L);
        req.setHasCoupon(true);
        req.setAdzoneId(110823000476L);
        req.setNeedFreeShipment(true);
        req.setNeedPrepay(true);
        req.setIncludePayRate30(true);
        req.setIncludeGoodRate(true);
        req.setIncludeRfdRate(true);
        TbkDgMaterialOptionalResponse rsp =client.execute(req);
        System.out.println(rsp.getBody());
    }

    /**
     * @return
     * @Author: luke
     * @Description: 官方活动
     * @Date: 2020-09-09 15:07
     * @param:null
     **/
    @Test
    public void test2() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(adZoneId);
        req.setActivityMaterialId("20150318020000184");
        TbkActivityInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    @Test
    public void test3() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(1L);
        req.setAdzoneId(adZoneId);
        req.setPageNo(1L);
        req.setMaterialId(32366L);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
