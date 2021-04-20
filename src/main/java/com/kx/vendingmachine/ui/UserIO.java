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
    
    void print(String msg);
    void print(String message, BigDecimal c);

    int readInt(String prompt);
    int readInt(String prompt, int min, int max);
    
    String readString(String prompt);
    
    String readStringDoubleUserMoney(String prompt);
}
