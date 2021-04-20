/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.ui;

import com.kx.vendingmachine.dto.VendingMachineItem;
import com.kx.vendingmachine.dto.VendingMachineItem.Coin;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author khuxi
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. List VendingItems");
        io.print("2. Pop Tarts");
        io.print("3. Banana Chips");
        io.print("4. Beef Jerky");
        io.print("5. Hot Cheetos");
        io.print("6. Basic Chips");
        io.print("7. BBQ Chips");
        io.print("8. Exit");
        
        return io.readInt("Please select from the above choices.", 1, 8);
    }
    
    public void displayVendingMachineItemList(List<VendingMachineItem> vendingMachineItemList) {
        for (VendingMachineItem currentVendingMachineItem : vendingMachineItemList) {
            io.print(currentVendingMachineItem.getTag() + ": "
                + currentVendingMachineItem.getPrice() + " "
                + currentVendingMachineItem.getQuantity());
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayDisplayAllBanner() {
        io.print("=== All Vending Items ===");
    }
    
    public void displayTransactionSuccessBanner() {
        io.print("Successfull Vending Item Transaction.");
        io.print("");
        io.readString("Please hit enter to continue.");
    }
    
    public String getMoney() {
        return io.readStringDoubleUserMoney("Please insert Money");
    }
    
    public boolean displayVendingMachineItemAndOptions(VendingMachineItem vendingMachineItem, BigDecimal money) {
        if(vendingMachineItem.getQuantity() > 0) {
            io.print(vendingMachineItem.getTag());
            io.print("Price: ", vendingMachineItem.getPrice());
            if((money.subtract(vendingMachineItem.getPrice()).compareTo(BigDecimal.ZERO)) > 0){
                io.print("Change: ", money.subtract(vendingMachineItem.getPrice()));
                if(io.readString("Purchase? [y/n]").equalsIgnoreCase("y")){
                    return true;
                } else
                    return false;
            } else
                io.print("Not Enough Funds");
            io.print("");
            io.readString("Please press Enter to continue");
            return false;
        } else {
            io.print("Out of "+vendingMachineItem.getTag());
            io.readString("Please press Enter to continue");
            return false;
        }
    }
    
    public void changeCalc(BigDecimal money){
            Coin coin = Coin.QUARTER;
            int quarterCount = 0;
            int dimeCount = 0;
            int nickelCount = 0;
            int pennyCount = 0;
            String sChange;
            BigDecimal change = money;
            BigDecimal looseChange;
            while ((change.compareTo(BigDecimal.ZERO)) > 0) {
                String x = (coin.DIME.label);
                if ((looseChange = new BigDecimal(coin.QUARTER.label)).compareTo(change) == -1 || (looseChange = new BigDecimal(coin.QUARTER.label)).compareTo(change) == 0) {
                    change = change.subtract(looseChange);
                    quarterCount++;
                } else if ((looseChange = new BigDecimal(coin.DIME.label)).compareTo(change) == -1 || (looseChange = new BigDecimal(coin.DIME.label)).compareTo(change) == 0) {
                    change = change.subtract(looseChange);
                    dimeCount++;
                } else if ((looseChange = new BigDecimal(coin.NICKEL.label)).compareTo(change) == -1 || (looseChange = new BigDecimal(coin.NICKEL.label)).compareTo(change) == 0) {
                    change = change.subtract(looseChange);
                    nickelCount++;
                } else if ((looseChange = new BigDecimal(coin.PENNY.label)).compareTo(change) == -1 || (looseChange = new BigDecimal(coin.PENNY.label)).compareTo(change) == 0) {
                    change = change.subtract(looseChange);
                    pennyCount++;
                }
            }
            sChange = "Here is your change in coins: " + quarterCount + " Quarters, " + dimeCount + " Dimes, " + nickelCount + " Nickels, and " + pennyCount + " Pennies"
                    + "\nTotal Change: " + money;
            io.print(sChange);
    }
    
    public String updateMoney(BigDecimal money, VendingMachineItem vendingMachineItem, boolean choice){
        String s = money.toString();
        if(!choice){
            
        } else {
        s = money.subtract(vendingMachineItem.getPrice()).toString();
        }
        io.print("Remaining budget: "+s);
        return s;
    }
    
    public VendingMachineItem purchaseVendingMachineItem(boolean choice, VendingMachineItem vendingMachineItem) {
        if(!choice){
            return vendingMachineItem;
        } else {
            String updateQuantity = Integer.toString(vendingMachineItem.getQuantity()-1);
            vendingMachineItem.setQuantity(updateQuantity);
            displayTransactionSuccessBanner();

            return vendingMachineItem;
        }
    }
    
    public void displayVendingItem(VendingMachineItem vendingItem) {
        io.print(vendingItem.getTag());
        io.print(vendingItem.getPrice().toString());
        io.print("");
        io.readString("Please hit enter to continue.");
    }
    public void displayItemBanner() {
        io.print("=== Selected Vending Item ===");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
