package com.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity

public class ItemEntity2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5585503187390864060L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="ITEM_NO")
	private int itemNo;
	
	@Column(name="ITEM_ID")
	private int itemId;
	
	@Column(name="ITEM_NAME")
	private  String itemName;
	
	@Column(name="ITEM_DESCRIPTION")
	private  String itemDescription;
	
	@Column(name="ITEM_QUANTITY")
	private  int itemQuantity;
	
	@Column(name="ITEM_UNITPRICE")
	private  int itemUnitPrice;

	@Override
	public String toString() {
		return "ItemObject [itemId=" + itemId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", itemQuantity=" + itemQuantity + ", itemUnitPrice=" + itemUnitPrice + "]";
	}
	public ItemEntity2()
	{
		this.itemNo=0;
		this.itemId=0;
		this.itemName="itemName";
		this.itemDescription="itemDescription";
		this.itemQuantity=0;
		this.itemUnitPrice=0;
	}
	public ItemEntity2(int itemId,String itemName,String itemDescription, int itemQuantity,int itemUnitPrice)
	{
		//this.itemNo=itemNo;
		this.itemId=itemId;
		this.itemName=itemName;
		this.itemDescription=itemDescription;
		this.itemQuantity=itemQuantity;
		this.itemUnitPrice=itemUnitPrice;
	}

	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity2) {
		this.itemQuantity = itemQuantity2;
	}

	public int getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(int itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public <T>List<T> getInventoryList()
	{
		return new ArrayList<T>();
	}
	


}
