package com.weisi.veems.frame.init;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * Freemarker + shiro 权限控制文件
 *
 * @author luomouren
 */
@Component
public class FreeMarkerConfig  {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@PostConstruct
	public void setSharedVariable() throws TemplateModelException {
		freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}
