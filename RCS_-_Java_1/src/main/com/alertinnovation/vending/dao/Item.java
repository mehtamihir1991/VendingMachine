package com.alertinnovation.vending.dao;

public class Item {
    private String name;
    private Integer sku;
    private Double price;

    /**
     * This contructor initializes items with name, sku and price
     * @param itemName
     * @param sku
     * @param price
     */
    public Item(String itemName, Integer sku, Double price){
        this.name = itemName;
        this.sku = sku;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sku=" + sku +
                ", price=" + price +
                '}';
    }
}
