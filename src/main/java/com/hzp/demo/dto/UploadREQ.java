package com.hzp.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UploadREQ {

    @ApiModelProperty("分片总数")
    private int chunkTotal;
}
