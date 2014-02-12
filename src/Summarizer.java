
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class Summarizer {
	
	String summary;
	
	public Summarizer(String inputFile){
		String content;
		Scanner reader = null;
		try {
			File input = new File(inputFile);
			reader = new Scanner(input);
			content = reader.useDelimiter("\\Z").next();
			Pattern p = Pattern.compile("<p>.+?</p>");
			Matcher m = p.matcher(content);
			m.find();
			summary = content.substring(m.start(), m.end());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			reader.close();
		}
	}
	
}