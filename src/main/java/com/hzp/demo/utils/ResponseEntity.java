package com.hzp.demo.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.hzp.demo.exception.Errors;
import com.hzp.demo.exception.SystemErrors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@Data
@EqualsAndHashCode
@ToString
@ApiModel
public class ResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 返回数据编码，0成功 其他为失败
	 */
	@ApiModelProperty("回数据编码，0成功 其他为失败")
	@JSONField(ordinal = 1)
	private int ecode = 0;
	/**
	 * 请求结果信息提示
	 */
	@ApiModelProperty("请求结果信息提示")
	@JSONField(ordinal = 2)
	private String message;
	/**
	 * 时间戳
	 */
	@ApiModelProperty("时间戳")
	@JSONField(ordinal = 3)
	private long ts;
	/**
	 * 返回请求数据{json}对象
	 */
	@ApiModelProperty("返回请求数据{json}对象")
	@JSONField(ordinal = 4)
	private T data;

	public ResponseEntity() {
		this.ecode = SystemErrors.SUCCESS.code;
		this.message = SystemErrors.SUCCESS.message;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(Errors errors) {
		this.ecode = errors.getCode();
		this.message = errors.getMessage();
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(int ecode, String message) {
		this.ecode = ecode;
		this.message = message;
		this.ts = System.currentTimeMillis();
	}

	public ResponseEntity(T t) {
		this.ecode = SystemErrors.SUCCESS.code;
		this.message = SystemErrors.SUCCESS.message;
		this.data = t;
		this.ts = System.currentTimeMillis();
	}
}
