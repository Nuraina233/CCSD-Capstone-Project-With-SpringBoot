package com.example.ecommerce.models;

public class Stocks {
    private int stock_id;
    private int product_id;
    private double size;
    private int stocks;

    public Stocks(int stock_id, int product_id, double size, int stocks){
        this.stock_id = stock_id;
        this.product_id = product_id;
        this.size = size;
        this.stocks = stocks;
    }

    public int getStockId() {
        return stock_id;
    }

    public int getProductId() {
        return product_id;
    }

    public int getStocks() {
        return stocks;
    }

    public double getSizes() {
        return size;
    }
}
