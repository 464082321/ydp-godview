package com.ydp.godview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydp.godview.common.PageFinder;
import com.ydp.godview.service.IDynamicLogService;
import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

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

	/**
	 * 查询被调用的接口日志信息
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/serviceLog")
	public String serviceLogList(String pageNo, ModelMap modelMap) throws Exception {
		int pageSize = 20;
		int page = pageNo == null ? 1 : Integer.valueOf(pageNo);
		// 根据结算单主键id查询结算单明细
		List<ActionServiceLogDto> data = new ArrayList<ActionServiceLogDto>();
		int rowCount = dynamicLogService.queryActionSerLogCount();
		if (rowCount > 0) {
			data = dynamicLogService.queryActionSerLogList();
		}
		// 创建PageFinder对象
		PageFinder<ActionServiceLogDto> pageFinder = new PageFinder<ActionServiceLogDto>(page, pageSize, rowCount, data);
		modelMap.addAttribute("pageFinder", pageFinder);
		return "dynamicLog/serviceLog";
	}

	/**
	 * 第三方的接口调用信息
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refLog")
	public String refLogList(String billId, ModelMap modelMap) throws Exception {
		String actionServiceId = "";
		List<ActionRefLogDto> list = dynamicLogService.queryActionRefLogList(actionServiceId);
		modelMap.addAttribute("refLogList", list);
		return "dynamicLog/refLog";
	}

}
