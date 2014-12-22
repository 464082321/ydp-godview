package com.ydp.godview.dao;

import java.util.List;

import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * 数据库操作类
 * 
 * @author he.f1
 */
public interface SysLogDao {
	
	/**
	 * 查询被调用的接口日志信息数量
	 * 
	 * @return List<ActionServiceLog>
	 */
	public int queryActionSerLogCount();
	
	/**
	 * 查询被调用的接口日志信息
	 * 
	 * @return List<ActionServiceLog>
	 */
	public List<ActionServiceLogDto> queryActionSerLogList();
	
	/**
	 * 查询第三方的接口调用信息数量
	 * 
	 * @return List<ActionRefLog>
	 */
	public int queryActionRefLogCount(String actionServiceId);
	
	/**
	 * 查询第三方的接口调用信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<ActionRefLogDto> queryActionRefLogList(String actionServiceId);

	/**
	 * 保存日志信息
	 * 
	 * @param lstActionServiceLog
	 */
	public void saveActionSerLog(List<ActionServiceLogDto> lstActionServiceLog);
	
	/**
	 * 保存日志信息
	 * 
	 * @param lstActionServiceLog
	 */
	public void saveActionRefLog(List<ActionRefLogDto> lstActionRefLog);
}
