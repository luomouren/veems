package com.weisi.veems.services.permission;

import com.github.pagehelper.PageInfo;
import com.weisi.common.ZtreeView;
import com.weisi.veems.mapper.sys.SysPermissionMapper;
import com.weisi.veems.models.sys.SysPermission;
import com.weisi.veems.services.base.BaseService;

import java.util.List;

/**
 * @author:luomouren
 * @description:系统所有权限
 * @dateTime: created in  2018-04-10 20:14
 * @modified by:
 **/
public interface SysPermissonService extends BaseService<SysPermissionMapper, SysPermission> {

	/**
	 * 根据用户查询其下面所有权限
	 *
	 * @param userId
	 * @return
	 */
	List<SysPermission> findPermissionListByUserId(String userId);

	/**
	 * 传入所有权限list，将其按照父子关系组装
	 * @param permiissionList
	 * @return
	 */
	List<SysPermission> formatChildPermission(List<SysPermission> permiissionList);

	/**
	 * 根据用户主键，获取其权限下所有的menu，按照sort asc排序
	 * @param userId
	 * @return
	 */
	List<SysPermission> findMenuListByUserId(String userId);

	/**
	 *  分页查找所有的用户
	 * @param pageNum 当前页码，从1开始
	 * @param pageSize  一页显示多少行
	 * @param orderBy  排序字段+排序方式
	 * @return
	 */
	PageInfo<SysPermission> findAll(int pageNum, int pageSize,String orderBy);

	/**
	 * 获取角色的权限树
	 *
	 * @param roleId
	 * @return
	 */
	List<ZtreeView> tree(String roleId);
}
