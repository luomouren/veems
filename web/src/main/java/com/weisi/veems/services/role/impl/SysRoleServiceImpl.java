package com.weisi.veems.services.role.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weisi.veems.mapper.sys.SysMapRolePermissionMapper;
import com.weisi.veems.mapper.sys.SysRoleMapper;
import com.weisi.veems.models.sys.SysRole;
import com.weisi.veems.services.base.impl.BaseServiceImpl;
import com.weisi.veems.services.role.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色service
 *
 * @author luomouren
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysMapRolePermissionMapper sysMapRolePermissionMapper;

	@Override
	public PageInfo<SysRole> findAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysRole> roles = sysRoleMapper.selectAll();
		return new PageInfo<SysRole>(roles);
	}

	@Override
	public void grant(String id, String[] permissionIds) {
		sysMapRolePermissionMapper.grant(id,permissionIds);
	}
}
