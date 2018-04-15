package com.weisi.veems.controller.user;

import com.github.pagehelper.PageInfo;
import com.weisi.common.JsonResult;
import com.weisi.veems.frame.constants.PageConstants;
import com.weisi.veems.models.sys.SysRole;
import com.weisi.veems.models.sys.SysUser;
import com.weisi.veems.services.role.SysRoleService;
import com.weisi.veems.services.user.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户信息
 *
 * @author luomouren
 * @date 2017/6/4
 */
@Controller
@RequestMapping({"/user"})
public class UserController {
	private static String PAGE_FILE_NAME = "bzh/user/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysUserService sysUserServices;
	@Autowired
	private SysRoleService sysRoleService;


	@RequestMapping(value = {"/index"})
	public String index(@RequestParam(value = "pageNum", required = true, defaultValue = PageConstants.PAGE_NUM+"") int pageNum,
										@RequestParam(value = "pageSize", required = true, defaultValue = PageConstants.PAGE_SIZE+"") int pageSize,
						ModelMap model) {
		PageInfo<SysUser> pageInfo = sysUserServices.findAll(pageNum, pageSize);
		// pageInfo.list 保存所有的sysUser
		model.put("pageInfo", pageInfo);
		return PAGE_FILE_NAME + "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		SysUser user = new SysUser();
		user.setUserCategory(1);
		user.setIsLocked(false);
		map.put("user", user);
		map.put("isAdd", true);
		return PAGE_FILE_NAME+"form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap map) {
		SysUser user = sysUserServices.selectByPrimaryKey(id);
		map.put("user", user);
		map.put("isAdd", false);
		return PAGE_FILE_NAME+"form";
	}

	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(SysUser user, Boolean isAdd,ModelMap map) {
		try {
			if(isAdd){
				// 新建保存
				sysUserServices.save(user);
			}else{
				// 编辑保存用户
				sysUserServices.updateByPrimaryKey(user);
			}
		} catch (Exception e) {
			logger.error(e.toString());
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable String id, ModelMap map) {
		try {
			sysUserServices.deleteById(id);
		} catch (Exception e) {
			logger.error(e.toString());
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}


	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable String id, ModelMap map) {
		SysUser user = sysUserServices.selectByPrimaryKey(id);
		map.put("user", user);
		List<String> roleIds = sysUserServices.findRoleIdsByUserId(id);
		map.put("roleIds", roleIds);
		List<SysRole> roles = sysRoleService.selectAll();
		map.put("roles", roles);
		return PAGE_FILE_NAME+"grant";
	}

	@ResponseBody
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	public JsonResult grant(@PathVariable String id, String[] roleIds) {
		try {
			sysUserServices.grant(id, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/updatePwd", method = RequestMethod.GET)
	public String updatePwd() {
		return PAGE_FILE_NAME+"updatePwd";
	}


	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updatePwd(String oldPassword, String password1, String password2) {
		try {
			Subject subject = SecurityUtils.getSubject();
			Object principal = subject.getPrincipal();
			if (principal == null) {
				return JsonResult.failure("您尚未登录");
			}
			sysUserServices.updatePwd((SysUser) principal, oldPassword, password1, password2);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
}


