package com.gildedrose.domain;

import com.gildedrose.Item;

import java.util.function.Function;

public interface QualityCalculator extends Function<Item, Integer> {
}
