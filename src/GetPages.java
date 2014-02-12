import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetPages {
	URL url;
	ArrayList<Topic> topics;
	Pattern p;
	Matcher m;
	
	Logger logger = Logger.getLogger("MainLog");
	
	public void run(){
		InputStream is;
		BufferedReader reader;
		String line;
		
		try {
			is =  url.openStream();
			logger.info("Created Connection");
			reader = new BufferedReader (new InputStreamReader(is));
			logger.info("Opened Reader");
			while ((line = reader.readLine()) != null){
				m = p.matcher(line);
				if (m.find()){
					System.out.println(line.substring(m.start(), m.end()));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GetPages(String topicList) throws MalformedURLException{
		url = new URL(topicList);
		p = Pattern.compile("<li><a href=\\\"http://en.wikipedia.org/wiki/.+?</a></li>");
	}
	
	public static void main(String[] args){
		try {
			GetPages gp = new GetPages("http://en.wikipedia.org/wiki/Outline_of_nuclear_technology");
			gp.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
}
