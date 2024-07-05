package com.gildedrose.product;

import com.gildedrose.Item;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED_ITEM = "Conjured Mana Cake";

    public static Product createProduct(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> new AgedBrie(item);
            case SULFURAS -> new Sulfuras(item);
            case BACKSTAGE_PASSES -> new BackstagePasses(item);
            case CONJURED_ITEM -> new ConjuredItem(item);
            default -> new RegularItem(item);
        };
    }
}
