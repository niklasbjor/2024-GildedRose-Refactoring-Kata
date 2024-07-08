package com.gildedrose.domain;

import com.gildedrose.Item;
import com.gildedrose.application.ProductFactory;

public class Product {
    private final Item item;
    private final QualityCalculator qualityCalculator;
    private final SellInCalculator sellInCalculator;

    public Product(Item item, QualityCalculator qualityCalculator, SellInCalculator sellInCalculator) {
        this.item = item;
        this.qualityCalculator = qualityCalculator;
        this.sellInCalculator = sellInCalculator;
    }

    public final void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (!item.name.equals(ProductFactory.SULFURAS)) {
            if (isPastSellByDate()) {
                item.quality = qualityCalculator.apply(item);
                item.quality = qualityCalculator.apply(item);
            } else {
                item.quality = qualityCalculator.apply(item);
            }
        }
    }

    private void updateSellIn() {
        item.sellIn = sellInCalculator.apply(item);
    }

    private boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }
}
