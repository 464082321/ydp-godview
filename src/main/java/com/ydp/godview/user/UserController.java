package com.ydp.godview.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydp.godview.model.User;
import com.ydp.godview.service.UserService;

@Controller("prototype")
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	//@Resource//测试1
//	private INoticeApi noticeApi;
	
	@RequestMapping("/list")
	public String userList(ModelMap modelMap) throws Exception {
		List<User> list = userService.listUsers();
		modelMap.addAttribute("userList", list);
		
		return "user/user-list";
	}
	
	@RequestMapping("/addUI")
	public String userAddUI() throws Exception {
		return "user/user-add";
	}
	
	@RequestMapping("/save")
	public String userSave(User user) throws Exception {
		userService.save(user);
		return "user/user-suc";
	}
}
