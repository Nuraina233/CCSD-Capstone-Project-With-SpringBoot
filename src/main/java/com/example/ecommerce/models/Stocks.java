package com.example.ecommerce.models;

public class Stocks {
    private int stocks;
    private int size;

    public Stocks(int stocks, int size){
        this.stocks = stocks;
        this.size = size;
    }

    public int getStocks() {
        return stocks;
    }

    public int getSize() {
        return size;
    }
}
