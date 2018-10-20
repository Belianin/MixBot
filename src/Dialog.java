
public interface Dialog {
	String getName();
	String getResumeMessage(UserData user);
	Response respond(UserData user, String[] words);
	void addAction(String[] words, Response response);
	
}
