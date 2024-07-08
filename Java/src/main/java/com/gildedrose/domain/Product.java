package com.gildedrose.domain;

import com.gildedrose.Item;

import static com.gildedrose.application.ProductFactory.MAX_QUALITY;
import static com.gildedrose.application.ProductFactory.MIN_QUALITY;

public class Product {
    private final Item item;
    private final QualityCalculator qualityCalculator;
    private final SellInCalculator sellInCalculator;

    private Product(Item item, QualityCalculator qualityCalculator, SellInCalculator sellInCalculator) {
        this.item = item;
        this.qualityCalculator = qualityCalculator;
        this.sellInCalculator = sellInCalculator;
    }

    public static ProductBuilder builder(Item item) {
        return new ProductBuilder(item);
    }

    public final void update() {
        updateQuality();
        updateSellIn();
    }

    private void updateQuality() {
        if (isPastSellByDate()) {
            item.quality = qualityCalculator.calculateNewQuality(item);
            item.quality = qualityCalculator.calculateNewQuality(item);
        } else {
            item.quality = qualityCalculator.calculateNewQuality(item);
        }
    }

    private void updateSellIn() {
        item.sellIn = sellInCalculator.calculateNewSellIn(item);
    }

    private boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    public static class ProductBuilder {
        public static final QualityCalculator decrementQuality = item -> withinBounds(item.quality - 1);
        public static final SellInCalculator decrementSellIn = item -> item.sellIn - 1;

        private final Item item;
        private QualityCalculator qualityCalculator;
        private SellInCalculator sellInCalculator;

        private ProductBuilder(Item item) {
            this.item = item;
            this.qualityCalculator = decrementQuality;
            this.sellInCalculator = decrementSellIn;
        }

        public ProductBuilder qualityCalculator(QualityCalculator qualityCalculator) {
            this.qualityCalculator = qualityCalculator;
            return this;
        }

        public ProductBuilder sellInCalculator(SellInCalculator sellInCalculator) {
            this.sellInCalculator = sellInCalculator;
            return this;
        }

        public Product build() {
            return new Product(item, qualityCalculator, sellInCalculator);
        }

        private static int withinBounds(int quality) {
            return Math.clamp(quality, MIN_QUALITY, MAX_QUALITY); // TODO dedup
        }
    }
}
