package com.ydp.godview.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydp.godview.service.IDynamicLogService;
import com.yougou.ydp.api.IDynamicLogApi;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * 日志操作接口实现（外部）
 * 
 * @author he.f1
 */
@Service
public class DynamicLogApiImpl implements IDynamicLogApi {

	@Resource
	private IDynamicLogService dynamicLogService;

	public Boolean sendLogs(List<ActionServiceLogDto> lstActionServiceLog) {
		dynamicLogService.saveActionSerLog(lstActionServiceLog);
		return true;
	}
}
