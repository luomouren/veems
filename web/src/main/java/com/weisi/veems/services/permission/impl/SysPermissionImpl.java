package com.weisi.veems.services.permission.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weisi.common.ZtreeView;
import com.weisi.veems.frame.constants.SysPermissionConstants;
import com.weisi.veems.mapper.sys.SysMapRolePermissionMapper;
import com.weisi.veems.mapper.sys.SysPermissionMapper;
import com.weisi.veems.mapper.sys.SysRoleMapper;
import com.weisi.veems.models.sys.SysPermission;
import com.weisi.veems.services.base.impl.BaseServiceImpl;
import com.weisi.veems.services.permission.SysPermissonService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:luomouren
 * @description:系统所有操作权限
 * @dateTime: created in  2018-04-10 20:15
 * @modified by:
 **/
@Service
public class SysPermissionImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissonService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysMapRolePermissionMapper sysMapRolePermissionMapper;

	@Override
	public List<SysPermission> findPermissionListByUserId(String userId) {
		return sysPermissionMapper.findPermissionListByUserId(userId);
	}

	@Override
	public List<SysPermission> findMenuListByUserId(String userId) {
		return  sysPermissionMapper.findMenuListByUserId(userId);
	}

	@Override
	public List<SysPermission> formatChildPermission(List<SysPermission> permiissionList) {
		// 最后的结果
		List<SysPermission> menuList = new ArrayList<SysPermission>();
		// 先找到所有的一级菜单
		for(SysPermission permission:permiissionList){
			if(SysPermissionConstants.FIST_MENU ==  permission.getPermissionType()){
				menuList.add(permission);
			}
		}
		// 为一级菜单设置子菜单，getChild是递归调用的
		for (SysPermission menu : menuList) {
			menu.setChildList(getChild(menu.getPermissionId(), permiissionList));
		}

		return menuList;
	}

	/**
	 * 递归查找子菜单
	 *
	 * @param id
	 *            当前菜单id
	 * @param rootMenu
	 *            要查找的列表
	 * @return
	 */
	private List<SysPermission> getChild(String id, List<SysPermission> rootMenu) {
		// 子菜单
		List<SysPermission> childList = new ArrayList<>();
		for (SysPermission menu : rootMenu) {
			// 遍历所有节点，将父菜单id与传过来的id比较
			if (StringUtils.isNotBlank(menu.getPermissionId())) {
				if ( menu.getParentPermissionId() !=null &&  id.equals(menu.getParentPermissionId()) ) {
					childList.add(menu);
				}
			}
		}
		// 把子菜单的子菜单再循环一遍
		for (SysPermission menu : childList) {
			// 没有url子菜单还有子菜单
			if ("javascript:void(0);".equalsIgnoreCase(menu.getUrl())) {
				// 递归
				menu.setChildList(getChild(menu.getPermissionId(), rootMenu));
			}
		}
		// 递归退出条件
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}


	@Override
	public PageInfo<SysPermission> findAll(int pageNum, int pageSize,String sortOrder) {
		PageHelper.startPage(pageNum, pageSize);
		PageHelper.orderBy(sortOrder);
		List<SysPermission> results = sysPermissionMapper.selectAll();
		return new PageInfo<SysPermission>(results);
	}

	@Override
	public List<ZtreeView> tree(String roleId) {
		List<ZtreeView> resulTreeNodes = new ArrayList<ZtreeView>();
		List<String> permissionIds = sysMapRolePermissionMapper.findAllPermissionIds(roleId);
		ZtreeView node;
		List<SysPermission> allPermissions = sysPermissionMapper.findAllByOrderByParentAscIdAscSortAsc();
		for (SysPermission resource : allPermissions) {
			node = new ZtreeView();
			node.setId(resource.getPermissionId());
			if (resource.getParentPermissionId() == null) {
				node.setId(resource.getPermissionId());
			} else {
				node.setPId(resource.getParentPermissionId());
			}
			node.setName(resource.getPermissionName());
			if (permissionIds != null && permissionIds.contains(resource.getPermissionId())) {
				node.setChecked(true);
			}
			resulTreeNodes.add(node);
		}
		return resulTreeNodes;
	}


}
