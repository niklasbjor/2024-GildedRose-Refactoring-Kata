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
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;
                }

            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;

                    if (item.sellIn < 11 && item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }

                    if (item.sellIn < 6 && item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                }
            } else {
                if (item.quality > MIN_QUALITY && !item.name.equals(SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }

            if (item.sellIn <= 0) {
                if (item.name.equals(AGED_BRIE)) {
                    if (item.quality < MAX_QUALITY) {
                        item.quality = item.quality + 1;
                    }
                } else if (item.name.equals(BACKSTAGE_PASSES)) {
                    item.quality = 0;
                } else if (item.quality > MIN_QUALITY && !item.name.equals(SULFURAS)) {
                    item.quality = item.quality - 1;
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }
        }
    }
}
