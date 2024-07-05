package com.gildedrose.product;

import com.gildedrose.Item;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";

    public static Product createProduct(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrie(item);
        } else {
            return new BackstagePasses(item);
        }
    }
}
