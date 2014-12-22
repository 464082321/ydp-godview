package com.ydp.godview.service;

import java.util.List;

import com.ydp.godview.model.User;

public interface UserService {
	public void save(User user);
	
	public List<User> listUsers();
}
