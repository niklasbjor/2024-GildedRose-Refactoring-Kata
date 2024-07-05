package com.gildedrose.product;

import com.gildedrose.Item;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";


    public static Product createProduct(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrie(item);
        } else if (item.name.equals(SULFURAS)) {
        return new Sulfuras(item);
        } else {
            return new BackstagePasses(item);
        }
    }
}
