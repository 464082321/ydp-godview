package com.ydp.godview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ydp.godview.dao.SettingLogDao;
import com.ydp.godview.model.SettingInterfaceDto;
import com.ydp.godview.model.SettingLogDto;
import com.ydp.godview.model.SettingMethodDto;
import com.ydp.godview.service.ISettingLogService;
import com.yougou.ydp.api.IActionLogApi;

@Service
public class SettingLogServiceImpl implements ISettingLogService {
	@Resource
	private SettingLogDao settingLogDao;
	
	@Resource
	private IActionLogApi actionLogApi;

	public List<SettingInterfaceDto> listSettings() {
		List<SettingInterfaceDto> dtoList = null;
		List<SettingLogDto> dbList = settingLogDao.querySettings(null);
		if (null != dbList && dbList.size() > 0) {
			Map<String, List<SettingLogDto>> tmpMap = new HashMap<String, List<SettingLogDto>>();
			String interfaceName;
			List<SettingLogDto> mapLogList = null;
			for (SettingLogDto sld : dbList) {
				interfaceName = getVisibleName(sld.getMethodName(), 1);
				if(StringUtils.hasText(interfaceName)) {
					if (tmpMap.containsKey(interfaceName)) {
						tmpMap.get(interfaceName).add(sld);
					} else {
						mapLogList = new ArrayList<SettingLogDto>();
						mapLogList.add(sld);
						tmpMap.put(interfaceName, mapLogList);
					}
				}
			}

			dtoList = new ArrayList<SettingInterfaceDto>();
			SettingInterfaceDto sid = null;
			boolean allStateMatch = true;
			String methodName = null;
			for (String in : tmpMap.keySet()) {
				allStateMatch = true;
				sid = new SettingInterfaceDto(in);

				for (SettingLogDto sld : tmpMap.get(in)) {
					if ("0".equals(sld.getIsOpen())) {
						allStateMatch = false;
					}
					methodName = getVisibleName(sld.getMethodName(), 0);
					if(StringUtils.hasText(methodName)) {
						sid.addMethod(new SettingMethodDto(methodName, sld.getIsOpen()));
					}
				}
				if (allStateMatch) {
					sid.setIsOpen("1");
				} else {
					sid.setIsOpen("0");
				}
				dtoList.add(sid);
			}
		}

		return dtoList;
	}
	
	/** type 1:获取接口名字,0:获取方法字符串 **/
	private String getVisibleName(String longMethodName, int type) {
		if(StringUtils.hasText(longMethodName)) {
			if(longMethodName.indexOf("(") > 0) {
				if(type==1) {
					String subStr = longMethodName.substring(0, longMethodName.indexOf("("));
					return subStr.substring(0, subStr.lastIndexOf("."));
				} else if(type==0) {
					String subStr = longMethodName.substring(0, longMethodName.indexOf("("));
					int dotIndex = subStr.lastIndexOf(".");
					return longMethodName.substring(dotIndex + 1);
				}
			}
		}
		return null;
	}

	public void addSetting(SettingLogDto settingLogDto) {
		settingLogDao.addSetting(settingLogDto);
	}

	public void delSetting(String methodName) {
		settingLogDao.delSetting(methodName);
	}

	public void udSetting(SettingLogDto settingLogDto) {
		settingLogDao.udSetting(settingLogDto);
	}

	public void closeSetting(String isOpen) {
		settingLogDao.closeSetting(isOpen);
	}

	public void butchUdSetting(List<SettingLogDto> lstSettingLogDto) {
		settingLogDao.butchUdSetting(lstSettingLogDto);
	}
	
	public void batchUdRemoteMethods() {
		List<SettingLogDto> dbSettings = settingLogDao.querySettings("1");
		List<String> methodNames = new ArrayList<String>();
		for(SettingLogDto sld : dbSettings) {
			methodNames.add(sld.getMethodName());
		}
		actionLogApi.batchUdMonitorMethod(methodNames);
	}

	public void updateSettingsByRemoteApi() {
		Map<String, List<String>> apis = actionLogApi.listMonitorMethods();
		if(null != apis && apis.size() > 0) {
			List<SettingLogDto> dbSettings = settingLogDao.querySettings(null);
			//封装成List<String>做比对
			List<String> dbStrSettings = new ArrayList<String>();
			for(SettingLogDto sld : dbSettings) {
				dbStrSettings.add(sld.getMethodName());
			}
			
			List<SettingLogDto> needToSaveList = new ArrayList<SettingLogDto>();
			List<String> apiStrSettings = new ArrayList<String>();
			SettingLogDto needToSave = null;
			for(String interfaceName : apis.keySet()) {
				for(String method : apis.get(interfaceName)) {
					if(!dbStrSettings.contains(interfaceName + "." + method)) {
						needToSave = new SettingLogDto();
						needToSave.setMethodName(interfaceName + "." + method);
						needToSave.setIsOpen("0");
						needToSaveList.add(needToSave);
					}
					
					//为下面删除多余的Settings做准备
					apiStrSettings.add(interfaceName + "." + method);
				}
			}
			
			if(null != needToSaveList && needToSaveList.size() > 0) {
				for(SettingLogDto sld : needToSaveList) {
					//System.out.println("sld:" + sld.getMethodName());
					settingLogDao.addSetting(sld);
				}
			}
			
			
			//删除多余的Settings
			for(SettingLogDto sld : dbSettings) {
				if(!apiStrSettings.contains(sld.getMethodName())) {
					settingLogDao.delSetting(sld.getMethodName());
				}
			}
		}
	}
}
