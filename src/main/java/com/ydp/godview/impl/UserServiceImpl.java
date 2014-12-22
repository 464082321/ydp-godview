package com.ydp.godview.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ydp.godview.dao.UserDao;
import com.ydp.godview.model.User;
import com.ydp.godview.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}
	
	public List<User> listUsers() {
		List<User> userList = userDao.queryAll();
		
//		List<User> list = new ArrayList<User>();
//		list.add(new User("haocheng", "hc001"));
//		list.add(new User("zhanghaocheng", "zhc001"));
		return userList;
	}
}
