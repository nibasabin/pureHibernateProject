package com.dao.managers;

import org.springframework.stereotype.Component;

import com.dao.entity.Inventory;
import com.dao.exception.DataBaseException;
import com.dao.exception.SessionFactoryException;
@Component
public interface InventoryManager {

	public void addInventory(Inventory inventory) throws SessionFactoryException, DataBaseException;
	public Integer getInventoryTypeId(String inventoryType) throws DataBaseException;
}