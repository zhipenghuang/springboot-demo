package com.hzp.demo.exception;

/**
 * @author hzp
 * @create 2018/9/19.
 */
public enum SystemErrors implements Errors {
	/**
	 * 操作成功
	 */
	SUCCESS(0, "操作成功"),
	FAIL(-1, "操作失败"),
	/**
	 * 内部服务错误
	 */
	SYSTEM_ERROR(500, "内部服务错误"),
	NO_AUTHORIZATION(401, "请重新登录！"),
	NO_AUTHORIZATION_EXPIRED(401, "您的身份已过期，请重新登录！"),
	NO_AUTHORIZATION_ANOTHER_LOGING_EQUIPMENT(401, "您的账号已在另一台设备登录!"),
	/**
	 * 请求处理出现异常
	 */
	SERVLET_EXCEPTION(402, "请求处理出现异常"),
	/**
	 * 没有权限访问
	 */
	FOR_BIDDEN (403, "没有权限访问!"),
	INCORRECT_ACCOUNT_NAME_OR_PASSWORD (406, "你输入的帐户名或密码不正确!"),
	METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION (4004,"参数绑定异常！"),
	REQUEST_PARAM_ERROR(4005, "请求参数格式或绑定错误"),
	/**
	 * 信息  1000-1200
	 */
	EXISTENCE_OF_THE_SAME_NAME(1001, "已存在相同的分类名称!"),
	NEWS_NOT_EXIST(1002, "该新闻不存在!"),

	/**
	 * 系统 sys 1201 - 1400
	 */
	EXISTENCE_OF_THE_SAME_ROLE(1201, "该角色已存在！"),
	EXISTENCE_OF_THE_SAME_USER(1202, "该号码已注册！"),
	NULL_OF_ROLE(1203, "角色不能为空！"),
	USED_OF_ROLE(1204, "该角色正在被使用！"),
			;
	;


	public int code;
	public String message;

	SystemErrors(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


	public static String getMsg(int code) {
		for (SystemErrors errorCode : SystemErrors.values()) {
			if (code == errorCode.getCode()) {
				return errorCode.getMessage();
			}
		}
		return null;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String msg) {
		this.message = msg;
	}
}
