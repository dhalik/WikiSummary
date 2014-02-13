import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetPages {
	URL url;
	ArrayList<Topic> topics;
	Pattern subtopicMatch;
	Pattern topicMatch;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void run(){
		InputStream is;
		BufferedReader reader;
		String line;
		ArrayList<Topic> listofTopics = new ArrayList<Topic>();
		
		try {
			is =  url.openStream();
			logger.log(Level.INFO,"Created Connection");
			reader = new BufferedReader (new InputStreamReader(is));
			logger.log(Level.INFO,"Opened Reader");
						
			while ((line = reader.readLine()) != null){
				
				Matcher topicFinder = topicMatch.matcher(line);
				Matcher subtopicFinder = subtopicMatch.matcher(line);
				
				if (topicFinder.find()){
					listofTopics.add(new Topic(line.substring(topicFinder.start(),topicFinder.end())));
				}
				
				if (subtopicFinder.find()){
					listofTopics.get(listofTopics.size()-1).addPage(line.substring(subtopicFinder.start(), subtopicFinder.end()));
				}
			}
			
			logger.log(Level.INFO,"Grabbed all Topics and Subtopics");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < listofTopics.size(); i++){
			if (listofTopics.get(i).isEmpty()){
				logger.log(Level.INFO,"Topics for " + listofTopics.get(i).getName() + " is empty... Removing");
				listofTopics.remove(i);
				continue;
			}
			System.out.println(listofTopics.get(i).getName());
			listofTopics.get(i).cleanPages();
		}
	}
	
	public GetPages(String topicList) throws MalformedURLException{
		url = new URL(topicList);
		subtopicMatch = Pattern.compile("<li><a href=(.+?)</li>");
		topicMatch = Pattern.compile("<span class=\"mw-headline\".+?</span>");
	}
	
	public static void main(String[] args){
		try {
			GetPages gp = new GetPages("http://en.wikipedia.org/wiki/Outline_of_finance");
			gp.run();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
	}
}
