package com.dao.managers;

import org.springframework.stereotype.Component;

import com.dao.entity.Inventory;
@Component
public interface InventoryManager {

	public void addInventory(Inventory inventory);
	public Integer getInventoryTypeId(String inventoryType);
}