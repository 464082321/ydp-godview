package com.ydp.godview.service;

import java.util.List;

import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;
import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * 系统日志服务接口
 * 
 * @author he.f1
 */
public interface IDynamicLogService {
	
	/**
	 * 查询被调用的接口日志信息总数
	 * 
	 * @return List<ActionServiceLog>
	 */
	public int queryActionSerLogCount();
	
	/**
	 * 查询被调用的接口日志信息
	 * 
	 * @return List<ActionServiceLog>
	 */
	public List<ServiceLogDto> queryActionSerLogList();
	
	/**
	 * 查询第三方的接口调用信息总数
	 * 
	 * @return List<ActionRefLog>
	 */
	public int queryActionRefLogCount(String actionServiceId);
	
	/**
	 * 查询第三方的接口调用信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<RefLogDto> queryActionRefLogList(String actionServiceId);
	
	/**
	 * 保存日志信息
	 * 
	 * @param lstActionServiceLog
	 */
	public void saveActionSerLog(List<ActionServiceLogDto> lstActionServiceLog);
	
	/**
	 * 保存日志信息
	 * 
	 * @param lstActionRefLog
	 */
	public void saveActionRefLog(List<ActionRefLogDto> lstActionRefLog);
}
