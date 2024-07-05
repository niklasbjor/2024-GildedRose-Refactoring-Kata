package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void regularItem_updateQuality_decrementSellInAndQuality() {
        Item regularItem = new Item("+5 Dexterity Vest", 8, 8);
        GildedRose app = new GildedRose(new Item[] {regularItem});

        app.updateQuality();

        assertThat(regularItem.sellIn).isEqualTo(7);
        assertThat(regularItem.quality).isEqualTo(7); // TODO separate sellIn from quality assertions? (check test names afterward)
    }

    @Test
    void agedBrie_updateQuality_incrementQuality() {
        Item brie = new Item("Aged Brie", 8, 8);
        GildedRose app = new GildedRose(new Item[] {brie});

        app.updateQuality();

        assertThat(brie.sellIn).isEqualTo(7);
        assertThat(brie.quality).isEqualTo(9);
    }

    @Test
    void sulfuras_updateQuality_noChange() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose app = new GildedRose(new Item[] {sulfuras});

        app.updateQuality();

        assertThat(sulfuras.sellIn).isEqualTo(0);
        assertThat(sulfuras.quality).isEqualTo(80);
    }

    @Test
    void backstagePasses_updateQuality_updatesAsExpected() { // TODO bad name
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 13, 8);
        GildedRose app = new GildedRose(new Item[] {backstagePasses});

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(12);
        assertThat(backstagePasses.quality).isEqualTo(9);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(11);
        assertThat(backstagePasses.quality).isEqualTo(10);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(10);
        assertThat(backstagePasses.quality).isEqualTo(11);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(9);
        assertThat(backstagePasses.quality).isEqualTo(13);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(8);
        assertThat(backstagePasses.quality).isEqualTo(15);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(7);
        assertThat(backstagePasses.quality).isEqualTo(17);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(6);
        assertThat(backstagePasses.quality).isEqualTo(19);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(5);
        assertThat(backstagePasses.quality).isEqualTo(21);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(4);
        assertThat(backstagePasses.quality).isEqualTo(24);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(3);
        assertThat(backstagePasses.quality).isEqualTo(27);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(2);
        assertThat(backstagePasses.quality).isEqualTo(30);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(1);
        assertThat(backstagePasses.quality).isEqualTo(33);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(0);
        assertThat(backstagePasses.quality).isEqualTo(36);

        app.updateQuality();

        assertThat(backstagePasses.sellIn).isEqualTo(-1);
        assertThat(backstagePasses.quality).isEqualTo(0);
    }


    // TODO all other items

    // TODO boundary (all items) : sellin 0 -> -1
    // TODO boundary (all items) : min q 0
    // TODO boundary (brie, passes) : max q 50

    // TODO alternative: 1 test per item type, multiple day updates (too complex test setup?)

}
