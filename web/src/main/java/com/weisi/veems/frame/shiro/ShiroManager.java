package com.weisi.veems.frame.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * Shiro Config Manager.
 * 因为shiroConfig中要注入service查询数据库，需要分离开shiro配置，否则会注入失败
 *
 * @author luomouren
 */
public class ShiroManager {

	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 开启注解控制权限的方式，AOP式方法级权限检查
	 *
	 * @return
	 */
	@Bean(name = "defaultAdvisorAutoProxyCreator")
	@ConditionalOnMissingBean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	/**
	 * 开启注解控制权限的方式
	 *
	 * @param securityManager
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
}
