package com.weisi.veems.services.role;

import com.github.pagehelper.PageInfo;
import com.weisi.veems.mapper.sys.SysRoleMapper;
import com.weisi.veems.models.sys.SysRole;
import com.weisi.veems.services.base.BaseService;

/**
 * 角色信息
 *
 * @author luomouren
 */
public interface SysRoleService extends BaseService<SysRoleMapper, SysRole> {

	/**
	 * 分页查找所有的角色
	 *
	 * @param pageNum  当前页码，从1开始
	 * @param pageSize 一页显示多少行
	 * @return
	 */
	PageInfo<SysRole> findAll(int pageNum, int pageSize);

	/**
	 * 给角色分配权限
	 *
	 * @param id          角色ID
	 * @param permissionIds 权限ids
	 */
	void grant(String id, String[] permissionIds);
}

