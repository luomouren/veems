package com.weisi.veems.mapper.sys;

import com.weisi.veems.frame.init.TkMapper;
import com.weisi.veems.models.sys.SysMapRolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色map信息
 *
 * @author luomouren
 */
public interface SysMapRolePermissionMapper extends TkMapper<SysMapRolePermission> {

	/**
	 * 给角色分配权限
	 *
	 * @param roleId        角色ID
	 * @param permissionIds 权限ids
	 */
	void grant(@Param("roleId") String roleId, @Param("permissionIds") String[] permissionIds);

	/**
	 * 根据角色id查找其所有的权限id
	 * @param roleId
	 * @return
	 */
	List<String> findAllPermissionIds(@Param("roleId")String roleId);
}
