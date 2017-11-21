package com.ssh.controller;

import com.ssh.model.UserVO;
import com.ssh.service.IUserService;
import org.springframework.stereotype.Controller;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);//record log
    private static final String USER = "user";//page name

    public UserController() {
        System.out.println("UserController()");
    }

    @Resource
    private IUserService userService;

	@RequestMapping(value = "/user")
	@ResponseBody
	public UserVO getUser(Integer id){
		return  userService.getByKey(id);
	}


	@RequestMapping(value = "/addUser")
	@ResponseBody
	public UserVO addUser(String name,Integer age){
		return userService.addUser(name,age);
	}

	@RequestMapping(value = "/set")
	@ResponseBody
	public String setUser(HttpSession session) {
		UserVO user = new UserVO();
		user.setName("lyf");
		user.setAge(123);
		session.setAttribute("user", user);
		return "成功";
	}
	@RequestMapping(value = "get")
	@ResponseBody
	public String getUser(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute("user");
		String name = user.getName();
		return "用户名称：" + name;
	}

}