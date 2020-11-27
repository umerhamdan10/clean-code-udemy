package com.a.introduction.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MYGildedRoseRefactoredTest {

	public static final int NOT_EXPIRED_SELLIN = 15;
	public static final int DEFAULT_QUALITY = 3;
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELLIN = -1;
	public static final String AGED_BRIE = "Aged Brie";
	public static final int MAXIMUM_QUALITY = 50;
	public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	public static final int SELLING_GRATER_THAN_10 = 15;
	public static final int BETWEEN_5_AND_10 = 7;
	public static final int LESS_THAN_5 = 4;



	@Test
	public void backStageLessThan5Days_QualityIncreasesByThree() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(BACKSTAGE_PASSES, LESS_THAN_5, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, LESS_THAN_5 - 1, DEFAULT_QUALITY + 3);
		assertItem(expectedItem, app.items[0]);
	}
	@Test
	public void backStageBETWEEN5And10Days_QualityIncreasesByTwo() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(BACKSTAGE_PASSES, BETWEEN_5_AND_10, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2);
		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void backStageBeyond10Days_QualityIncreasesByOne() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLING_GRATER_THAN_10, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(BACKSTAGE_PASSES, SELLING_GRATER_THAN_10 - 1, DEFAULT_QUALITY + 1);
		assertItem(expectedItem, app.items[0]);
	}



	@Test
	public void unexpiredAgedBrieQualityDoesNotGoBeyondMaximum() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, MAXIMUM_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, MAXIMUM_QUALITY);
		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void expiredAgedBrieQualityIncreasesByTwo() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2);
		assertItem(expectedItem, app.items[0]);
	}


	@Test
	public void unexpiredAgedBrieQualityIncreasesByOne() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(AGED_BRIE, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1);
		assertItem(expectedItem, app.items[0]);
	}


	@Test
	public void unexpiredDefaultQualityDecreaseByTwo() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(DEFAULT_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(DEFAULT_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2);
		assertItem(expectedItem, app.items[0]);
	}

	@Test
	public void unexpiredDefaultQualityDecreaseByOne() {
		//setup
		GildedRose app =  createGildedRoseWithOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);
		//invoke
		app.updateQuality();
		//verify
		Item expectedItem = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1);
		assertItem(expectedItem, app.items[0]);
	}

	private void assertItem(Item expectedItem, Item actualItem) {
		assertEquals(expectedItem.name, actualItem.name);
		assertEquals(expectedItem.sellIn, actualItem.sellIn);
		assertEquals(expectedItem.quality, actualItem.quality);
	}

	private GildedRose createGildedRoseWithOneItem(String defaultItem, int notExpiredSellin, int defaultQuality) {
		Item item = new Item(defaultItem, notExpiredSellin, defaultQuality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}

}