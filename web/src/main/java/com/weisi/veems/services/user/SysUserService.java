package com.weisi.veems.services.user;

import com.github.pagehelper.PageInfo;
import com.weisi.veems.mapper.sys.SysUserMapper;
import com.weisi.veems.models.sys.SysUser;
import com.weisi.veems.services.base.BaseService;

import java.util.List;

/**
 * 用户信息
 *
 * @author luomouren
 */
public interface SysUserService extends BaseService<SysUserMapper, SysUser> {

	/**
	 * 根据用户名查找用户
	 *
	 * @param userName 用户名
	 * @return
	 */
	SysUser findByUserName(String userName);


	/**
	 *  分页查找所有的用户
	 * @param pageNum 当前页码，从1开始
	 * @param pageSize  一页显示多少行
	 * @return
	 */
	PageInfo<SysUser> findAll(int pageNum, int pageSize);

	/**
	 * 分配角色
	 * @param id 用户id
	 * @param roleIds 角色id
	 */
	void grant(String id, String[] roleIds);

	/**
	 * 修改用户密码
	 *
	 * @param user
	 * @param oldPassword
	 * @param password1
	 * @param password2
	 */
	void updatePwd(SysUser user, String oldPassword, String password1, String password2);

	/**
	 * 根据用户id查找roleIds
	 *
	 * @param userId 用户id
	 * @return
	 */
	List<String> findRoleIdsByUserId(String userId);

	/**
	 * 新建保存用户，自动生成salt盐，加密password保存
	 * @param user
	 */
	void save(SysUser user);
}

