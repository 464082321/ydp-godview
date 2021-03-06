package com.ydp.godview.service;

import java.util.List;

import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * 系统日志服务接口
 * 
 * @author he.f1
 */
public interface IDynamicLogService {
	
	/**
	 * 查询接口调用的记录数
	 * 
	 * @return List<ActionServiceLog>
	 */
	public int queryActionSerLogCount();
	
	/**
	 * 查询接口调用的日志信息
	 * 
	 * @return List<ActionServiceLog>
	 */
	public List<ServiceLogDto> queryActionSerLogList(int start, int pageSize);
	
	/**
	 * 查询第三方接口调用的日志信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<RefLogDto> queryActionRefLogList(String actionServiceId);
	
	/**
	 * 保存日志信息
	 * 
	 * @param serviceLogDto
	 * @return
	 */
	public boolean sendLogs(ActionServiceLogDto serviceLogDto) throws Exception;
}
