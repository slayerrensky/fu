package fu.netzsys.ex3.one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Util {

	public static BufferedReader getBufferedReaderFromFile(String fullPath)
			throws FileNotFoundException {
		File f = new File(fullPath);
		if (!f.exists())
			throw new FileNotFoundException();

		FileReader fr = new FileReader(f);
		return new BufferedReader(fr);
	}

	public static String[] getStringListFromStringLine(String line, String splitter) {
		String[] split = line.split(splitter);

		// Set Values
		String[] ret = new String[split.length];

		for (int i = 0; i < split.length; i++)
			ret[i] = split[i];

		return ret;
	}
}
