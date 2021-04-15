/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author khuxi
 */
public class VendingMachineItem {
    private String tag;
    private BigDecimal price;
    private int quantity;

    //VendingMachineItem tag/object's associate id
    public VendingMachineItem(String tag) {
        this.tag = tag;
    }
    
    //Get VendingMachineItem tag/object's associate id
    public String getTag() {
        return tag;
    }

    //Get VendingMachineItem Price
    public BigDecimal getPrice() {
        return price;
    }

    //Set VendingMachineItem Price
    public void setPrice(String price) {
        this.price = new BigDecimal(price);
    }

    //Get VendingMachineItem Quantity
    public int getQuantity() {
        return quantity;
    }

    //Set VendingMachineItem Quantity
    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }
    
    
    public enum Coin {
        PENNY(".01"), NICKEL(".05"), DIME(".10"), QUARTER(".25");
        
        public final String label;
 
        private Coin(String label) {
            this.label = label;
        }
    }
}
