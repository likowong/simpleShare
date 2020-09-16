package com.simple.share.vo.collection;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品收藏
 * </p>
 *
 * @author luke
 * @since 2020-09-16
 */
@Data
@ApiModel(value = "memberCollectionVo", description = "商品收藏对象")
public class MemberCollectionVo {
    /**
     * 商品图片
     */
    @NotNull(message = "商品图片为空")
    @ApiModelProperty(value = "商品图片", example = "12")
    private String goodsImg;

    /**
     * 商品分享地址
     */
    @ApiModelProperty(value = "商品分享地址", example = "12")
    @NotNull(message = "商品分享地址")
    private String goodsImgShareUrl;

    /**
     * 商品Id
     */
    @NotNull(message = "商品Id为空")
    @ApiModelProperty(value = "商品Id", example = "12")
    private String goodsId;

    /**
     * 商品标题
     */
    @NotNull(message = "商品标题为空")
    @ApiModelProperty(value = "商品标题", example = "12")
    private String goodsTitle;

    /**
     * 优惠券
     */
    @NotNull(message = "优惠券为空")
    @ApiModelProperty(value = "优惠券", example = "12")
    private String couponAmount;


}
