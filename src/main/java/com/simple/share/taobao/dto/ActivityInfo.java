package com.simple.share.taobao.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 淘宝客活动
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-09  14:55
 */
@Data
public class ActivityInfo {

    /**
     * wx_qrcode_url : https://img.alicdn.com/xxx
     * click_url : https://s.click.xx.xx/xxx?xx
     * short_click_url : https://s.click.xx.xx/xxx?xx
     * terminal_type : 1
     * material_oss_url : http://xxx.xxx.xxx
     * page_name : 活动会场A
     * page_start_time : 2020-02-27
     * page_end_time : 2020-02-27
     * wx_miniprogram_path : pages/sharePid/web/index?scene=https://xxx
     */
    @JSONField(name = "wx_qrcode_url")
    private String wxQrcodeUrl;
    @JSONField(name = "click_url")
    private String clickUrl;
    @JSONField(name = "short_click_url")
    private String shortClickUrl;
    @JSONField(name = "terminal_type")
    private String terminalType;
    @JSONField(name = "material_oss_url")
    private String materialOssUrl;
    @JSONField(name = "page_name")
    private String pageName;
    @JSONField(name = "page_start_time")
    private String pageStartTime;
    @JSONField(name = "page_end_time")
    private String pageEndTime;
    @JSONField(name = "wx_miniprogram_path")
    private String wxMiniprogramPath;
}
