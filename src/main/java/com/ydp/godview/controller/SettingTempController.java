package com.ydp.godview.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydp.godview.model.SettingInterfaceDto;
import com.ydp.godview.service.ISettingLogService;

/**
 * Setting临时Controller，为了Jquery ckeckbox tree做准备
 * @author zhang.hc
 *
 */
@Controller
@RequestMapping("/setting")
public class SettingTempController {
	@Resource
	private ISettingLogService settingLogService;
	
	@RequestMapping("/list")
	public String settringList(ModelMap modelMap) {
		List<SettingInterfaceDto> settingList = settingLogService.listSettings();
		modelMap.addAttribute("settingList", settingList);
		return "settingOperation/setting_list";
	}
}
