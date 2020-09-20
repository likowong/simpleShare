package com.simple.share.taobao.impl;

import com.alibaba.fastjson.JSONObject;
import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.support.SimpleShareResponseCode;
import com.simple.share.taobao.TaobaoService;
import com.simple.share.taobao.dto.InviterInfo;
import com.simple.share.taobao.dto.InviterMemberInfo;
import com.spring.simple.development.core.component.mvc.controller.ApiController;
import com.spring.simple.development.support.exception.GlobalException;
import com.spring.simple.development.support.properties.PropertyConfigurer;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 淘宝客API
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  14:32
 */
@Service
public class TaobaoServiceImpl implements TaobaoService {
    private static final Logger logger = LogManager.getLogger(TaobaoServiceImpl.class);

    /**
     * 淘宝客配置
     **/
    private String url = PropertyConfigurer.getProperty("taobao.url");
    private String appKey = PropertyConfigurer.getProperty("taobao.appkey");
    private String secret = PropertyConfigurer.getProperty("taobao.secret");
    private Long adZoneId = 110823000476L;
    private String sessionKey = PropertyConfigurer.getProperty("taobao.sessionKey");
    private String channelInviterUrl = "https://mos.m.taobao.com/inviter/register";


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
            logger.error("物料精选获取失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_DG_OPTIMUS_MATERIAL, "物料精选获取失败");
        }
    }

    @Override
    public String getGoodsDetail(String goodsId) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
            req.setNumIids(goodsId);
            req.setPlatform(2L);
            TbkItemInfoGetResponse rsp = client.execute(req);
            return rsp.getBody();
        } catch (Exception e) {
            logger.error("获取详情失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_ITEM_INFO_GET, "获取详情失败");
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
            logger.error("活动获取失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_ACTIVITY_INFO_GET, "活动获取失败");
        }
    }

    @Override
    public String createTpw(String text, String twdUrl) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setText(text);
            req.setUrl(twdUrl);
            TbkTpwdCreateResponse rsp = client.execute(req);
            return rsp.getBody();
        } catch (Exception e) {
            logger.error("生成淘口令失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_TPWD_CREATE, "生成淘口令失败");
        }
    }

    @Override
    public String searchGoods(String text, String sort, Long pageSize, Long pageNo) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            req.setQ(text);
            req.setSort(sort);
            req.setPlatform(2L);
            req.setAdzoneId(adZoneId);
            req.setPageSize(pageSize);
            req.setRelationId("2602382436");
            req.setHasCoupon(true);
            req.setNeedFreeShipment(true);
            req.setNeedPrepay(true);
            req.setPageNo(pageNo);
            req.setIsOverseas(false);
            req.setIncludeGoodRate(true);
            req.setLockRateEndTime(System.currentTimeMillis());
            TbkDgMaterialOptionalResponse rsp = client.execute(req);
            return rsp.getBody();
        } catch (Exception e) {
            logger.error("搜索商品失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_DG_MATERIAL_OPTIONAL, "搜索商品失败");
        }
    }

    @Override
    public InviterInfo createInviterTbCode(String openId) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkScInvitecodeGetRequest req = new TbkScInvitecodeGetRequest();
            req.setRelationApp("common");
            req.setCodeType(1L);
            TbkScInvitecodeGetResponse rsp = client.execute(req, sessionKey);
            JSONObject jsonObject = JSONObject.parseObject(rsp.getBody());
            String data = jsonObject.getString("tbk_sc_invitecode_get_response");
            if (StringUtils.isEmpty(data)) {
                logger.error("生成备案口令失败:", rsp.getBody());
                throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_INVITECODE_GET, "生成备案口令失败");
            }
            JSONObject dataJsonObject = JSONObject.parseObject(data);
            String inviterData = dataJsonObject.getString("data");
            JSONObject inviterDataJsonObject = JSONObject.parseObject(inviterData);
            String inviterCode = inviterDataJsonObject.getString("inviter_code");

            String inviterUrl = channelInviterUrl + "?inviterCode=" + inviterCode + "&src=pub&app=common&rtag=" + openId;
            InviterInfo inviterInfo = new InviterInfo();
            inviterInfo.setOpenId(openId);
            inviterInfo.setInviterCode(inviterCode);
            inviterInfo.setInviterUrl(inviterUrl);
            // 生成淘口令
            String createTwd = createTpw("备案信息", inviterUrl);
            JSONObject createTwdJsonObject = JSONObject.parseObject(createTwd);

            String createTwdTpwData = createTwdJsonObject.getString("tbk_tpwd_create_response");
            if (StringUtils.isEmpty(createTwdTpwData)) {
                logger.error("生成备案口令失败:", rsp.getBody());
                throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_INVITECODE_GET, "生成备案口令失败");
            }
            JSONObject tpwDataJsonObject = JSONObject.parseObject(createTwdTpwData);
            String twdData = JSONObject.parseObject(tpwDataJsonObject.getString("data")).getString("model");
            inviterInfo.setTwdCode(twdData);

            return inviterInfo;
        } catch (Exception e) {
            logger.error("生成备案口令失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_INVITECODE_GET, "生成备案口令失败");
        }
    }

    @Override
    public InviterMemberInfo getInviterMemberInfo(String openId) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkScPublisherInfoGetRequest req = new TbkScPublisherInfoGetRequest();
            req.setInfoType(1L);
            req.setPageNo(1L);
            req.setPageSize(50L);
            req.setRelationApp("common");
            TbkScPublisherInfoGetResponse rsp = client.execute(req, sessionKey);
            JSONObject jsonObject = JSONObject.parseObject(rsp.getBody());
            String data = jsonObject.getString("tbk_sc_publisher_info_get_response");
            if (StringUtils.isEmpty(data)) {
                logger.error("私域用户备案信息查询失败:", rsp.getBody());
                throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_INVITECODE_GET, "私域用户备案信息查询失败");
            }
            JSONObject dataJsonObject = JSONObject.parseObject(data);
            String inviterList = JSONObject.parseObject(JSONObject.parseObject(dataJsonObject.getString("data")).getString("inviter_list")).getString("map_data");
            List<InviterMemberInfo> inviterMemberInfos = JSONObject.parseArray(inviterList, InviterMemberInfo.class);
            if (CollectionUtils.isEmpty(inviterMemberInfos)) {
                return null;
            }
            List<InviterMemberInfo> onlyInviterMemberInfo = inviterMemberInfos.stream().filter(InviterMemberInfo -> openId.equals(InviterMemberInfo.getRtag())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(onlyInviterMemberInfo)) {
                return null;
            }
            InviterMemberInfo inviterMemberInfo = onlyInviterMemberInfo.get(0);
            return inviterMemberInfo;
        } catch (Exception e) {
            logger.error("私域用户备案信息查询失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_SC_PUBLISHER_INFO_GET, "私域用户备案信息查询失败");
        }
    }

    @Override
    public List<TaobaoOrderDo> getTaobaoOrderDoList(String startTime, String endTime) {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
            TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();
            req.setPositionIndex("2222_334666");
            req.setPageSize(20L);
            req.setEndTime(endTime);
            req.setStartTime(startTime);
            req.setJumpType(1L);
            req.setPageNo(1L);
            req.setOrderScene(1L);
            req.setTkStatus(12L);
            TbkOrderDetailsGetResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            JSONObject jsonObject = JSONObject.parseObject(rsp.getBody());
            String orderData = jsonObject.getString("tbk_order_details_get_response");
            if (StringUtils.isEmpty(orderData)) {
                return null;
            }
            String orderJson = JSONObject.parseObject(JSONObject.parseObject(JSONObject.parseObject(orderData).getString("data")).getString("results")).getString("publisher_order_dto");
            if (StringUtils.isEmpty(orderJson)) {
                return null;
            }
            List<TaobaoOrderDo> taobaoOrderDos = JSONObject.parseArray(orderJson, TaobaoOrderDo.class);
            return taobaoOrderDos;
        } catch (Exception e) {
            logger.error("订单查询 查询失败", e);
            throw new GlobalException(SimpleShareResponseCode.RES_TAOBAO_TBK_ORDER_DETAILS_GET, "订单查询 查询失败");
        }
    }
}
