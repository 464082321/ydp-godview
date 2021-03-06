package com.ydp.godview.model;

/**
 * 日志参数设置Dto
 * @author zhang.hc
 *
 */
public class SettingLogDto {
	
	/** 主键id **/
	private String id;
	
	/** id,not null primary key **/
	private String methodName;
	
	/** 1：为此方法开启；0：此方法关闭 **/
	private String isOpen;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
}
