package com.gildedrose.product;

import com.gildedrose.Item;

public class Sulfuras extends Product {
    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        // Sulfuras never has its quality altered
    }

    @Override
    public void updateSellIn() {
        // Sulfuras never has to be sold
    }
}
