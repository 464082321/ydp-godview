package com.ydp.godview.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydp.godview.common.PageFinder;
import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;
import com.ydp.godview.service.IDynamicLogService;

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
	 * 查询接口调用的日志信息
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
		List<ServiceLogDto> data = new ArrayList<ServiceLogDto>();
		int rowCount = dynamicLogService.queryActionSerLogCount();
		if (rowCount > 0) {
			data = dynamicLogService.queryActionSerLogList();
		}
		// 创建PageFinder对象
		PageFinder<ServiceLogDto> pageFinder = new PageFinder<ServiceLogDto>(page, pageSize, rowCount, data);
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

}
