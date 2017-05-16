package com.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Inventory implements Serializable {

private static final long serialVersionUID = -8193344148221900696L;

@GeneratedValue(strategy=GenerationType.AUTO)
@Id
@Column(name="INVENTORY_ID")	
private Integer inventoryId;

//@OneToOne
@Column(name ="INVENTORY_TYPE_ID")
private Integer inventoryTypeId;

@Column(name="INVENTORY_TYPE")
private String inventoryType;

@Column(name="INVENTORY_PRICE")
private Integer inventoryPrice;

@Column(name="INVENTORY_DESCRIPTION")
private String inventoryDescription;

@Column(name="INVENTORY_IMAGE",columnDefinition="LONGBLOB")
private byte[] inventoryImage;

public Integer getInventoryId() {
	return inventoryId;
}
public void setInventoryId(Integer inventoryId) {
	this.inventoryId = inventoryId;
}
public Integer getInventoryTypeId() {
	return inventoryTypeId;
}
public void setInventoryTypeId(Integer inventoryTypeId) {
	this.inventoryTypeId = inventoryTypeId;
}
public String getInventoryDescription() {
	return inventoryDescription;
}
public void setInventoryDescription(String inventoryDescription) {
	this.inventoryDescription = inventoryDescription;
}
public String getInventoryType() {
	return inventoryType;
}
public void setInventoryType(String inventoryType) {
	this.inventoryType = inventoryType;
}
public Integer getInventoryPrice() {
	return inventoryPrice;
}
public void setInventoryPrice(Integer inventoryPrice) {
	this.inventoryPrice = inventoryPrice;
}
public byte[] getInventoryImage() {
	return inventoryImage;
}
public void setInventoryImage(byte[] inventoryImage) {
	this.inventoryImage = inventoryImage;
}

}
