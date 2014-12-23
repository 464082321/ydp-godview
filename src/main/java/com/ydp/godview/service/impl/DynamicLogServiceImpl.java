package com.ydp.godview.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ydp.godview.dao.SysLogDao;
import com.ydp.godview.model.RefLogDto;
import com.ydp.godview.model.ServiceLogDto;
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
	 * 查询接口调用的记录数
	 * 
	 * @return int
	 */
	public int queryActionSerLogCount() {
		return sysLogDao.queryActionSerLogCount();
	}

	/**
	 * 查询接口调用的日志信息
	 * 
	 * @return List<ActionServiceLog>
	 */
	public List<ServiceLogDto> queryActionSerLogList() {
		return sysLogDao.queryActionSerLogList();
	}

	/**
	 * 查询第三方接口调用的日志信息
	 * 
	 * @return List<ActionRefLog>
	 */
	public List<RefLogDto> queryActionRefLogList(String serviceId) {
		return sysLogDao.queryActionRefLogList(serviceId);
	}

	/**
	 * 保存接口调用的日志信息
	 * 
	 * @param lstActionServiceLog
	 */
	public void saveActionSerLog(List<ActionServiceLogDto> lstActionServiceLog) {
		sysLogDao.saveActionSerLog(lstActionServiceLog);
	}

	/**
	 * 保存第三方接口调用的日志信息
	 * 
	 * @param lstActionRefLog
	 */
	public void saveActionRefLog(List<ActionRefLogDto> lstActionRefLog) {
		sysLogDao.saveActionRefLog(lstActionRefLog);
	}

}
