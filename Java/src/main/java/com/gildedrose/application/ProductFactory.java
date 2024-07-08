package com.gildedrose.application;

import com.gildedrose.Item;
import com.gildedrose.domain.Product;
import com.gildedrose.domain.QualityCalculator;
import com.gildedrose.domain.SellInCalculator;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    public static final SellInCalculator decrementSellIn = item -> --item.sellIn;

    public Product createProduct(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> Product.builder(item)
                    .qualityCalculator(product -> withinBounds(++product.quality))
                    .build();
            case SULFURAS -> new Product(item, product -> product.quality, product -> product.sellIn);
            case BACKSTAGE_PASSES -> new Product(item, backstagePassCalculator(), decrementSellIn);
            default -> Product.builder(item)
                    .build();
        };
    }

    private static QualityCalculator backstagePassCalculator() {
        return item -> {
            if (item.sellIn > 10) {
                return withinBounds(item.quality + 1);
            } else if (item.sellIn > 5) {
                return withinBounds(item.quality + 2);
            } else if (item.sellIn > 0) {
                return withinBounds(item.quality + 3);
            } else {
                return 0;
            }
        };
    }

    private static int withinBounds(int quality) {
        return Math.clamp(quality, MIN_QUALITY, MAX_QUALITY);
    }
}
