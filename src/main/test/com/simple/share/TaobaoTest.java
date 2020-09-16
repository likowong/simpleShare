package com.simple.share;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.junit.Test;

public class TaobaoTest {
    String url = "https://eco.taobao.com/router/rest";
    String appKey = "31238408";
    String secret = "07fb32b10dd0ec573f72544743de57a6";
    private Long adZoneId = 110823000476L;
    String sessionKey = "6100a17e317f619260581106b11e0fdb664a4642335216a828324030";

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
        req.setAdzoneId(adZoneId);
        req.setNeedFreeShipment(true);
        req.setNeedPrepay(true);
        req.setIncludePayRate30(true);
        req.setIncludeGoodRate(true);
        req.setIncludeRfdRate(true);
        req.setRelationId("2602382436");
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
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
        req.setUserId("2602382436");
        req.setText("长度大于5个字符");
        req.setUrl("https://mos.m.taobao.com/inviter/register?inviterCode=MQL8W5&src=pub&app=common&rtag=%E5%B0%8F%E8%8B%B9%E6%9E%9C");
        req.setLogo("https://uland.taobao.com/");
        req.setExt("{}");
        TbkTpwdCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    @Test
    public void test5() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();
        req.setPositionIndex("2222_334666");
        req.setPageSize(20L);
        req.setEndTime("2020-09-15 20:00:00");
        req.setStartTime("2020-09-15 19:00:00");
        req.setJumpType(1L);
        req.setPageNo(1L);
        req.setOrderScene(2L);
        req.setTkStatus(12L);
        TbkOrderDetailsGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    @Test
    public void test6() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkScInvitecodeGetRequest req = new TbkScInvitecodeGetRequest();
        req.setRelationApp("common");
        req.setCodeType(1L);
        TbkScInvitecodeGetResponse rsp = client.execute(req, "6100a17e317f619260581106b11e0fdb664a4642335216a828324030");
        System.out.println(rsp.getBody());
        // https://mos.m.taobao.com/inviter/register?inviterCode=MQL8W5&src=pub&app=common&rtag=小苹果
    }

    @Test
    public void test7() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkScPublisherInfoSaveRequest req = new TbkScPublisherInfoSaveRequest();
        req.setRelationFrom("1");
        req.setOfflineScene("1");
        req.setOnlineScene("1");
        req.setInviterCode("MQL8W5");
        req.setInfoType(1L);
        req.setNote("小蜜蜂");
        TbkScPublisherInfoSaveResponse rsp = client.execute(req, sessionKey);
        System.out.println(rsp.getBody());
    }

    @Test
    public void test8() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
        TbkScPublisherInfoGetRequest req = new TbkScPublisherInfoGetRequest();
        req.setInfoType(1L);
        req.setPageNo(1L);
        req.setPageSize(10L);
        req.setRelationApp("common");
        TbkScPublisherInfoGetResponse rsp = client.execute(req, sessionKey);
        System.out.println(rsp.getBody());
    }
}
