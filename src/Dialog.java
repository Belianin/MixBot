
public interface Dialog {
	String getName();
	Response respond(UserData user, String[] words);
	void addAction(String[] words, Response response);
	
}
