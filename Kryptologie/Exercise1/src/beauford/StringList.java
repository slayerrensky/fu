package beauford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StringList extends ArrayList<StringListElement> {
	String text = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void sortByNumber() {
		Collections.sort(this, new Comparator<StringListElement>() {

			@Override
			public int compare(StringListElement o1, StringListElement o2) {
				if (o1.getNumber() > o2.getNumber())
					return -1;
				if (o1.getNumber() < o2.getNumber())
					return 1;
				return 0;
			}
		});
	}

	public boolean inkrementByString(String string, int index) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getString().compareTo(string) == 0) {
				get(i).setNumber(get(i).getNumber() + 1);
				get(i).getDistance().add(index);
				// System.out.println(get(i).getString() + "=" + string);
				return true;
			}
		}
		return false;
	}

	public boolean contains(String o) {
		for (int i = 0; i < size(); i++) {
			if (get(i).getString().compareTo(o) == 0)
				return true;
		}
		return false;
	}

	public void out() {
		for (int i = 0; i < size(); i++) {

			System.out.println(get(i).getString() + " ->  "
					+ get(i).getNumber());
		}
	}

	public void removeUnrelevantsObjects() {
		for (int i = 0; i < size(); i++) {
			if (get(i).getNumber() == 1) {
				remove(i);
				i--;
			}
		}
	}

	public void printDistance() {
		for (int i = 0; i < (size() % 2 == 0 ? size()-1 : size()-2); i++) {
			int distance1 = get(i).getDistance().get(1)
					- get(i).getDistance().get(0);
			int distance2 = get(i+1).getDistance().get(1)
					- get(i+1).getDistance().get(0);
			int gGT2 = gGT(distance1, distance2);
			System.out.println(get(i).getString() + " - > " + gGT2);
		}
	}

	public int gGT(int x, int y) {

		int r;
		while (y != 0) {
			r = x % y;
			x = y;
			y = r;
		}
		return x;
	}

	public int kGV(int x, int y) {
		return (x * y) / gGT(x, y);
	}
}
