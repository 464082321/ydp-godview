package com.ydp.godview.model;

import java.util.HashSet;
import java.util.Set;

public class SettingInterfaceDto {
	private String interfaceName;
	
	private String isOpen;
	
	private Set<SettingMethodDto> methods = new HashSet<SettingMethodDto>();
	
	public SettingInterfaceDto() {
		super();
	}
	public SettingInterfaceDto(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public Set<SettingMethodDto> getMethods() {
		return methods;
	}

	public void setMethods(Set<SettingMethodDto> methods) {
		this.methods = methods;
	}
	
	//-----injected methods
	public void addMethod(SettingMethodDto method) {
		if(!this.methods.contains(method)) {
			this.methods.add(method);
		}
	}
}
