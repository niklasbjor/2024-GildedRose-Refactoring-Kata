package com.gildedrose.product;

import com.gildedrose.Item;

public class ProductFactory {
    public static Product createProduct(Item item) {
        return new AgedBrie(item);
    }
}
