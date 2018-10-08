
public interface Dialog {

	Response respond(String[] words);
	void addAction(String[] words, Response response);
	
}
