package com.ydp.godview.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 日志管理dto，存储ydp-api的请求数据
 * 
 * @author he.f1
 */
public class ServiceLogDto implements Serializable{

	/****/
	private static final long serialVersionUID = 7753173321109233886L;
	
	/** 请求的接口名称 **/
	private String interfaceName;
	
	/** 被请求的方法 **/
	private String reqMethod;
	
	/** 请求的参数 **/
	private String reqParams;
	
	/** 请求的ip **/
	private String reqIp;
	
	/** 返回的数据 **/
	private String respData;
	
	/** 异常信息 **/
	private String errMsg;
	
	/** 请求的时长 **/
	private Long accessPeriod;
	
	/** 接口内请求其他第三方接口的对象信息集合 **/
	private Set<RefLogDto> refLogs = new HashSet<RefLogDto>();

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

	public String getReqIp() {
		return reqIp;
	}

	public void setReqIp(String reqIp) {
		this.reqIp = reqIp;
	}

	public String getRespData() {
		return respData;
	}

	public void setRespData(String respData) {
		this.respData = respData;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public Long getAccessPeriod() {
		return accessPeriod;
	}

	public void setAccessPeriod(Long accessPeriod) {
		this.accessPeriod = accessPeriod;
	}

	public Set<RefLogDto> getRefLogs() {
		return refLogs;
	}

	/** 添加refLog **/
	public void addRefLog(RefLogDto refLog) {
		if(refLogs.size() > 0 && refLogs.contains(refLog)) {
			refLogs.add(refLog);
		}
	}
}
