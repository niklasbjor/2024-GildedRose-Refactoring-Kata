package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void regularItem_updateQuality_decrementsQuality() {
        Item regularItem = new Item("+5 Dexterity Vest", 3, 6);
        GildedRose app = new GildedRose(new Item[]{regularItem});

        app.updateQuality();
        assertSellInAndQuality(regularItem, 2, 5);
        app.updateQuality();
        assertSellInAndQuality(regularItem, 1, 4);
        app.updateQuality();
        assertSellInAndQuality(regularItem, 0, 3);
    }

    @Test
    void regularItemAfterSellByDate_updateQuality_doubleDegradation() {
        Item regularItem = new Item("+5 Dexterity Vest", 0, 3);
        GildedRose app = new GildedRose(new Item[]{regularItem});

        app.updateQuality();

        assertSellInAndQuality(regularItem, -1, 1);
    }

    @Test
    void regularItem_updateQuality_doesNotGoBelowMinimumQuality() {
        Item regularItem = new Item("+5 Dexterity Vest", -1, 1);
        GildedRose app = new GildedRose(new Item[]{regularItem});

        app.updateQuality();
        assertSellInAndQuality(regularItem, -2, 0);
        app.updateQuality();
        assertSellInAndQuality(regularItem, -3, 0);
    }

    @Test
    void agedBrie_updateQuality_incrementsQuality() {
        Item brie = new Item("Aged Brie", 2, 44);
        GildedRose app = new GildedRose(new Item[]{brie});

        app.updateQuality();
        assertSellInAndQuality(brie, 1, 45);
        app.updateQuality();
        assertSellInAndQuality(brie, 0, 46);
    }

    @Test
    void agedBrieAfterSellByDate_updateQuality_doubleIncrementsQuality() {
        Item brie = new Item("Aged Brie", 0, 46);
        GildedRose app = new GildedRose(new Item[]{brie});

        app.updateQuality();
        assertSellInAndQuality(brie, -1, 48);
        app.updateQuality();
        assertSellInAndQuality(brie, -2, 50);
    }

    @Test
    void agedBrie_updateQuality_doesNotGoAboveMaximumQuality() {
        Item brie1 = new Item("Aged Brie", -2, 50);
        Item brie2 = new Item("Aged Brie", 3, 50);
        Item brie3 = new Item("Aged Brie", -2, 49);

        GildedRose app = new GildedRose(new Item[]{brie1, brie2, brie3});

        app.updateQuality();

        assertSellInAndQuality(brie1, -3, 50);
        assertSellInAndQuality(brie2, 2, 50);
        assertSellInAndQuality(brie3, -3, 50);
    }

    @Test
    void sulfuras_updateQuality_noChange() {
        Item sulfuras1 = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item sulfuras2 = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        GildedRose app = new GildedRose(new Item[]{sulfuras1, sulfuras2});

        app.updateQuality();

        assertSellInAndQuality(sulfuras1, 0, 80);
        assertSellInAndQuality(sulfuras2, -1, 80);
    }

    @Test
    void backstagePassesMoreThanTenDays_updateQuality_incrementsQuality() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 12, 6);
        GildedRose app = new GildedRose(new Item[]{backstagePasses});

        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 11, 7);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 10, 8);
    }

    @Test
    void backstagePassesBetweenFiveAndTenDays_updateQuality_increasesQualityByTwo() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 8);
        GildedRose app = new GildedRose(new Item[]{backstagePasses});

        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 9, 10);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 8, 12);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 7, 14);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 6, 16);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 5, 18);
    }

    @Test
    void backstagePassesLessThanFiveDays_updateQuality_increasesQualityByThree() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 18);
        GildedRose app = new GildedRose(new Item[]{backstagePasses});

        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 4, 21);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 3, 24);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 2, 27);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 1, 30);
        app.updateQuality();
        assertSellInAndQuality(backstagePasses, 0, 33);
    }

    @Test
    void backstagePassesAfterSellByDate_updateQuality_qualityDropsToZero() {
        Item backstagePasses = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 33);
        GildedRose app = new GildedRose(new Item[]{backstagePasses});

        app.updateQuality();

        assertSellInAndQuality(backstagePasses, -1, 0);
    }

    @Test
    void backstagePasses_updateQuality_doesNotGoAboveMaximumQuality() {
        Item passes1 = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50);
        Item passes2 = new Item("Backstage passes to a TAFKAL80ETC concert", 7, 49);
        Item passes3 = new Item("Backstage passes to a TAFKAL80ETC concert", 3, 48);

        GildedRose app = new GildedRose(new Item[]{passes1, passes2, passes3});

        app.updateQuality();

        assertSellInAndQuality(passes1, 10, 50);
        assertSellInAndQuality(passes2, 6, 50);
        assertSellInAndQuality(passes3, 2, 50);
    }

    @Test
    void conjuredItem_updateQuality_decreasesQualityByTwo() {
        Item conjuredItem = new Item("Conjured Mana Cake", 3, 10);
        GildedRose app = new GildedRose(new Item[]{conjuredItem});

        app.updateQuality();
        assertSellInAndQuality(conjuredItem, 2, 8);
        app.updateQuality();
        assertSellInAndQuality(conjuredItem, 1, 6);
        app.updateQuality();
        assertSellInAndQuality(conjuredItem, 0, 4);
    }

    @Test
    void conjuredItemAfterSellByDate_updateQuality_decreasesQualityByFour() {
        Item conjuredItem = new Item("Conjured Mana Cake", 0, 4);
        GildedRose app = new GildedRose(new Item[]{conjuredItem});

        app.updateQuality();

        assertSellInAndQuality(conjuredItem, -1, 0);
    }

    @Test
    void conjuredItem_updateQuality_doesNotGoBelowMinimumQuality() {
        Item conjuredItem1 = new Item("Conjured Mana Cake", 1, 0);
        Item conjuredItem2 = new Item("Conjured Mana Cake", 1, 1);
        Item conjuredItem3 = new Item("Conjured Mana Cake", -1, 0);
        Item conjuredItem4 = new Item("Conjured Mana Cake", -1, 1);
        Item conjuredItem5 = new Item("Conjured Mana Cake", -1, 2);
        Item conjuredItem6 = new Item("Conjured Mana Cake", -1, 3);
        GildedRose app = new GildedRose(new Item[]{conjuredItem1, conjuredItem2, conjuredItem3, conjuredItem4, conjuredItem5, conjuredItem6});

        app.updateQuality();
        assertSellInAndQuality(conjuredItem1, 0, 0);
        assertSellInAndQuality(conjuredItem2, 0, 0);
        assertSellInAndQuality(conjuredItem3, -2, 0);
        assertSellInAndQuality(conjuredItem4, -2, 0);
        assertSellInAndQuality(conjuredItem5, -2, 0);
        assertSellInAndQuality(conjuredItem6, -2, 0);
    }


    private static void assertSellInAndQuality(Item item, int expectedSellIn, int expectedQuality) {
        assertThat(item.sellIn).isEqualTo(expectedSellIn);
        assertThat(item.quality).isEqualTo(expectedQuality);
    }

}
