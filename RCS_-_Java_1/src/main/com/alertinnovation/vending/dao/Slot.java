package com.alertinnovation.vending.dao;

public class Slot {
    private Item item;
    private  int itemCount;

    /**
     * This constructor initializes Slot with item and default slot size of 15
     * @param item
     */
    public Slot(Item item){
        this.item = item;
        this.itemCount = 15;
    }

    public Item getItem() {
        return item;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "item=" + item +
                ", itemCount=" + itemCount +
                '}';
    }
}
