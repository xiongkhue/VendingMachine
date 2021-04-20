/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.dao;

/**
 *
 * @author khuxi
 */
public interface VendingMachineAuditDao {
    public void writeAuditEntry(String entry) 
            throws VendingMachineDaoException;
    //Write data changes/timestamp to audit file
}
