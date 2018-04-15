package com.weisi.veems.models.sys;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;

/**
 * 用户角色表
 *
 * @author luomouren
 */
@Data
public class SysMapUserRole implements java.io.Serializable {
	private String userId;
	private String roleId;

}