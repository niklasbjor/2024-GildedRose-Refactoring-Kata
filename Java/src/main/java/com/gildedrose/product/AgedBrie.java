package com.gildedrose.product;

import com.gildedrose.Item;

public class AgedBrie extends Product {
    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void updateQuality() {
        if (isPastSellByDate()) {
            safelyIncreaseQuality(2);
        } else {
            safelyIncreaseQuality(1);
        }
    }
}
