package com.weisi.veems.mapper.sys;

import com.weisi.veems.frame.init.TkMapper;
import com.weisi.veems.models.sys.SysPermission;

import java.util.List;

/**
 * @author:luomouren
 * @description:权限mapper
 * @dateTime: created in  2018-04-10 12:11
 * @modified by:
 **/
public interface SysPermissionMapper extends TkMapper<SysPermission> {
	/**
	 * 根据用户id查询权限url
	 *
	 * @param userId
	 * @return
	 */
	List<SysPermission> findPermissionListByUserId(String userId);

	/**
	 * 根据用户主键，获取其权限下所有的menu，按照sort asc排序
	 * @param userId
	 * @return
	 */
	List<SysPermission> findMenuListByUserId(String userId);

	/**
	 * 根据父节点asc/排序值asc 查询所有的权限
	 *
	 * @return
	 */
	List<SysPermission> findAllByOrderByParentAscIdAscSortAsc();
}
