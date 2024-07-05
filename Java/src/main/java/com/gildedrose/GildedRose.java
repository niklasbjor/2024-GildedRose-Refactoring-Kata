package com.gildedrose;

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
                case AGED_BRIE -> updateQualityBrie(item);
                case BACKSTAGE_PASSES -> updateQualityBackstagePasses(item);
                case SULFURAS -> updateQualitySulfuras();
                default -> updateQualityRegularItem(item);
            }

            updateSellIn(item);
        }
    }

    private static void updateQualityRegularItem(Item item) {
        if (item.sellIn > 0) {
            safelyDecreaseQuality(item, 1);
        } else {
            safelyDecreaseQuality(item, 2);
        }
    }

    private static void updateQualityBrie(Item item) {
        if (item.sellIn > 0) {
            safelyIncreaseQuality(item, 1);
        } else {
            safelyIncreaseQuality(item, 2);
        }
    }

    private static void updateQualitySulfuras() {
        // do nothing
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
