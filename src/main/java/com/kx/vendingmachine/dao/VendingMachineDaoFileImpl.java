/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.dao;

import com.kx.vendingmachine.dto.VendingMachineItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author khuxi
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    
    private Map<String, VendingMachineItem> vendingItems = new HashMap<>();
    public static final String DVDCOLLECTION_FILE = "vendingitems";
    public static final String DELIMITER = "::";

    @Override
    public VendingMachineItem addVendingMAchineItem(String tag, VendingMachineItem vendingItem) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VendingMachineItem> getAllVendingMachineItems() throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VendingMachineItem getVendingMachineItem(String tag) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VendingMachineItem buyingVendingMachineItem(String tag, VendingMachineItem buying) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VendingMachineItem removeVendingMachineItem(String tag) throws VendingMachineDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
