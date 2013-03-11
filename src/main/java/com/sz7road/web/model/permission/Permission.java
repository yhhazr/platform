package com.sz7road.web.model.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author hai.yuan
 *
 */
@Entity
@Table(name="permission")
public class Permission {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int parentId;
	private String permName;
	private String permDesc;
	private String permUrl;
	private String orderId;
	private boolean enable;
	private boolean menuShow;
	
	public Permission(){};
	
	public Permission(int parentId, String permName, String permDesc,
			String permUrl, String orderId, boolean enable, boolean isMenu) {
		super();
		this.parentId = parentId;
		this.permName = permName;
		this.permDesc = permDesc;
		this.permUrl = permUrl;
		this.orderId = orderId;
		this.enable = enable;
		this.menuShow = isMenu;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getPermName() {
		return permName;
	}
	
	public void setPermName(String permName) {
		this.permName = permName;
	}
	
	public String getPermDesc() {
		return permDesc;
	}
	
	public void setPermDesc(String permDesc) {
		this.permDesc = permDesc;
	}
	
	public String getPermUrl() {
		return permUrl;
	}
	
	public void setPermUrl(String permUrl) {
		this.permUrl = permUrl;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public boolean isEnable() {
		return enable;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isMenuShow() {
		return menuShow;
	}

	public void setMenuShow(boolean menuShow) {
		this.menuShow = menuShow;
	}
	
	
	
	
	
}
