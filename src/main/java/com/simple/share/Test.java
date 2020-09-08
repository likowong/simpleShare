package com.simple.share;

import com.spring.simple.development.support.properties.PropertyConfigurer;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;

public class Test {

    public static void main(String[] args) throws ApiException {
        /**
         * @Author: luke
         * @Description: //TODO
         * @Date: 2020-09-08 17:09
         * @param:args
         * @return void
         **/
        //String url = PropertyConfigurer.getProperty("taobao.url");
        //String appkey = PropertyConfigurer.getProperty("taobao.appkey");
        //String secret = PropertyConfigurer.getProperty("taobao.secret");
        String url = "https://eco.taobao.com/router/rest";
        String appkey = "31238408";
        String secret = "07fb32b10dd0ec573f72544743de57a6";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
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
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
