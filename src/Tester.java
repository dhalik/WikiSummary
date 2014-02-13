
public class Tester {
	public static void main(String[] args){
		Summarizer s = new Summarizer("http://en.wikipedia.org/wiki/Effective_interest_rate");
		System.out.println(s.getSummary());
	}
}
