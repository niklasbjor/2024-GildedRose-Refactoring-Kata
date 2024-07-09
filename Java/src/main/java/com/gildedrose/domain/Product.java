package com.gildedrose.domain;

import com.gildedrose.Item;

public class Product {
    private final Item item;
    private final int upperQualityBound;
    private final QualityCalculator qualityCalculator;
    private final SellInCalculator sellInCalculator;

    private Product(Item item, int upperQualityBound, QualityCalculator qualityCalculator, SellInCalculator sellInCalculator) {
        this.item = item;
        this.upperQualityBound = upperQualityBound;
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
        item.quality = withinBounds(qualityCalculator.calculateNewQuality(item));
        if (isPastSellByDate()) {
            item.quality = withinBounds(qualityCalculator.calculateNewQuality(item));
        }
    }

    private int withinBounds(int quality) {
        return Math.clamp(quality, 0, upperQualityBound);
    }

    private void updateSellIn() {
        item.sellIn = sellInCalculator.calculateNewSellIn(item);
    }

    private boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    public static class ProductBuilder {
        public static final QualityCalculator decrementQuality = item -> item.quality - 1;
        public static final SellInCalculator decrementSellIn = item -> item.sellIn - 1;
        public static final int DEFAULT_UPPER_QUALITY_BOUND = 50;

        private final Item item;
        private int upperQualityBound;
        private QualityCalculator qualityCalculator;
        private SellInCalculator sellInCalculator;

        private ProductBuilder(Item item) {
            this.item = item;
            this.upperQualityBound = DEFAULT_UPPER_QUALITY_BOUND;
            this.qualityCalculator = decrementQuality;
            this.sellInCalculator = decrementSellIn;
        }

        public ProductBuilder removeUpperQualityBound() {
            this.upperQualityBound = Integer.MAX_VALUE;
            return this;
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
            return new Product(item, upperQualityBound, qualityCalculator, sellInCalculator);
        }
    }
}
