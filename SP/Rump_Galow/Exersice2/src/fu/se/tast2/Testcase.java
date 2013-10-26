package fu.se.tast2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Testcase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void getZero() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(0);
		assertArrayEquals("Vergeliche one bei getAsString(0)",new String("zero").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getOne() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(1);
		assertArrayEquals("Vergeliche one bei getAsString(1)",new String("one").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getTow() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(2);
		assertArrayEquals("Vergeliche tow bei getAsString(2)",new String("tow").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getThree() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(3);
		assertArrayEquals("Vergeliche one bei getAsString(3)",new String("three").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getFour() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(4);
		assertArrayEquals("Vergeliche one bei getAsString(4)",new String("four").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getFive() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(5);
		assertArrayEquals("Vergeliche one bei getAsString(5)",new String("five").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getSix() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(6);
		assertArrayEquals("Vergeliche one bei getAsString(6)",new String("six").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getSeven() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(7);
		assertArrayEquals("Vergeliche one bei getAsString(7)",new String("seven").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getEight() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(8);
		assertArrayEquals("Vergeliche one bei getAsString(8)",new String("eight").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getNine() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(9);
		assertArrayEquals("Vergeliche one bei getAsString(9)",new String("nine").toCharArray(), s.toCharArray());
	}
	
	@Test
	public void getTen() {
		NumberConverter nc = new NumberConverter();
		String s = nc.getAsString(10);
		assertArrayEquals("Vergeliche one bei getAsString(10)",new String("ten").toCharArray(), s.toCharArray());
	}
}
