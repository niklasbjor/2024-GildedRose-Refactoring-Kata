package com.gildedrose.application;

import com.gildedrose.Item;
import com.gildedrose.domain.AgedBrie;
import com.gildedrose.domain.BackstagePasses;
import com.gildedrose.domain.Product;
import com.gildedrose.domain.RegularItem;
import com.gildedrose.domain.Sulfuras;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public Product createProduct(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> new AgedBrie(item);
            case SULFURAS -> new Sulfuras(item);
            case BACKSTAGE_PASSES -> new BackstagePasses(item);
            default -> new RegularItem(item);
        };
    }
}
