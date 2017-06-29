package com.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GlobalInventory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6915272288489222125L;
	@Id
	@Column(name="INVENTORY_ID")
	private Integer inventoryId;

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	
}
