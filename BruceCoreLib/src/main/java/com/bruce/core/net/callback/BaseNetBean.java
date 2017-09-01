package com.bruce.core.net.callback;

import java.io.Serializable;

/**
 * @author lt
 *
 */
public class BaseNetBean implements Serializable{
	//data：返回结果，code为0时，如果有返回值，则通过data返回。
	
	/**
	 * 结果码  0成功
	 */
	private int code;
	/**
	 * 错误详细信息，用于打印日志，定位等（code不为0时，必选）
	 */
	private String message;
	/**
	 * 结错误描述，可直接用于显示给客户的错误描述（可选）
	 */
	private String desc;
	/**
	 * 多级错误描述，用于复杂异常
	 */
	private String errors;
	/**
	 * 返回结果
	 */
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}

}
