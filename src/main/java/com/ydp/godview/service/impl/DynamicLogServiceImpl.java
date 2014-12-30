package com.ydp.godview.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ydp.godview.dao.DynamicLogDao;
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

	/** LOGGER日志打印对象 */
	protected final Logger logger = Logger.getLogger(super.getClass().getName());

	@Resource
	private DynamicLogDao sysLogDao;

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
	 * 保存日志信息
	 * 
	 * @param serviceLogDto
	 * @return
	 */
	public boolean sendLogs(ActionServiceLogDto actionServiceLog) throws Exception {
		try {
			// 封装接口调用的日志信息
			ServiceLogDto serviceLog = this.setServiceLogInfo(actionServiceLog);
			// 保存接口调用的日志信息
			sysLogDao.saveActionSerLog(serviceLog);
			int serviceId = serviceLog.getId();
			
			// 封装第三方接口调用的日志信息
			Set<ActionRefLogDto> refLogs = actionServiceLog.getRefLogs();
			if (CollectionUtils.isNotEmpty(refLogs)) {
				List<RefLogDto> lstRefLog = new ArrayList<RefLogDto>();
				for (ActionRefLogDto actionRefLog : refLogs) {
					RefLogDto refLog = this.setRefLogInfo(actionRefLog, serviceId);
					lstRefLog.add(refLog);
				}
				// 保存第三方接口调用的日志信息
				sysLogDao.saveActionRefLog(lstRefLog);
				System.out.println("\t接口调用的相关日志信息保存成功......");
			}
		} catch (Exception e) {
			logger.error("保存日志信息到数据库时出现异常", e);
			return false;
		}
		return true;
	}

	/**
	 * 设置接口调用的日志信息
	 * 
	 * @param actionServiceLog
	 * @return ServiceLogDto
	 */
	private ServiceLogDto setServiceLogInfo(ActionServiceLogDto actionServiceLog) {
		ServiceLogDto serviceLog = new ServiceLogDto();
		serviceLog.setInterfaceName(actionServiceLog.getInterfaceName());
		serviceLog.setReqIp(actionServiceLog.getReqIp());
		serviceLog.setReqMethod(actionServiceLog.getReqMethod());
		Object[] objParam1 = actionServiceLog.getReqParams();
		serviceLog.setReqParams(this.converStr(objParam1));
		Object objRespData1 = actionServiceLog.getRespData();
		if (null != objRespData1) {
			serviceLog.setRespData(objRespData1.toString());
		}
		serviceLog.setErrMsg(actionServiceLog.getErrMsg());
		serviceLog.setAccessPeriod(actionServiceLog.getAccessPeriod());
		return serviceLog;
	}

	/**
	 * 设置第三方接口调用的日志信息
	 * 
	 * @param actionRefLog
	 * @param serviceId
	 * @return RefLogDto
	 */
	private RefLogDto setRefLogInfo(ActionRefLogDto actionRefLog, int serviceId) {
		RefLogDto refLog = new RefLogDto();
		refLog.setInterfaceName(actionRefLog.getInterfaceName());
		refLog.setReqMethod(actionRefLog.getReqMethod());
		Object[] objParam2 = actionRefLog.getReqParams();
		refLog.setReqParams(this.converStr(objParam2));
		Object objRespData2 = actionRefLog.getRespData();
		if (null != objRespData2) {
			refLog.setRespData(objRespData2.toString());
		}
		refLog.setAccessPeriod(actionRefLog.getAccessPeriod());
		refLog.setServiceId(serviceId);
		return refLog;
	}

	/**
	 * 转换字符串
	 * 
	 * @param objs
	 * @return String
	 */
	private String converStr(Object[] objs) {
		if (!ObjectUtils.isEmpty(objs)) {
			StringBuffer sbParam = new StringBuffer();
			for (int i = 0; i < objs.length; i++) {
				sbParam.append(objs[i].toString());
				sbParam.append(";");
			}
			return sbParam.toString();
		}
		return "";
	}

}
