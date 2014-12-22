package com.ydp.godview.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydp.godview.dao.SysLogDao;
import com.ydp.godview.service.IDynamicLogService;
import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * 系统日志服务层
 * 
 * @author he.f1
 */
@Service
public class DynamicLogServiceImpl implements IDynamicLogService {

	@Resource
	private SysLogDao sysLogDao;

	/**
	 * 查询被调用的接口日志信息数量
	 * 
	 * @return int
	 */
	public int queryActionSerLogCount() {
		return sysLogDao.queryActionSerLogCount();
	}

	/**
	 * 查询被调用的接口日志信息
	 * 
	 * @return List<ActionServiceLog>
	 */
	public List<ActionServiceLogDto> queryActionSerLogList() {
		return sysLogDao.queryActionSerLogList();
	}

	/**
	 * 查询第三方的接口调用信息数量
	 * 
	 * @return int
	 */
	public int queryActionRefLogCount(String actionServiceId) {
		return sysLogDao.queryActionRefLogCount(actionServiceId);
	}

	/**
	 * 查询第三方的接口调用信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<ActionRefLogDto> queryActionRefLogList(String actionServiceId) {
		return sysLogDao.queryActionRefLogList(actionServiceId);
	}

	/**
	 * 保存日志信息
	 * 
	 * @param lstActionServiceLog
	 */
	public void saveActionSerLog(List<ActionServiceLogDto> lstActionServiceLog) {
		sysLogDao.saveActionSerLog(lstActionServiceLog);
	}

	/**
	 * 保存日志信息
	 * 
	 * @param lstActionRefLog
	 */
	public void saveActionRefLog(List<ActionRefLogDto> lstActionRefLog) {
		sysLogDao.saveActionRefLog(lstActionRefLog);
	}

}
