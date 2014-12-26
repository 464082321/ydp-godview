package com.ydp.godview.rabbitmq;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ydp.godview.service.IDynamicLogService;
import com.ydp.godview.utils.ReflectUtils;
import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

/**
 * MQ消息队列处理者
 * 
 * @author he.f1
 */
public class MqMessageInfoHandler {
	
	/** LOGGER日志打印对象 */
	protected final Logger logger = Logger.getLogger(super.getClass().getName());

	private ObjectMapper mapper = new ObjectMapper();

	@Resource
	private IDynamicLogService dynamicLogService;

	/** 处理rabbit异步传输过来的消息 **/
	@SuppressWarnings("unchecked")
	public void doMessage(Map<String, Object> asldMap) {
		ActionServiceLogDto asld = ReflectUtils.getBean(asldMap, ActionServiceLogDto.class);
		if (null != asld) {
			try {
				// 处理rabbit
				Map<String, Object>[] refLogsArray = mapper.convertValue(asldMap.get("refLogs"), Map[].class);
				if (null != refLogsArray && refLogsArray.length > 0) {
					ActionRefLogDto arfl = null;
					for (Map<String, Object> refLog : refLogsArray) {
						arfl = ReflectUtils.getBean(refLog, ActionRefLogDto.class);
						asld.addRefLog(arfl);
					}
				}
			} catch (Exception e) {
				logger.error("处理rabbit异步传输过来的消息时出现异常", e);
				return;
			}
			try {
				// 调用接口保存日志信息
				dynamicLogService.sendLogs(asld);
			} catch (Exception e) {
				logger.error("调用sendLogs()保存异步传输过来的消息时出现异常", e);
				return;
			}
		}
	}
}
