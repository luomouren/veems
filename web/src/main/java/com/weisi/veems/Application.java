package com.weisi.veems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 按照官方的建议放在root目录下，这样才能扫描到Service和dao，不然还会引起，扫描不到注解的问题
 * @author luomouren
 */

@ServletComponentScan
@SpringBootApplication
// mapper.java扫描
@MapperScan("com.weisi.veems.mapper")
@ImportResource(locations = { "classpath:druid-bean.xml" })
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}