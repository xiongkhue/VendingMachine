/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.service;

import com.kx.vendingmachine.dao.VendingMachineAuditDao;
import com.kx.vendingmachine.dao.VendingMachineDao;
import com.kx.vendingmachine.dao.VendingMachineDaoException;
import com.kx.vendingmachine.dto.VendingMachineItem;
import java.util.List;

/**
 *
 * @author khuxi
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public VendingMachineItem buyingVendingMachineItem(String tag, VendingMachineItem buying) throws VendingMachineDaoException {
        auditDao.writeAuditEntry(buying.getTag() + ": "+buying.getQuantity()+" remaining in stock");
        return dao.buyingVendingMachineItem(tag, buying);
    }

    @Override
    public VendingMachineItem getVendingMachineItem(String tag) throws VendingMachineDaoException {
        return dao.getVendingMachineItem(tag);
    }

    @Override
    public List<VendingMachineItem> getAllVendingMachineItems() throws VendingMachineDaoException {
        return dao.getAllVendingMachineItems();
    }
    
}
