/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine;

import com.kx.vendingmachine.controller.VendingMachineController;
import com.kx.vendingmachine.dao.VendingMachineAuditDao;
import com.kx.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.kx.vendingmachine.dao.VendingMachineDao;
import com.kx.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.kx.vendingmachine.service.VendingMachineServiceLayer;
import com.kx.vendingmachine.ui.UserIO;
import com.kx.vendingmachine.ui.UserIOConsoleImpl;
import com.kx.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author khuxi
 */
public class App {
    
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineController controller = new VendingMachineController(myDao, myView);
        controller.run();
    } 
    
}
