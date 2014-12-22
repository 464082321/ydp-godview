package com.ydp.godview.service.impl;

import org.springframework.stereotype.Service;

import com.yougou.ydp.api.IDynamicLogApi;

@Service
public class DynamicLogApiImpl implements IDynamicLogApi {

	public Boolean sendLogs(String something) {
		System.out.println("here is the message from remote invoke:" + something);
		return true;
	}

}
