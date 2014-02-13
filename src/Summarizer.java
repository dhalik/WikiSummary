
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

public class Summarizer {
	
	String summary;
	Logger logger = Logger.getLogger("MainLog");
	
	public Summarizer(String inputUrl){
		Pattern p = Pattern.compile("<p>.+?</p>",Pattern.DOTALL);
		
		
		URL url;
		InputStream is;
		BufferedReader reader = null;
		String line;
		
		try {
			url = new URL(inputUrl);
			is =  url.openStream();
			logger.log(Level.FINE,"Created Connection");
			reader = new BufferedReader (new InputStreamReader(is));
			logger.log(Level.FINE,"Opened Reader");
						
			while ((line = reader.readLine()) != null){
				Matcher m = p.matcher(line);
				if (m.find()){
					summary = line.substring(m.start(), m.end());
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE,"Could not open reader");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Could not read from reader");
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.log(Level.SEVERE,"Could not close file");
				e.printStackTrace();
			}
		}
	}
	
	public String getSummary(){
		return summary;
	}
	
}