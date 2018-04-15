package com.weisi.veems.frame.shiro;

import com.weisi.veems.frame.constants.UserPasswordConstants;
import com.weisi.veems.models.sys.SysPermission;
import com.weisi.veems.services.permission.SysPermissonService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shiro config配置项
 *
 * @author luomouren
 */
@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

	@Autowired
	private SysPermissonService sysPermissonService;

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public Realm realm() {
		ShiroRealm realm = new ShiroRealm();
		// 告诉realm,使用credentialsMatcher加密算法类来验证密文
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	/**
	 * 用户授权信息Cache
	 */
	@Bean(name = "shiroCacheManager")
	@ConditionalOnMissingBean
	public CacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	/**
	 * 将缓存对象注入到SecurityManager中
	 * @return
	 */
	@Bean(name = "securityManager")
	@ConditionalOnMissingBean
	public DefaultSecurityManager securityManager() {


		DefaultSecurityManager sm = new DefaultWebSecurityManager();
		sm.setCacheManager(cacheManager());
		return sm;
	}

	/**
	 * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
	 * 指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
	 * @return
	 */
	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		// 散列算法：这里指md5
		credentialsMatcher.setHashAlgorithmName(UserPasswordConstants.HASHALGORITHM_NAME);
		// 散列的次数：比如散列两次，相当于 md5(md5(""));
		credentialsMatcher.setHashIterations(UserPasswordConstants.HASHITERATIONS);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}


	/**
	 * 配置地址
	 * @param securityManager
	 * @return
	 */
	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		securityManager.setRealm(realm);

		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setSuccessUrl("/index");
		shiroFilter.setUnauthorizedUrl("/previlige/no");
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//静态资源不拦截
		filterChainDefinitionMap.put("/assets/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		//登录不拦截，anon 可以理解为不拦截
		filterChainDefinitionMap.put("/login", "anon");

		List<SysPermission> list = sysPermissonService.selectAll();
		for (SysPermission permission: list) {
			filterChainDefinitionMap.put(permission.getUrl(), "perms[" + permission.getPercode() + "]");
		}
		// 其他资源全部拦截
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}

}
