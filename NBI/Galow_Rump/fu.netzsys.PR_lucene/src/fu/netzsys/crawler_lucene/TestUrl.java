package fu.netzsys.crawler_lucene;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestUrl {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		
		Crawler c = new Crawler();
		LinkedList<String> list = new LinkedList<String>();
		list.add("www.google.de");
		list.add("//www.google.de");
		list.add("//google.de");
		list.add("/google.de");
		list.add("http://www.google.de/");
		list.add("http://google.de/");
		list.add("https://www.google.de/");
		list.add("https://www.google.de/");
		list.add("https://google.de/");
		list.add("a.google.de/");
		list.add("https://a.google.de/");
		list.add("//a.google.de?q=suche&p=not");
		list.add("a.google.de???q=suche&p=not");
		list.add("/a.google.de???q=suche&p=not");
		list.add("tps://a.google.de???q=suche&p=not");
		list.add("ftp://a.google.de???q=suche&p=not");

		for(String s : list)
		{
			c.checkUrlIfValid(s, "https://google.de/");
		}
		
	}

}
