package com.weisi.veems.models.sys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 权限
 * @author luomouren
 */
@Data
@Table(name = "sys_permission")
public class SysPermission implements java.io.Serializable {
	@Id
	private String permissionId;

	private String permissionName;

	private Integer permissionType;

	private String url;

	private String percode;

	private String parentPermissionId;

	private Integer sort;

	private Boolean isAvailable;

	private String icon;

	/**
	 * 该权限下所有子权限
	 */
	@Transient
	private List<SysPermission> childList;
}