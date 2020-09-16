package com.simple.share.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品收藏
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_member_collection")
public class MemberCollectionDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 商品图片
     */
    @TableField("goodsImg")
    private String goodsImg;

    /**
     * 商品分享地址
     */
    private String goodsImgShareUrl;

    /**
     * 商品Id
     */
    @TableField("goodsId")
    private String goodsId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 优惠券
     */
    private String couponAmount;


}
