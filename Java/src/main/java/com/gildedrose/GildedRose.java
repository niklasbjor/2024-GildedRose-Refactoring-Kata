package com.gildedrose;

import com.gildedrose.product.Product;
import com.gildedrose.product.ProductFactory;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = ProductFactory.createProduct(item);
            product.update();
        }
    }

}
