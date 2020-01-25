package com.hzp.demo.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@ApiModel
public class PageRequest implements Serializable {
    @Min(
            value = 1L,
            message = "每页显示条数必须大于1"
    )
    @ApiModelProperty("每页多少条记录,默认为10")
    private Integer size = 10;
    @Min(
            value = 0L,
            message = "当前页必须大于0"
    )
    @ApiModelProperty("当前页码，从0开始，默认为0")
    private Integer number = 0;
    @ApiModelProperty("排序字段非必填，默认创建时间")
    private String sort = "createTime";
}

