
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class StripPage {
	
	public static void main(String[] args){
		String content;
		try {
			content = new Scanner(new File("infinity")).useDelimiter("\\Z").next();
			Pattern p = Pattern.compile("<p>.+?</p>");
			Matcher m = p.matcher(content);
			m.find();
			System.out.println(content.substring(m.start(), m.end()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}