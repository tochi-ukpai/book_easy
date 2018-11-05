/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ysg.util;

import com.ysg.data.Seat;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tochukwu
 */
public class ShoppingCart implements Serializable {
    private List<Seat> items;
    private int numberOfItems;
    private double grandTotal;

    public ShoppingCart(){
        this.items = new ArrayList<>();
        this.numberOfItems = 0;
        this.grandTotal = 0;
    }

    public synchronized void add(Seat seat){
        items.add(seat);
    }

    public synchronized void update(Seat seat, int quantity){
        
    }

    public synchronized List<Seat> getItems(){
        return items;
    }

    public synchronized int getNumberOfItems(){
        numberOfItems = 0;
        for (Seat cItem : items) {
            numberOfItems += 1;
        }
        return numberOfItems;
    }

    public synchronized double getGrandTotal(){
        grandTotal = 0;
        items.forEach((cItem) -> {
            grandTotal += cItem.getPrice();
        });
        return to2Dec(grandTotal);
    }

    public synchronized void remove(Seat seat){
        if (seat != null) {
            items.remove(seat);
        }
    }


    public synchronized void clear(){
        items.clear();
        numberOfItems = 0;
        grandTotal = 0;
    }

    private static double to2Dec(double num){
        DecimalFormat fmt = new DecimalFormat("###.##");
        return Double.parseDouble(fmt.format(num));
    }
}
