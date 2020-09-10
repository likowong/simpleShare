package com.simple.share;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
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
    @Test
    public void test4() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setUserId("123");
        req.setText("长度大于5个字符");
        req.setUrl("https://s.click.taobao.com/t?e=m%3D2%26s%3DAxCJaWTSeIZw4vFB6t2Z2ueEDrYVVa64Dne87AjQPk9yINtkUhsv0KF59RMBa4vSU70RzSpzBNdjbLhZS%2BRfCoB4U5jY9%2BEZBiBPVre%2FhtYkZAozc%2BRbKZWCLkleKehKUyNpxLfgKr0jWpzpm6nEC7u7m09eNMONFrjU9UGf0rJcfm37xb4PJRLvbryQoUVfN6AMIIz9%2BKDjNQ%2FKbdQu23EqY%2Bakgpmw&scm=1007.19011.125585.0_13366&pvid=17f2c08a-b18d-4efe-af5b-d6052d4c2c4e&app_pvid=59590_11.132.118.119_978_1599674425454&ptl=floorId:13366;originalFloorId:13366;pvid:17f2c08a-b18d-4efe-af5b-d6052d4c2c4e;app_pvid:59590_11.132.118.119_978_1599674425454&union_lens=lensId%3AMAPI%401599674425%400b847677_b0e9_1747406a098_76a6%4001");
        req.setLogo("https://uland.taobao.com/");
        req.setExt("{}");
        TbkTpwdCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
}
