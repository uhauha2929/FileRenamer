package com.example;

import javax.swing.*;
import java.util.Enumeration;

public class UIManagerDemo {

    public static void printUIManagerKeys(String filter) {

        String filterToLowerCase = filter.toLowerCase();

        Enumeration<?> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {

            Object key = keys.nextElement();
            String keyToString = key.toString().toLowerCase();

            if (keyToString.contains(filterToLowerCase)) {
                System.out.println(key + " ( " + UIManager.getDefaults().get(key) + " )");
            }
        }
    }

    public static void main(String[] args) {
        printUIManagerKeys("close");
    }
}
