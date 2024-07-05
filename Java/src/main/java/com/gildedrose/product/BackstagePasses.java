package com.gildedrose.product;

import com.gildedrose.Item;

public class BackstagePasses extends Product {
    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (item.sellIn > 10) {
            safelyIncreaseQuality(1);
        } else if (item.sellIn > 5) {
            safelyIncreaseQuality(2);
        } else if (item.sellIn > 0) {
            safelyIncreaseQuality(3);
        } else {
            item.quality = 0;
        }
    }
}
