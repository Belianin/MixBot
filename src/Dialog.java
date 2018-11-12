
public interface Dialog {
	String getName();
	Response getResumeResponse(UserData user);
	Response respond(UserData user, String[] words);
	void addAction(String[] words, Response response);
	
}
