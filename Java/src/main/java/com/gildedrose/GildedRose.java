package com.gildedrose;

import java.util.Arrays;
import java.util.Comparator;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED_ITEM = "Conjured Mana Cake";
    public static final String CONJURED_BRIE = "Conjured Brie";
    public static final String CURSED_ITEM = "Cursed crown";
    public static final String AGEING_POTION = "Ageing potion";
    public static final String INSURANCE = "Valuable item insurance";

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .sorted(insurancesLast()) // make sure all other items have updated before updating any insurances
                .forEach(item -> {
                    updateItemQuality(item);
                    updateSellIn(item);
                });
    }

    private static Comparator<Item> insurancesLast() { // Note: inconsistent with equals; use with care!
        return (item1, item2) -> item1.name.equals(INSURANCE) ? 1 : -1;
    }

    private void updateItemQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE -> updateQualityBrie(item);
            case BACKSTAGE_PASSES -> updateQualityBackstagePasses(item);
            case SULFURAS -> updateQualitySulfuras(item);
            case CONJURED_ITEM -> updateQualityConjuredItem(item);
            case CONJURED_BRIE -> updateQualityConjuredBrie(item);
            case CURSED_ITEM -> updateQualityCursedItem(item);
            case AGEING_POTION -> updateQualityAgeingPotion(item);
            case INSURANCE -> updateQualityInsurance(item, items);
            default -> updateQualityRegularItem(item);
        }
    }

    private static void updateQualityRegularItem(Item item) {
        if (hasSellByDatePassed(item)) {
            safelyDecreaseQuality(item, 2);
        } else {
            safelyDecreaseQuality(item, 1);
        }
    }

    private static void updateQualityBrie(Item item) {
        if (hasSellByDatePassed(item)) {
            safelyIncreaseQuality(item, 2);
        } else {
            safelyIncreaseQuality(item, 1);
        }
    }

    private static void updateQualitySulfuras(Item item) {
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

    private static void updateQualityConjuredItem(Item item) {
        if (hasSellByDatePassed(item)) {
            safelyDecreaseQuality(item, 4);
        } else {
            safelyDecreaseQuality(item, 2);
        }
    }

    private static void updateQualityConjuredBrie(Item item) {
        if (hasSellByDatePassed(item)) {
            safelyIncreaseQuality(item, 4);
        } else {
            safelyIncreaseQuality(item, 2);
        }
    }

    private static void updateQualityCursedItem(Item item) {
        if (hasSellByDatePassed(item)) {
            item.quality = item.quality - 2;
        } else {
            item.quality = item.quality - 1;
        }
    }

    private static void updateQualityAgeingPotion(Item item) {
        if (item.sellIn % 2 == 0) {
            return;
        }

        if (hasSellByDatePassed(item)) {
            safelyDecreaseQuality(item, 2);
        } else {
            safelyDecreaseQuality(item, 1);
        }
    }

    private static void updateQualityInsurance(Item insurance, Item[] items) {
        if (hasSellByDatePassed(insurance)) {
            insurance.quality = 0;
        } else {
            long amountOfValuableItems = Arrays.stream(items)
                    .filter(item -> !item.name.equals(INSURANCE))
                    .filter(item -> item.quality >= 30)
                    .count();
            insurance.quality = Math.toIntExact(amountOfValuableItems);
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
