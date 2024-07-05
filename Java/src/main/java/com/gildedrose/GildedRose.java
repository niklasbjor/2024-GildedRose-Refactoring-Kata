package com.gildedrose;

import com.gildedrose.product.AgedBrie;
import com.gildedrose.product.Product;
import com.gildedrose.product.RegularItem;
import com.gildedrose.product.Sulfuras;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case AGED_BRIE -> updateBrie(item);
                case BACKSTAGE_PASSES -> {
                    updateQualityBackstagePasses(item);
                    updateSellIn(item);
                }
                case SULFURAS -> updateSulfuras(item);
                default -> updateRegularItem(item);
            }
        }
    }

    private static void updateRegularItem(Item item) {
        Product product = new RegularItem(item);
        product.update();
    }

    private static void updateBrie(Item item) {
        Product product = new AgedBrie(item);
        product.update();
    }

    private static void updateSulfuras(Item item) {
        Product product = new Sulfuras(item);
        product.update();
    }

    private static void updateQualityBackstagePasses(Item item) {
        if (item.sellIn > 10) {
            safelyIncreaseQuality(item, 1);
        } else if (item.sellIn > 5) {
            safelyIncreaseQuality(item, 2);
        } else if (item.sellIn > 0) {
            safelyIncreaseQuality(item, 3);
        } else {
            item.quality = 0;
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

    private static void updateSellIn(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }
}
