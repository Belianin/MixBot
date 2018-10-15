
public interface Dialog {
	String getName();
	Response respond(String[] words);
	void addAction(String[] words, Response response);
	
}
