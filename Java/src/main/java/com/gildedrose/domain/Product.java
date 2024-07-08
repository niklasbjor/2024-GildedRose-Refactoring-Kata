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

    /**
     * Do the update of the "quality" property of the item.
     * To be overridden in each concrete subclass.
     */
    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelySetQuality(qualityCalculator.apply(item));
            safelySetQuality(qualityCalculator.apply(item));
        } else {
            safelySetQuality(qualityCalculator.apply(item));
        }
    }

    /**
     * Do the update of the "sellIn" property of the item.
     * This default behaviour may be overridden in a concrete subclass.
     */
    protected void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    protected boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    protected void safelyIncreaseQuality(int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    protected void safelyDecreaseQuality(int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }

    protected void safelySetQuality(int quality) {
        item.quality = Math.min(Math.max(quality, MIN_QUALITY), MAX_QUALITY); // TODO make readable
    }
}
