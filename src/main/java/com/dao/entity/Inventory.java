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

@Column(name="INVENTORY_CATEGORY")
private String inventoryCategory;

@Column(name="INVENTORY_TYPE")
private String inventoryType;

@Column(name="INVENTORY_PRICE")
private Double inventoryPrice;

@Column(name="INVENTORY_DESCRIPTION")
private String inventoryDescription;

@Column(name="INVENTORY_IMAGE",columnDefinition="LONGBLOB")
private byte[] inventoryImage;

@Column(name="USED_INVENTORY")
private Boolean usedInventory;

@Column(name = "NEW_INVENTORY")
private Boolean newInventory;

@Column(name ="SELLER_NAME")
private String sellerName;

@Column(name="ITEM_SOLD")
private Boolean itemSold;

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

public String getInventoryCategory() {
	return inventoryCategory;
}

public void setInventoryCategory(String inventoryCategory) {
	this.inventoryCategory = inventoryCategory;
}

public String getInventoryType() {
	return inventoryType;
}

public void setInventoryType(String inventoryType) {
	this.inventoryType = inventoryType;
}

public Double getInventoryPrice() {
	return inventoryPrice;
}

public void setInventoryPrice(Double inventoryPrice) {
	this.inventoryPrice = inventoryPrice;
}

public String getInventoryDescription() {
	return inventoryDescription;
}

public void setInventoryDescription(String inventoryDescription) {
	this.inventoryDescription = inventoryDescription;
}

public byte[] getInventoryImage() {
	return inventoryImage;
}

public void setInventoryImage(byte[] inventoryImage) {
	this.inventoryImage = inventoryImage;
}

public Boolean getUsedInventory() {
	return usedInventory;
}

public void setUsedInventory(Boolean usedInventory) {
	this.usedInventory = usedInventory;
}

public Boolean getNewInventory() {
	return newInventory;
}

public void setNewInventory(Boolean newInventory) {
	this.newInventory = newInventory;
}

public String getSellerName() {
	return sellerName;
}

public void setSellerName(String sellerName) {
	this.sellerName = sellerName;
}

public Boolean getItemSold() {
	return itemSold;
}

public void setItemSold(Boolean itemSold) {
	this.itemSold = itemSold;
}


}
