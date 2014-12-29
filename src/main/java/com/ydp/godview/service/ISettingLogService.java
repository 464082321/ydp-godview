package com.ydp.godview.service;

import java.util.List;

import com.ydp.godview.model.SettingInterfaceDto;
import com.ydp.godview.model.SettingLogDto;

public interface ISettingLogService {
	
	/**
	 * 根据条件查询所有监控了的方法(1:已开启;0:已关闭;null:所有)
	 * 
	 * @return
	 */
	public List<SettingInterfaceDto> listSettings();
	
	/**
	 * 插入需要监控的方法
	 * 
	 * @param settingLogDto
	 */
	public void addSetting(SettingLogDto settingLogDto);

	/**
	 * 删除需要监控的方法,id为方法的全称,包括包路径
	 * 
	 * @param methodName
	 */
	public void delSetting(String methodName);

	/**
	 * 根据methodName更新setting，目前只更新isOpen
	 * 
	 * @param settingLogDto
	 */
	public void udSetting(SettingLogDto settingLogDto);

	/**
	 * 关闭所有的接口监控
	 * 
	 * @param isOpen
	 */
	public void closeSetting(String isOpen);

	/**
	 * 批量更新
	 * 
	 * @param lstSettingLogDto
	 */
	public void butchUdSetting(List<SettingLogDto> lstSettingLogDto);
}
