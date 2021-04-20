/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.controller;

import com.kx.vendingmachine.dao.VendingMachineDao;
import com.kx.vendingmachine.dao.VendingMachineDaoException;
import com.kx.vendingmachine.dto.VendingMachineItem;
import com.kx.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author khuxi
 */
public class VendingMachineController {
    VendingMachineView view;
    VendingMachineDao  dao;
    
    public VendingMachineController(VendingMachineDao  dao, VendingMachineView view) {
    this.dao = dao;
    this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        BigDecimal money;
        boolean choice = false;
        try{
            money = insertMoney();
            while (keepGoing) {
            
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listVendingMachineItems();
                        break;
                    case 2:
                        money = new BigDecimal(select("Pop Tarts", money, choice));
                        break;
                    case 3:
                        money = new BigDecimal(select("Banana Chips", money, choice));
                        break;
                    case 4:
                        money = new BigDecimal(select("Beef Jerky", money, choice));
                        break;
                    case 5:
                        money = new BigDecimal(select("Hot Cheetos", money, choice));
                        break;
                    case 6:
                        money = new BigDecimal(select("Basic Chips", money, choice));
                        break;
                    case 7:
                        money = new BigDecimal(select("BBQ Chips", money, choice));
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommad();
                }

            }
            exitMessage(money);
        } catch (VendingMachineDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listVendingMachineItems() throws VendingMachineDaoException{
        view.displayDisplayAllBanner();
        List<VendingMachineItem> vendingMachineItemList = dao.getAllVendingMachineItems();
        view.displayVendingMachineItemList(vendingMachineItemList);
    }
    
    private BigDecimal insertMoney(){
        BigDecimal money = new BigDecimal(view.getMoney());
        return money;
    }
    
    private String select(String name, BigDecimal money, boolean choice) throws VendingMachineDaoException{
        //view.displaySelectedBanner();
        view.displayItemBanner();
        VendingMachineItem selectedVendingMachineItem = dao.getVendingMachineItem(name);
        choice = view.displayVendingMachineItemAndOptions(selectedVendingMachineItem, money);
        VendingMachineItem buyVendingMachineItem = view.purchaseVendingMachineItem(choice, selectedVendingMachineItem);
        dao.buyingVendingMachineItem(name, buyVendingMachineItem);
        String s = view.updateMoney(money, buyVendingMachineItem, choice);
        return s;
    }
    
    private void exitMessage(BigDecimal money){
        view.changeCalc(money);
        view.displayExitBanner();
    }
    private void unknownCommad(){
        view.displayUnknownCommandBanner();
    }
}
