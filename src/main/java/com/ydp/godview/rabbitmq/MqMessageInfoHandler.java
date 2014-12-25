package com.ydp.godview.rabbitmq;

import java.util.Map;

public class MqMessageInfoHandler {
	public MqMessageInfoHandler() {
		System.out.println("MqMessageInfoHandler初始化了......");
	}
	
	/** 处理rabbit异步传输过来的消息 **/
	public void doMessage(Map<String, Object> asldMap) {
		System.out.println("Message come!!!");
	}
	
	public void doMessage2(String testStr) {
		System.out.println("here is the message from rabbit:" + testStr);
	}
}
