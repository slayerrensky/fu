package main;

import org.junit.Assert;
import org.junit.Test;

public class TestNumber {

	@Test
	public void testNumber14() {
		String numberString = Main.getSpellingNumberFromInteger(14);
		Assert.assertEquals(numberString, "14: fourteen");
	}

	@Test
	public void testNumber112() {
		String numberString = Main.getSpellingNumberFromInteger(112);
		Assert.assertEquals(numberString, "112: one hundred twelve");
	}

	@Test
	public void testNumber199() {
		String numberString = Main.getSpellingNumberFromInteger(199);
		Assert.assertEquals(numberString, "199: one hundred ninety nine");
	}

	@Test
	public void testNumber123456() {
		String numberString = Main.getSpellingNumberFromInteger(123456);
		Assert.assertEquals(numberString,
				"123456: one hundred twenty three thousand four hundred fifty six");
	}

	@Test
	public void testNumber421000() {
		String numberString = Main.getSpellingNumberFromInteger(421000);
		Assert.assertEquals(numberString,
				"421000: four hundred twenty one thousand");
	}

	@Test
	public void testNumber999999() {
		String numberString = Main.getSpellingNumberFromInteger(999999);
		Assert.assertEquals(numberString,
				"999999: nine hundred ninety nine thousand nine hundred ninety nine");
	}
	
	@Test
	public void testNumber1() {
		String numberString = Main.getSpellingNumberFromInteger(1);
		Assert.assertEquals(numberString,
				"1: one");
	}
	
	@Test
	public void testNumber10() {
		String numberString = Main.getSpellingNumberFromInteger(10);
		Assert.assertEquals(numberString,
				"10: ten");
	}
	
	@Test
	public void testNumber100() {
		String numberString = Main.getSpellingNumberFromInteger(100);
		Assert.assertEquals(numberString,
				"100: one hundred");
	}
	
	@Test
	public void testNumber1000() {
		String numberString = Main.getSpellingNumberFromInteger(1000);
		Assert.assertEquals(numberString,
				"1000: one thousand");
	}
	
	@Test
	public void testNumber10000() {
		String numberString = Main.getSpellingNumberFromInteger(10000);
		Assert.assertEquals(numberString,
				"10000: ten thousand");
	}
	
	@Test
	public void testNumber100000() {
		String numberString = Main.getSpellingNumberFromInteger(100000);
		Assert.assertEquals(numberString,
				"100000: one hundred thousand");
	}
	
	@Test
	public void testNumber100001() {
		String numberString = Main.getSpellingNumberFromInteger(100001);
		Assert.assertEquals(numberString,
				"100001: one hundred thousand one");
	}
	
	@Test
	public void testNumber100011() {
		String numberString = Main.getSpellingNumberFromInteger(100011);
		Assert.assertEquals(numberString,
				"100011: one hundred thousand eleven");
	}
	
	@Test
	public void testNumber100111() {
		String numberString = Main.getSpellingNumberFromInteger(100111);
		Assert.assertEquals(numberString,
				"100111: one hundred thousand one hundred eleven");
	}
	
	@Test
	public void testNumber101111() {
		String numberString = Main.getSpellingNumberFromInteger(101111);
		Assert.assertEquals(numberString,
				"101111: one hundred one thousand one hundred eleven");
	}
	
	@Test
	public void testNumber111111() {
		String numberString = Main.getSpellingNumberFromInteger(111111);
		Assert.assertEquals(numberString,
				"111111: one hundred eleven thousand one hundred eleven");
	}
	
	@Test
	public void testNumber111110() {
		String numberString = Main.getSpellingNumberFromInteger(111110);
		Assert.assertEquals(numberString,
				"111110: one hundred eleven thousand one hundred ten");
	}
	
	@Test
	public void testNumber111100() {
		String numberString = Main.getSpellingNumberFromInteger(111100);
		Assert.assertEquals(numberString,
				"111100: one hundred eleven thousand one hundred");
	}
	
	@Test
	public void testNumber111000() {
		String numberString = Main.getSpellingNumberFromInteger(111000);
		Assert.assertEquals(numberString,
				"111000: one hundred eleven thousand");
	}
	
	@Test
	public void testNumber114030() {
		String numberString = Main.getSpellingNumberFromInteger(114030);
		Assert.assertEquals(numberString,
				"114030: one hundred fourteen thousand thirty");
	}
}