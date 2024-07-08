package com.gildedrose.domain;

import com.gildedrose.Item;

import java.util.function.Function;

public class Product {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    protected final Item item;
    private final Function<Item, Integer> qualityCalculator;

    public Product(Item item, Function<Item, Integer> qualityCalculator) {
        this.item = item;
        this.qualityCalculator = qualityCalculator;
    }

    public final void update() {
        updateQuality();
        updateSellIn();
    }

    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelySetQuality(qualityCalculator.apply(item));
            safelySetQuality(qualityCalculator.apply(item));
        } else {
            safelySetQuality(qualityCalculator.apply(item));
        }
    }

    protected void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    protected boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    protected void safelySetQuality(int quality) {
        item.quality = Math.min(Math.max(quality, MIN_QUALITY), MAX_QUALITY); // TODO make readable
    }
}
