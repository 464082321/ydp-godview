package com.ydp.godview.model;

import java.io.Serializable;

/**
 * 存储调用ydp-api以外的dubbo服务信息
 * 
 * @author he.f1
 */
public class RefLogDto implements Serializable {

	/****/
	private static final long serialVersionUID = -3618105907187999838L;

	/** 主键Id **/
	private int id;

	/** 请求的接口名称 **/
	private String interfaceName;

	/** 被请求的方法 **/
	private String reqMethod;

	/** 请求的参数 **/
	private String reqParams;

	/** 返回的数据 **/
	private String respData;

	/** 请求的时长 **/
	private Long accessPeriod;

	/** tbl_service_log的外键 **/
	private int serviceId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getReqMethod() {
		return reqMethod;
	}

	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getReqParams() {
		return reqParams;
	}

	public void setReqParams(String reqParams) {
		this.reqParams = reqParams;
	}

	public String getRespData() {
		return respData;
	}

	public void setRespData(String respData) {
		this.respData = respData;
	}

	public Long getAccessPeriod() {
		return accessPeriod;
	}

	public void setAccessPeriod(Long accessPeriod) {
		this.accessPeriod = accessPeriod;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
}
