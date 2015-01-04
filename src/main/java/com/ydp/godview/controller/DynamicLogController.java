package com.ydp.godview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.ydp.godview.common.PageFinder;
import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;
import com.ydp.godview.model.SettingInterfaceDto;
import com.ydp.godview.model.SettingLogDto;
import com.ydp.godview.service.IDynamicLogService;
import com.ydp.godview.service.ISettingLogService;
import com.ydp.godview.utils.JsonResult.StateCode;

/**
 * 日志加载控制器
 * 
 * @author he.f1
 */
@Controller
@RequestMapping("/dynamicLog")
public class DynamicLogController {

	@Resource
	private IDynamicLogService dynamicLogService;

	@Resource
	private ISettingLogService settingLogService;

	/**
	 * 查询接口调用的日志信息
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/serviceLog")
	public String serviceLogList(String pageNo, ModelMap modelMap) throws Exception {
		int pageSize = 6;
		int page = pageNo == null ? 1 : Integer.valueOf(pageNo);
		// 根据结算单主键id查询结算单明细
		List<ServiceLogDto> data = new ArrayList<ServiceLogDto>();
		int rowCount = dynamicLogService.queryActionSerLogCount();
		if (rowCount > 0) {
			int start = (page - 1) * pageSize;
			data = dynamicLogService.queryActionSerLogList(start, pageSize);
		}

		// 查询数据库的配置信息
		List<SettingInterfaceDto> settingList = settingLogService.listSettings();

		// 创建PageFinder对象
		PageFinder<ServiceLogDto> pageFinder = new PageFinder<ServiceLogDto>(page, pageSize, rowCount, data);
		modelMap.addAttribute("settingList", settingList);
		modelMap.addAttribute("pageFinder", pageFinder);
		return "dynamicLog/serviceLog";
	}

	/**
	 * 查询第三方接口调用的日志信息
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refLog")
	public String refLogList(String serviceId, ModelMap modelMap) throws Exception {
		List<RefLogDto> refLogList = dynamicLogService.queryActionRefLogList(serviceId);
		modelMap.addAttribute("refLogList", refLogList);
		modelMap.addAttribute("serviceId", serviceId);
		return "dynamicLog/refLog";
	}

	/**
	 * 异步提交
	 * 
	 * @param methodName
	 * @return
	 */
	@RequestMapping("/udMethods")
	@ResponseBody
	public String saveMonitorMethod(String methodName) {
		List<SettingLogDto> lstSettingLogDto = new ArrayList<SettingLogDto>();
		// 关闭所有的接口监控
		settingLogService.closeSetting("0");
		// 打开需要监控的接口方法
		if (StringUtils.isNotEmpty(methodName)) {
			String[] methodNames = methodName.split(",");
			for (int i = 0; i < methodNames.length; i++) {
				SettingLogDto settingLogDto = new SettingLogDto();
				settingLogDto.setMethodName(methodNames[i]);
				settingLogDto.setIsOpen("1");
				lstSettingLogDto.add(settingLogDto);
			}
			settingLogService.butchUdSetting(lstSettingLogDto);
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("state", StateCode.SUCCESS);
		jsonObj.put("msg", "");
		jsonObj.put("data", null);
		return jsonObj.toString();
	}
	
	@RequestMapping("/udSettings")
	@ResponseBody
	public String udSettings() {
		settingLogService.updateSettingsByRemoteApi();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("state", StateCode.SUCCESS);
		jsonObj.put("msg", "");
		jsonObj.put("data", null);
		return jsonObj.toString();
	}
}
