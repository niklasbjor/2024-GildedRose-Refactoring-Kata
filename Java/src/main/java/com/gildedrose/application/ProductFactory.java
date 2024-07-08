package com.gildedrose.application;

import com.gildedrose.Item;
import com.gildedrose.domain.Product;

import java.util.function.Function;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public Product createProduct(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> new Product(item, product -> ++product.quality, null);
            case SULFURAS -> new Product(item, product -> product.quality, product -> product.sellIn);
            case BACKSTAGE_PASSES -> new Product(item, backstagePassCalculator(), null);
            default -> new Product(item, product -> --product.quality, null);
        };
    }

    private static Function<Item, Integer> backstagePassCalculator() {
        return item -> {
            if (item.sellIn > 10) {
                return item.quality + 1;
            } else if (item.sellIn > 5) {
                return item.quality + 2;
            } else if (item.sellIn > 0) {
                return item.quality + 3;
            } else {
                return 0;
            }
        };
    }
}
