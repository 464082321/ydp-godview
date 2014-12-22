package com.ydp.godview.model;

import java.util.Date;

/**
 * 系统日志
 * @author zhang.hc
 *
 */
public class SysLog {
	/** 主键id **/
	private String id;
	
	/** 被请求的方法 **/
	private String reqMethod;
	
	/** 请求的参数 **/
	private String reqParam;
	
	/** 请求的url **/
	private String reqUrl;
	
	/** 返回的数据 **/
	private Object respData;
	
	/** 创建的时间 **/
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getReqParam() {
		return reqParam;
	}

	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public Object getRespData() {
		return respData;
	}

	public void setRespData(Object respData) {
		this.respData = respData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
