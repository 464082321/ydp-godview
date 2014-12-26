package com.ydp.godview.rabbitmq;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.ydp.godview.utils.ReflectUtils;
import com.yougou.ydp.dto.ActionRefLogDto;
import com.yougou.ydp.dto.ActionServiceLogDto;

public class MqMessageInfoHandler {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/** 处理rabbit异步传输过来的消息 **/
	@SuppressWarnings("unchecked")
	public void doMessage(Map<String, Object> asldMap) {
		ActionServiceLogDto asld = ReflectUtils.getBean(asldMap, ActionServiceLogDto.class);
		try {
			Map<String, Object>[] refLogsArray = mapper.convertValue(asldMap.get("refLogs"), Map[].class);
			ActionRefLogDto arfl = null;
			if(null != refLogsArray) {
				for(Map<String, Object> refLog : refLogsArray) {
					arfl = ReflectUtils.getBean(refLog, ActionRefLogDto.class);
					asld.addRefLog(arfl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//------这是封装好的数据
		if(null != asld) {
			System.out.println("被请求的ydp接口:" + asld.getInterfaceName());
			System.out.println("其他省略......");
			for(ActionRefLogDto arfl : asld.getRefLogs()) {
				System.out.println("\t请求的第三方接口:" + arfl.getInterfaceName());
				System.out.println("\t其他省略......");
			}
		}
	}
}
