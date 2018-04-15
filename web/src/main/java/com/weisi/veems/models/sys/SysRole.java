package com.weisi.veems.models.sys;

import lombok.Data;

import javax.persistence.Id;

/**
 * 角色
 * @author luomouren
 */
@Data
public class SysRole  implements java.io.Serializable {
    @Id
    private String roleId;

    private String roleName;

    private Boolean isAvailable;

}