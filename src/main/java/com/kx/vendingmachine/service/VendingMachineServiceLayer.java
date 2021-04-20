/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.service;

import com.kx.vendingmachine.dao.VendingMachineDaoException;
import com.kx.vendingmachine.dto.VendingMachineItem;
import java.util.List;

/**
 *
 * @author khuxi
 */
public interface VendingMachineServiceLayer {
    
    VendingMachineItem buyingVendingMachineItem(String name, VendingMachineItem buying)
        throws VendingMachineDaoException;
    
    VendingMachineItem getVendingMachineItem(String tag)
        throws VendingMachineDaoException;
    
    List<VendingMachineItem> getAllVendingMachineItems()
            throws VendingMachineDaoException;
    
    
}
