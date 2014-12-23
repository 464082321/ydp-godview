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

	/** 这个id只是为了对RefLog之间进行区分 **/
	private String id;

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
	private String serviceId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getReqParam() {
		return reqParams;
	}

	public void setReqParam(String reqParams) {
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RefLogDto other = (RefLogDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
