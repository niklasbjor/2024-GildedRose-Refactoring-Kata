package com.gildedrose.domain;

import com.gildedrose.Item;

import java.util.function.Function;

public class Product {
    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    protected final Item item;
    private final Function<Item, Integer> qualityCalculator;
    private final Function<Item, Integer> sellInCalculator;

    public Product(Item item, Function<Item, Integer> qualityCalculator, Function<Item, Integer> sellInCalculator) {
        this.item = item;
        this.qualityCalculator = qualityCalculator;
        this.sellInCalculator = product -> --product.sellIn;
    }

    public final void update() {
        updateQuality();
        updateSellIn();
    }

    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelySetQuality(qualityCalculator.apply(item));
            safelySetQuality(qualityCalculator.apply(item));
        } else {
            safelySetQuality(qualityCalculator.apply(item));
        }
    }

    protected void updateSellIn() {
        item.sellIn = sellInCalculator.apply(item);
    }

    protected boolean isPastSellByDate() {
        return item.sellIn <= 0;
    }

    protected void safelySetQuality(int quality) {
        item.quality = Math.min(Math.max(quality, MIN_QUALITY), MAX_QUALITY); // TODO make readable
    }
}
