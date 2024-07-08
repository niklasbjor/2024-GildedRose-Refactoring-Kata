package com.gildedrose.domain;

import com.gildedrose.Item;
import com.gildedrose.application.ProductFactory;

import static com.gildedrose.application.ProductFactory.MAX_QUALITY;
import static com.gildedrose.application.ProductFactory.MIN_QUALITY;

public class Product {
    private final Item item;
    private final QualityCalculator qualityCalculator;
    private final SellInCalculator sellInCalculator;

    public Product(Item item, QualityCalculator qualityCalculator, SellInCalculator sellInCalculator) {
        this.item = item;
        this.qualityCalculator = qualityCalculator;
        this.sellInCalculator = sellInCalculator;
    }

    public static Product.Builder builder(Item item) {
        return new Builder(item);
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

    public static class Builder {
        public static final QualityCalculator decrementQuality = item -> withinBounds(item.quality - 1);
        public static final SellInCalculator decrementSellIn = item -> item.sellIn - 1;

        private final Item item;
        private QualityCalculator qualityCalculator;
        private SellInCalculator sellInCalculator;

        private Builder(Item item) {
            this.item = item;
            this.qualityCalculator = decrementQuality;
            this.sellInCalculator = decrementSellIn;
        }

        public Product build() {
            return new Product(item, qualityCalculator, sellInCalculator);
        }

        private static int withinBounds(int quality) {
            return Math.clamp(quality, MIN_QUALITY, MAX_QUALITY); // TODO dedup
        }
    }
}
