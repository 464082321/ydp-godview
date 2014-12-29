package com.ydp.godview.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydp.godview.dao.SettingLogDao;
import com.ydp.godview.model.SettingInterfaceDto;
import com.ydp.godview.model.SettingLogDto;
import com.ydp.godview.model.SettingMethodDto;
import com.ydp.godview.service.ISettingLogService;

@Service
public class SettingLogServiceImpl implements ISettingLogService {
	@Resource
	private SettingLogDao settingLogDao;

	public List<SettingInterfaceDto> listSettings() {
		List<SettingInterfaceDto> dtoList = null;
		List<SettingLogDto> dbList = settingLogDao.querySettings(null);
		if (null != dbList && dbList.size() > 0) {
			Map<String, List<SettingLogDto>> tmpMap = new HashMap<String, List<SettingLogDto>>();
			String interfaceName;
			List<SettingLogDto> mapLogList = null;
			for (SettingLogDto sld : dbList) {
				interfaceName = sld.getMethodName().substring(0, sld.getMethodName().lastIndexOf("."));
				if (tmpMap.containsKey(interfaceName)) {
					tmpMap.get(interfaceName).add(sld);
				} else {
					mapLogList = new ArrayList<SettingLogDto>();
					mapLogList.add(sld);
					tmpMap.put(interfaceName, mapLogList);
				}
			}

			dtoList = new ArrayList<SettingInterfaceDto>();
			SettingInterfaceDto sid = null;
			String openState = null;
			boolean allStateMatch = true;
			for (String in : tmpMap.keySet()) {
				sid = new SettingInterfaceDto(in);

				for (SettingLogDto sld : tmpMap.get(in)) {
					if (null == openState) {
						openState = sld.getIsOpen();
					} else {
						if (!openState.equals(sld.getIsOpen())) {
							allStateMatch = false;
						}
					}
					sid.addMethod(new SettingMethodDto(sld.getMethodName().substring(
							sld.getMethodName().lastIndexOf(".") + 1), sld.getIsOpen()));
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

}
