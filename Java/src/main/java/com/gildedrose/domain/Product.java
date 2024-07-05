package com.gildedrose.domain;

import com.gildedrose.Item;

public class Product {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    protected final Item item;

    public Product(Item item) {
        this.item = item;
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
            safelyDecreaseQuality(2);
        } else {
            safelyDecreaseQuality(1);
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
}
