package com.simple.share.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * banner活动表
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_banner")
public class BannerDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @TableField("activeName")
    private String activeName;

    /**
     * y/n
     */
    private String isPush;

    /**
     * banner页面地址
     */
    private String bannerUrl;

    /**
     * 活动地址
     */
    private String url;

    /**
     * 排序
     */
    private Integer sort;


}
