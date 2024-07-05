package com.gildedrose;

import com.gildedrose.domain.Product;
import com.gildedrose.application.ProductFactory;

class GildedRose {

    Item[] items;
    private final ProductFactory productFactory;

    public GildedRose(Item[] items) {
        this.items = items;
        this.productFactory = new ProductFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            Product product = productFactory.createProduct(item);
            product.update();
        }
    }

}
