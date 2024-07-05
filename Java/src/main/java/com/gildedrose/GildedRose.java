package com.gildedrose;

import com.gildedrose.product.Product;
import com.gildedrose.product.ProductFactory;
import com.gildedrose.product.RegularItem;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case AGED_BRIE -> {
                    Product product = ProductFactory.createProduct(item);
                    updateBrie(product);
                }
                case BACKSTAGE_PASSES -> {
                    Product product = ProductFactory.createProduct(item);
                    updateBackstagePasses(product);
                }
                case SULFURAS -> {
                    Product product = ProductFactory.createProduct(item);
                    updateSulfuras(product);
                }
                default -> updateRegularItem(item);
            }
        }
    }

    private static void updateRegularItem(Item item) {
        Product product = new RegularItem(item);
        product.update();
    }

    private static void updateBrie(Product product) {
        product.update();
    }

    private static void updateSulfuras(Product product) {
        product.update();
    }

    private static void updateBackstagePasses(Product product) {
        product.update();
    }
}
