package com.gildedrose.product;

import com.gildedrose.Item;

public class ConjuredItem extends Product {
    public ConjuredItem(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelyDecreaseQuality(4);
        } else {
            safelyDecreaseQuality(2);
        }

    }
}
