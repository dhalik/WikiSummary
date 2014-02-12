import java.net.MalformedURLException;
import java.net.URL;


public class Topic {
	String name;
	URL url;
	Summarizer summarizer;
	
	public Topic(String loc){
		try {
			url = new URL(loc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
