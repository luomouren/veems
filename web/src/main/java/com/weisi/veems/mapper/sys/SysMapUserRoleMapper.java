package com.weisi.veems.mapper.sys;

import com.weisi.veems.frame.init.TkMapper;
import com.weisi.veems.models.sys.SysMapUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色map信息
 *
 * @author luomouren
 */
public interface SysMapUserRoleMapper extends TkMapper<SysMapUserRole> {

	/**
	 * 根据用户id查找roleIds
	 *
	 * @param userId 用户id
	 * @return
	 */
	List<String> findRoleIdsByUserId(@Param("userId") String userId);

	/**
	 * 分配角色
	 *
	 * @param userId  用户id
	 * @param roleIds 角色id
	 */
	void grant(@Param("userId") String userId, @Param("roleIds") String[] roleIds);
}
