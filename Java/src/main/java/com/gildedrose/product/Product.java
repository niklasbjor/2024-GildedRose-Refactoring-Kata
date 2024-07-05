package com.gildedrose.product;

import com.gildedrose.Item;

public abstract class Product {
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

    protected abstract void updateQuality();

    protected boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    protected void safelyIncreaseQuality(int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    protected void safelyDecreaseQuality(int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }

    protected void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }
}
