package com.weisi.veems.models.sys;

import lombok.Data;

/**
 * 角色权限表
 *
 * @author luomouren
 */
@Data
public class SysMapRolePermission implements java.io.Serializable {
	private String roleId;
	private String permissionId;

}