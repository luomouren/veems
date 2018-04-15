package com.weisi.veems.models.sys;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

/**
 * 用户表
 *
 * @author luomouren
 */
@Data
public class SysUser implements java.io.Serializable {
	@Id
	private String userId;

	private String realName;

	private String userName;

	private String password;

	private Integer userCategory;

	private String positionJob;

	private String email;

	private String cellphone;

	private String tel;

	private String salt;

	private Boolean isLocked;

	/**
	 * 用户权限下所有的导航权限
	 */
	@Transient
	private String menuJson;
}