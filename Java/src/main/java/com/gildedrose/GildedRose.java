package com.gildedrose;

import com.gildedrose.product.BackstagePasses;
import com.gildedrose.product.Product;
import com.gildedrose.product.ProductFactory;
import com.gildedrose.product.RegularItem;
import com.gildedrose.product.Sulfuras;

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
                case BACKSTAGE_PASSES -> updateBackstagePasses(item);
                case SULFURAS -> updateSulfuras(item);
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

    private static void updateSulfuras(Item item) {
        Product product = new Sulfuras(item);
        product.update();
    }

    private static void updateBackstagePasses(Item item) {
        Product product = new BackstagePasses(item);
        product.update();
    }
}
