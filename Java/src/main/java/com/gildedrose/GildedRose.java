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
            if (item.name.equals(AGED_BRIE)) {
                if (item.sellIn > 0) {
                    safelyIncreaseQuality(item, 1);
                } else {
                    safelyIncreaseQuality(item, 2);
                }

            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn > 10) {
                    safelyIncreaseQuality(item, 1);
                } else if (item.sellIn > 5) {
                    safelyIncreaseQuality(item, 2);
                } else if (item.sellIn > 0) {
                    safelyIncreaseQuality(item, 3);
                } else {
                    item.quality = 0;
                }
            } else if (item.name.equals(SULFURAS)) {
                // do nothing
            } else {
                if (item.sellIn > 0) {
                    safelyDecreaseQuality(item, 1);
                } else {
                    safelyDecreaseQuality(item, 2);
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }

    private static void safelyIncreaseQuality(Item item, int amount) {
        item.quality = Math.min(item.quality + amount, MAX_QUALITY);
    }

    private static void safelyDecreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, MIN_QUALITY);
    }
}
