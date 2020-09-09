package com.simple.share.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author luke
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_material")
public class MaterialDo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物料Id
     */
    private String materialId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否上架
     */
    private String isPush;

    /**
     * 类目跳转地址
     */
    private String materialUrl;

    /**
     * icon地址
     */
    private String iconUrl;


}
