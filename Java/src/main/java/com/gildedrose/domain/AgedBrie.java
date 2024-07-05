package com.gildedrose.domain;

import com.gildedrose.Item;

public class AgedBrie extends Product {
    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelyIncreaseQuality(2);
        } else {
            safelyIncreaseQuality(1);
        }
    }
}
