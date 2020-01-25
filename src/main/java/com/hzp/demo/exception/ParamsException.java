package com.hzp.demo.exception;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public class ParamsException extends BaseException {


	public ParamsException() {
		super();
	}

	public ParamsException(Errors error, String msg) {
		super(error, msg);
	}

	public ParamsException(Errors error) {
		super(error);
	}


}
