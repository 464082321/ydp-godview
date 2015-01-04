package com.ydp.godview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;

/**
 * 数据库操作类
 * 
 * @author he.f1
 */
public interface DynamicLogDao {
	
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
	public List<ServiceLogDto> queryActionSerLogList(@Param("start")int start, @Param("pageSize")int pageSize);
	
	/**
	 * 查询第三方接口调用的日志信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<RefLogDto> queryActionRefLogList(@Param("serviceId")String serviceId);

	/**
	 * 保存接口调用的日志信息
	 * 
	 * @param serviceLog
	 */
	public int saveActionSerLog(ServiceLogDto serviceLog);
	
	/**
	 * 保存第三方接口调用的日志信息
	 * 
	 * @param lstRefLog
	 */
	public void saveActionRefLog(List<RefLogDto> lstRefLog);
}
