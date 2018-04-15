package com.weisi.veems.controller.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:luomouren
 * @description:用户登录
 * @dateTime: created in  2018-04-10 16:38
 * @modified by:
 **/
@Controller
public class LoginController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login() {
		return "bzh/login";
	}

	@RequestMapping("/index")
	public String list(Model model) {
		return "bzh/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,ModelMap model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return "redirect:index";
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
		}
		return "bzh/login";
	}

	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		// url重定向
		return "redirect:bzh/login";
	}



}