import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Topic {
	private ArrayList<String> rawUrls = new ArrayList<String>();
	private String name;
	private ArrayList<Subtopic> stList = new ArrayList<Subtopic>();
	private Logger log = Logger.getLogger("MainLog");
	
	
	public Topic(String loc){
		Pattern title = Pattern.compile(">.+?<");
		Matcher titleFinder = title.matcher(loc);
		titleFinder.find();
		String topicName = loc.substring(titleFinder.start()+1,titleFinder.end()-1);
		
		this.name = topicName;
		
		log.log(Level.FINE,"Creating topic with name "+loc);
	}
	
	public void addPage(String page){
		rawUrls.add(page);
	}
	
	public void cleanPages(){
		Pattern link = Pattern.compile(".+?\"");
		Pattern title = Pattern.compile("title=\".+?\"");
		
		String base = "http://en.wikipedia.org";
		
		for (String s : rawUrls){
			
			s = s.replaceFirst("<li><a href=\"", "");

			Matcher linkFinder = link.matcher(s);
			linkFinder.find();
			String url = s.substring(linkFinder.start(),linkFinder.end()-1);
			
			Matcher titleFinder = title.matcher(s);
			titleFinder.find();
			String topicName = s.substring(titleFinder.start(),titleFinder.end());
			
			if (url.startsWith("/wiki")){
				url = base + url;
			}
			if (url.contains("php")){
				rawUrls.remove(s);
				continue;
			}
			log.log(Level.INFO,"	Creating new Subtopic with " + url + " and " + name);
			stList.add(new Subtopic(topicName, url));
		}
	}
	
	public void createSummaries(){
		for (int i = 0; i < stList.size(); i++){
			stList.get(i).summarize();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isEmpty(){
		if (name.equalsIgnoreCase("See also") || name.equalsIgnoreCase("External Links")){
			return true;
		}
		return rawUrls.isEmpty();
	}
	
	public String summarize(){
		String topicSummary = "";
		for (Subtopic s : stList){
			topicSummary += s.getSummary();
			topicSummary += "\n";
		}
		return topicSummary;
	}
	
}
