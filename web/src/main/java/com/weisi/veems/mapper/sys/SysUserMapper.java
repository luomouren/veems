package com.weisi.veems.mapper.sys;

import com.weisi.veems.frame.init.TkMapper;
import com.weisi.veems.models.sys.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息
 *
 * @author luomouren
 */
public interface SysUserMapper extends TkMapper<SysUser> {

	/**
	 * 根据用户名查找用户
	 *
	 * @param userName 用户名
	 * @return
	 */
	SysUser findByUserName(@Param("userName") String userName);

}
