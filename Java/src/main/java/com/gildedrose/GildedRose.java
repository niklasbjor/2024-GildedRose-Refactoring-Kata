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
                safelyIncrementQuality(item);

                if (item.sellIn <= 0) {
                    safelyIncrementQuality(item);
                }

            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                safelyIncrementQuality(item);

                if (item.sellIn < 11)
                    safelyIncrementQuality(item);

                if (item.sellIn < 6)
                    safelyIncrementQuality(item);

                if (item.sellIn <= 0) {
                    item.quality = 0;
                }
            } else if (item.name.equals(SULFURAS)) {
                // do nothing
            } else {
                if (item.quality > MIN_QUALITY) {
                    item.quality = item.quality - 1;
                    if (item.quality > MIN_QUALITY && item.sellIn <= 0) {
                        item.quality = item.quality - 1;
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }

    private static void safelyIncrementQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }
}
