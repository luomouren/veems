package com.weisi.veems.frame.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.weisi.veems.models.sys.SysPermission;
import com.weisi.veems.models.sys.SysUser;
import com.weisi.veems.services.permission.SysPermissonService;
import com.weisi.veems.services.user.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Shiro Realm
 *
 * @author luomouren
 */
public class ShiroRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysPermissonService sysPermissonService;

	/**
	 * 设置realm的名称
	 *
	 * @param name
	 */
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	/**
	 * realm的认证方法，从数据库查询用户信息
	 *
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的用户名和密码
		// 第一步从token中取出用户名
		String userName = (String) token.getPrincipal();

		// 第二步：根据用户输入的userCode从数据库查询
		SysUser sysUser = sysUserService.findByUserName(userName);

		// 如果查询不到返回null
		if (sysUser == null) {
			return null;
		} else {
			// 判断帐号是否锁定
			if (Boolean.TRUE.equals(sysUser.getIsLocked())) {
				// 抛出 帐号锁定异常
				throw new LockedAccountException();
			}
			// 从数据库查询到密码
			String password = sysUser.getPassword();
			//盐
			String salt = sysUser.getSalt();

			// 查询用户对应的导航模块信息
			List<SysPermission> menulList = sysPermissonService.findMenuListByUserId(sysUser.getUserId());
			// 组装导航权限父子关系
			menulList = sysPermissonService.formatChildPermission(menulList);
			sysUser.setMenuJson(JSON.toJSONString(menulList));

			// 将sysUser设置simpleAuthenticationInfo
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
					sysUser, password, ByteSource.Util.bytes(salt), this.getName());
			return simpleAuthenticationInfo;
		}

	}




	/**
	 * 用于授权
	 *
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {

		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		SysUser activeUser = (SysUser) principals.getPrimaryPrincipal();

		//根据身份信息获取权限信息
		//从数据库获取到权限数据
		List<SysPermission> permissionList = sysPermissonService.findPermissionListByUserId(activeUser.getUserId());

		//单独定一个集合对象
		List<String> permissions = new ArrayList<String>();
		if (permissionList != null) {
			for (SysPermission sysPermission : permissionList) {
				//将数据库中的权限标签 符放入集合
				permissions.add(sysPermission.getPercode());
			}
		}

		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissions);

		return simpleAuthorizationInfo;
	}

	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	/**
	 * 明文密码进行加密
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		int hashIterations = 1;//加密的次数
		Object salt = "eteokues";//盐值
		Object credentials = "111111";//密码
		String hashAlgorithmName = "MD5";//加密方式
		Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
				salt, hashIterations);
		// cb571f7bd7a6f73ab004a70322b963d5
		System.out.println("加密后的值----->" + simpleHash);
	}


}
