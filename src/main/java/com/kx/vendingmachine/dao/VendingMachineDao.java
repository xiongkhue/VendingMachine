/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.dao;

import com.kx.vendingmachine.dto.VendingMachineItem;
import java.util.List;
/**
 *
 * @author khuxi
 */
public interface VendingMachineDao {
    
    /**
     * Adds the given Item to the Vending Machine and associates it with the given 
     * item id/tag. If there is already a item associated with the given 
     * item id it will return that item object, otherwise it will 
     * return null.
     * 
     * @param tag id with which item is to be associated
     * @param vendingItem VendingMachineItem to be added to the Vending Machine
     * @return the Item object previously associated with the given  
     * item id if it exists, null otherwise
     */
    VendingMachineItem addVendingMachineItem(String tag, VendingMachineItem vendingMachineItem)
            throws VendingMachineDaoException;
    
    /**
     * Returns a String array containing the item ids of all 
     * items in the Machine.
     * 
     * @return String array containing the ids of all the items 
     * in the Machine
     */
    List<VendingMachineItem> getAllVendingMachineItems()
            throws VendingMachineDaoException;
    
    /**
     * Returns the item object associated with the given item id.
     * Returns null if no such item exists
     * 
     * @param tag ID of the item to retrieve
     * @return the Item object associated with the given item id,  
     * null if no such item exists
     */
    VendingMachineItem getVendingMachineItem(String tag)
            throws VendingMachineDaoException;
    
    /**
     * @return Item object that was bought or null if no item 
     * was with the given id/tag
     */
    
    VendingMachineItem buyingVendingMachineItem(String tag, VendingMachineItem buying)
            throws VendingMachineDaoException;
    
    /**
     * Removes from the Vending Machine the item associated with the given id. 
     * Returns the item object that is being removed or null if 
     * there is no item associated with the given id
     * 
     * @param tag id of item to be removed
     * @return Item object that was removed or null if no item 
     * was associated with the given item id
     */
    VendingMachineItem removeVendingMachineItem(String tag)
            throws VendingMachineDaoException;
}
