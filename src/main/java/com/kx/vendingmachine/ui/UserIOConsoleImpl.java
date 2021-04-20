/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author khuxi
 */
public class UserIOConsoleImpl implements UserIO {

    private ArrayList<Integer> quizScores = new ArrayList<>();
    private double scoreAverage;

    private Scanner sc = new Scanner(System.in);

    public void print(String message) {
        System.out.println(message);
    };
    
    public void print(String message, BigDecimal c) {
        message += c.toString();
        System.out.println(message);
    };
    
    public int readInt(String prompt) {
        print(prompt);
        String temp = sc.nextLine();
        boolean isTrue = false;
        int x;
        while (!isTrue) {
            try {
                x = Integer.parseInt(temp);
                isTrue = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Numbers only beyond this point, enter a int");
                temp = sc.nextLine();
                isTrue = false;
            }
        }
        x = Integer.parseInt(temp);
        return x;
    };
    
    public int readInt(String prompt, int min, int max) {
        print(prompt);
        String temp = sc.nextLine();
        boolean isTrue = false;
        int x;
        while (!isTrue) {
            try {
                x = Integer.parseInt(temp);
                isTrue = true;
            } catch (NumberFormatException e) {
                print("Error: Numbers only beyond this point, enter a int between 1 and 8");
                temp = sc.nextLine();
                isTrue = false;
                continue;
            }
            if (x < min || x > max) {
                print("Error: Numbers only beyond this point, enter a int between 1 and 8");
                temp = sc.nextLine();
                isTrue = false;
            }
        }
        x = Integer.parseInt(temp);
        return x;
    };
    
    public String readString(String prompt) {
        print(prompt);
        String s;
        s = sc.nextLine();
        return s;
    };
    
    public String readStringDoubleUserMoney(String prompt) {
        print(prompt);
        String s;
        s = sc.nextLine();
        boolean isTrue = false;
        double x;
        int y;
        while (!isTrue) {
            if (s.equals("")) {
                s = "0.00";
                isTrue = true;
            } else {
                try {
                    x = Double.parseDouble(s);
                    isTrue = true;
                } catch (NumberFormatException e) {
                    print("Error: Must enter Money, Max $5");
                    s = sc.nextLine();
                    isTrue = false;
                    continue;
                }
                if (x < 0 || x > 5) {
                    print("Error: Must enter Money, Max $5");
                    s = sc.nextLine();
                    isTrue = false;
                    continue;
                }

                try {
                    y = Integer.parseInt(s);
                    s += ".00";
                } catch (NumberFormatException e) {
                    String[] strArray = s.split("\\.");
                    if (strArray[1].length() != 2) {
                        print("Please enter money in acceptable format, Whole[$] or Two-Decimals[$.$$]");
                        s = sc.nextLine();
                        isTrue = false;
                        continue;
                    }
                }
            }
        }
        return s;
    };
}