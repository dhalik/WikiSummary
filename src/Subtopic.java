
public class Subtopic {
	
	private String name;
	private String summary;
	private String url;
	
	
	public Subtopic (String name, String url){
		this.name = name;
		this.url = url;
	}
	
	public void summarize(){
		Summarizer s = new Summarizer(url);
		summary = s.getSummary();
	}
	
	public String getSummary(){
		return summary;
	}
	
	public String getName(){
		return name;
	}
}
