package com.simple.share.vo.taobao;

import com.spring.simple.development.core.component.mvc.page.ReqPageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 订单查询对象
 *
 * @author luke
 * @email 15010343670@163.com
 * @date 2020-09-16  17:27
 */
@Data
@ApiModel(value = "orderVo", description = "订单查询对象")
public class OrderVo extends ReqPageDTO {
    /**
     * 类目Id
     **/
    @ApiModelProperty(value = "订单状态", example = "12")
    private String orderStatus;
}
