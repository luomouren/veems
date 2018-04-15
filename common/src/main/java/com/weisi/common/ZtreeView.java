package com.weisi.common;

import lombok.Data;

import java.io.Serializable;

/**
 * ztree树
 *
 * @author luomouren
 */
@Data
public class ZtreeView implements Serializable {
	private static final long serialVersionUID = 6237809780035784312L;

	private String id;

	private String pId;

	private String name;

	private boolean open;

	private boolean checked = false;

	public ZtreeView() {
	}

	public ZtreeView(String id, String pId, String name, boolean open) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}


	public ZtreeView(String id, String pId, String name, boolean open, boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
		this.checked = checked;
	}
}
