package com.dao.managers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.entity.FilterCriteriaObject;
import com.dao.entity.Inventory;
import com.dao.exception.DataBaseException;
import com.dao.exception.SessionFactoryException;
@Component
public interface InventoryManager {

	public void addInventory(Inventory inventory) throws SessionFactoryException, DataBaseException;
	public Integer getInventoryTypeId(String inventoryType) throws DataBaseException;
	public List< Inventory > getAllInventory();
	public List< Inventory > getUsersInventory(String userName);
	public List< Inventory > getFilteredInventory(FilterCriteriaObject filterCriteriaObject);
	public void addToGlobalInventory(Integer itemNo) throws DataBaseException;
	public void deleteItem(Integer itemNo) throws DataBaseException;
}