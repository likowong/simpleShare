package com.simple.share.taobao;

import com.simple.share.model.TaobaoOrderDo;
import com.simple.share.taobao.dto.InviterInfo;
import com.simple.share.taobao.dto.InviterMemberInfo;
import com.taobao.api.ApiException;

import java.util.List;

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

    /**
     * @return String
     * @Author: luke
     * @Description: 通过商品Id查询商品
     * @Date: 2020-09-10 13:12
     * @param:goodsId
     **/
    String getGoodsDetail(String goodsId);

    /**
     * @return
     * @Author: luke
     * @Description: 生成淘口令
     * @Date: 2020-09-10 15:52
     * @param:null
     **/
    String createTpw(String text, String url);

    /**
     * @return
     * @Author: luke
     * @Description: 搜索商品
     * @Date: 2020-09-10 17:32
     * @param:null
     **/
    String searchGoods(String text, String sort, Long pageSize, Long pageNo);

    /**
     * @return
     * @Author: luke
     * @Description: 生成邀请的淘口令
     * @Date: 2020-09-16 12:25
     * @param:null
     **/
    InviterInfo createInviterTbCode(String openId);

    /**
     * @return
     * @Author: luke
     * @Description: 获取备案用户信息
     * @Date: 2020-09-16 13:09
     * @param:null
     **/
    InviterMemberInfo getInviterMemberInfo(String openId);

    /**
     * @return
     * @Author: luke
     * @Description: 查询淘宝订单
     * @Date: 2020-09-16 15:50
     * @param:null
     **/
    List<TaobaoOrderDo> getTaobaoOrderDoList(String startTime, String endTime);
}
