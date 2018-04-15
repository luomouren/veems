package com.weisi.veems.services.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weisi.veems.frame.constants.UserPasswordConstants;
import com.weisi.veems.mapper.sys.SysMapUserRoleMapper;
import com.weisi.veems.mapper.sys.SysUserMapper;
import com.weisi.veems.models.sys.SysUser;
import com.weisi.veems.services.base.impl.BaseServiceImpl;
import com.weisi.veems.services.user.SysUserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

/**
 * 系统用户service
 *
 * @author luomouren
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysMapUserRoleMapper sysMapUserRoleMapper;

	@Override
	public SysUser findByUserName(String userName) {
		return sysUserMapper.findByUserName(userName);
	}

	@Override
	public PageInfo<SysUser> findAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<SysUser> users = sysUserMapper.selectAll();
		return new PageInfo<SysUser>(users);
	}

	@Override
	public void grant(String id, String[] roleIds) {
		sysMapUserRoleMapper.grant(id, roleIds);
	}

	@Override
	public List<String> findRoleIdsByUserId(String userId) {
		return sysMapUserRoleMapper.findRoleIdsByUserId(userId);
	}

	@Override
	public void updatePwd(SysUser user, String oldPassword, String password1, String password2) {
		Assert.notNull(user, "用户不能为空");
		Assert.notNull(oldPassword, "原始密码不能为空");
		Assert.notNull(password1, "新密码不能为空");
		Assert.notNull(password2, "重复密码不能为空");

		SysUser dbUser = sysUserMapper.findByUserName(user.getUserName());
		Assert.notNull(dbUser, "用户不存在");

		String salt = dbUser.getSalt();
		// 旧密码加密后与数据库中的原密码比较是否一致
		String oldPasswordEncrypt = new SimpleHash(UserPasswordConstants.HASHALGORITHM_NAME, oldPassword,
				salt, UserPasswordConstants.HASHITERATIONS).toString();
		Assert.isTrue(user.getPassword().equals(oldPasswordEncrypt), "原始密码不正确");
		Assert.isTrue(password1.equals(password2), "两次密码不一致");
		String password1Encrypt = new SimpleHash(UserPasswordConstants.HASHALGORITHM_NAME, password1,
				salt, UserPasswordConstants.HASHITERATIONS).toString();
		dbUser.setPassword(password1Encrypt);
		sysUserMapper.updateByPrimaryKey(dbUser);
	}

	@Override
	public void save(SysUser user) {
		if (null != user) {
			String password = user.getPassword();
			user.setUserId(UUID.randomUUID() + "");

			// 生成随机salt
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[16];
			random.nextBytes(bytes);
			String salt = Base64.encodeBase64String(bytes);
			user.setSalt(salt);

			// 加密密码
			String passwordEncrypt = new SimpleHash(UserPasswordConstants.HASHALGORITHM_NAME, password,
					salt, UserPasswordConstants.HASHITERATIONS).toString();
			user.setPassword(passwordEncrypt);

			this.insert(user);
		}
	}

}
