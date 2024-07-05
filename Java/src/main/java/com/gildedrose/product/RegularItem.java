package com.gildedrose.product;

import com.gildedrose.Item;

public class RegularItem extends Product {
    public RegularItem(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (isPastSellByDate()) {
            safelyDecreaseQuality(2);
        } else {
            safelyDecreaseQuality(1);
        }
    }
}
