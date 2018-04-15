package com.weisi.veems.controller.role;

import com.github.pagehelper.PageInfo;
import com.weisi.common.JsonResult;
import com.weisi.veems.frame.constants.PageConstants;
import com.weisi.veems.models.sys.SysRole;
import com.weisi.veems.services.role.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 角色信息
 *
 * @author luomouren
 */
@Controller
@RequestMapping({"/role"})
public class RoleController {
	private static String PAGE_FILE_NAME = "bzh/role/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = {"/index"})
	public String index(@RequestParam(value = "pageNum", required = true, defaultValue = PageConstants.PAGE_NUM + "") int pageNum,
						@RequestParam(value = "pageSize", required = true, defaultValue = PageConstants.PAGE_SIZE + "") int pageSize,
						ModelMap modelMap) {
		PageInfo<SysRole> pageInfo = sysRoleService.findAll(pageNum, pageSize);
		modelMap.put("pageInfo", pageInfo);
		return PAGE_FILE_NAME + "index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		SysRole role = new SysRole();
		role.setIsAvailable(true);
		map.put("role", role);
		map.put("isAdd", true);
		return PAGE_FILE_NAME + "form";
	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap map) {
		SysRole role = sysRoleService.selectByPrimaryKey(id);
		map.put("role", role);
		map.put("isAdd", false);
		return PAGE_FILE_NAME + "form";
	}


	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(SysRole role,Boolean isAdd, ModelMap map) {
		try {
			if(isAdd){
				// 新增角色
				role.setRoleId(UUID.randomUUID()+"");
				sysRoleService.insert(role);
			}else{
				// 编辑保存角色
				sysRoleService.updateByPrimaryKey(role);
			}
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable String id, ModelMap map) {
		try {
			sysRoleService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable String id, ModelMap map) {
		SysRole role = sysRoleService.selectByPrimaryKey(id);
		map.put("role", role);
		return PAGE_FILE_NAME + "grant";
	}

	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult grant(@PathVariable String id,
							@RequestParam(required = false) String[] permissionIds, ModelMap map) {
		try {
			sysRoleService.grant(id, permissionIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

}


