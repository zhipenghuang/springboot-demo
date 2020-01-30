package com.hzp.demo.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SequentialFile implements Comparable<SequentialFile> {

    public SequentialFile(int index, String fileName) {
        this.index = index;
        this.fileName = fileName;
    }

    @ApiModelProperty("index")
    private int index;

    @ApiModelProperty("fileName")
    private String fileName;

    @Override
    public int compareTo(SequentialFile o) {
        if (this.index > o.index) {
            return -1;
        }
        return 1;
    }
}
