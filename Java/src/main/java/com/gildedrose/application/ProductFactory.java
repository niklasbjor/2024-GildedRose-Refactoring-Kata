package com.gildedrose.application;

import com.gildedrose.Item;
import com.gildedrose.domain.Product;
import com.gildedrose.domain.QualityCalculator;

public class ProductFactory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String CONJURED = "Conjured Mana Cake";

    public Product createProduct(Item item) {
        return switch (item.name) {
            case AGED_BRIE -> Product.defaultProduct(item)
                    .qualityCalculator(product -> ++product.quality)
                    .build();
            case SULFURAS -> Product.defaultProduct(item)
                    .removeUpperQualityBound()
                    .qualityCalculator(product -> product.quality)
                    .sellInCalculator(product -> product.sellIn)
                    .build();
            case BACKSTAGE_PASSES -> Product.defaultProduct(item)
                    .qualityCalculator(backstagePassCalculator())
                    .build();
            case CONJURED -> Product.defaultProduct(item)
                    .qualityCalculator(product -> product.quality - 2)
                    .build();
            default -> Product.defaultProduct(item)
                    .build();
        };
    }

    private static QualityCalculator backstagePassCalculator() {
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
