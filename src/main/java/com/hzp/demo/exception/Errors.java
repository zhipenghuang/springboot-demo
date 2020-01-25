package com.hzp.demo.exception;

/**
 * @author hzp 错误接口
 * @create 2018/9/19.
 */
public interface Errors {

	/**
	 * 返回码
	 */
	int getCode();

	/**
	 * 错误信息
	 */
	String getMessage();

	void setMessage(String msg);

}
