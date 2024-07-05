package com.gildedrose.product;

import com.gildedrose.Item;

public abstract class Product {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    private final Item item;

    public Product(Item item) {
        this.item = item;
    }

    public void update() {
        if (isPastSellByDate()) {
            safelyIncreaseQuality(2);
        } else {
            safelyIncreaseQuality(1);
        }
    }

    private boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    private void safelyIncreaseQuality(int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    private void safelyDecreaseQuality(int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }
}
