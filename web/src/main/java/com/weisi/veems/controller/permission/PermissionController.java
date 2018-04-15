package com.weisi.veems.controller.permission;

import com.github.pagehelper.PageInfo;
import com.weisi.common.JsonResult;
import com.weisi.common.ZtreeView;
import com.weisi.veems.frame.constants.PageConstants;
import com.weisi.veems.models.sys.SysPermission;
import com.weisi.veems.services.permission.SysPermissonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 权限信息
 *
 * @author luomouren
 */
@Controller
@RequestMapping({"/permission"})
public class PermissionController {
	private static String PAGE_FILE_NAME = "bzh/permission/";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysPermissonService permissionService;

	@RequestMapping("/tree/{permissionId}")
	@ResponseBody
	public List<ZtreeView> tree(@PathVariable String permissionId) {
		List<ZtreeView> list = permissionService.tree(permissionId);
		return list;
	}

	@RequestMapping("/index")
	public String index(@RequestParam(value = "pageNum", required = true, defaultValue = PageConstants.PAGE_NUM+"") int pageNum,
						@RequestParam(value = "pageSize", required = true, defaultValue = PageConstants.PAGE_SIZE+"") int pageSize,
						@RequestParam(value = "sortOrder", required = true, defaultValue = " PERMISSION_NAME asc ") String sortOrder,
						ModelMap map) {
		PageInfo<SysPermission> pageInfo = permissionService.findAll(pageNum, pageSize,sortOrder);
		// pageInfo.list 保存所有的SysPermission
		map.put("pageInfo", pageInfo);
		return PAGE_FILE_NAME +"index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		SysPermission permission = new SysPermission();
		permission.setIsAvailable(true);
		permission.setPermissionType(1);
		map.put("permissionInfo", permission);
		map.put("isAdd", true);
		return PAGE_FILE_NAME +"form";
	}


	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap map) {
		SysPermission permission = permissionService.selectByPrimaryKey(id);
		map.put("permissionInfo", permission);
		map.put("isAdd", false);
		return PAGE_FILE_NAME +"form";
	}

	@RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(SysPermission permission, Boolean isAdd, ModelMap map) {
		try {
			if(isAdd){
				// 新建保存
				permission.setPermissionId(UUID.randomUUID()+"");
				permissionService.insert(permission);
			}else{
				// 编辑保存用户
				permissionService.updateByPrimaryKey(permission);
			}
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable String id) {
		try {
			permissionService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

	/**
	 * 无权访问页面时
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return PAGE_FILE_NAME +"unauthorized";
	}

}


