package com.ydp.godview.dao;

import java.util.List;

import com.ydp.godview.model.User;

public interface UserDao {
	public List<User> queryAll();
	
	public void save(User user);
}
