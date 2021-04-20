/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.ui;

import java.math.BigDecimal;

/**
 *
 * @author khuxi
 */
public interface UserIO {
    
    //UserIO/View print functionality
    void print(String msg);
    void print(String message, BigDecimal c);

    //Read in user input when expecting int
    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    
    //Read in string
    String readString(String prompt);
    
    //Read in user input for money (0.00-5.00)
    String readStringDoubleUserMoney(String prompt);
}
