package com.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryType implements Serializable {

private static final long serialVersionUID = 1544791292681250611L;


@Id  
@Column(name="INVENTORY_TYPE_ID")	
private Integer inventoryTypeId;

@Column(name="INVENTORY_TYPE")	
private String inventoryType;

public Integer getInventoryTypeId() {
	return inventoryTypeId;
}


public void setInventoryTypeId(Integer inventoryTypeId) {
	this.inventoryTypeId = inventoryTypeId;
}


public String getInventoryType() {
	return inventoryType;
}


public void setInventoryType(String inventoryType) {
	this.inventoryType = inventoryType;
}


}
