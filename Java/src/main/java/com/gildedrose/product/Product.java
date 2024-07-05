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
        if (hasSellByDatePassed(item)) {
            safelyIncreaseQuality(item, 2);
        } else {
            safelyIncreaseQuality(item, 1);
        }

    }

    private static boolean hasSellByDatePassed(Item item) {
        return item.sellIn <= 0;
    }

    private static void safelyIncreaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    private static void safelyDecreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }
}
