import java.util.HashMap;
import java.util.Map;

public class SimpleDialog implements Dialog {

	public Map<String, Response> keyWords;
	public static Response defaultResponse = new Response("Я не знаю такой команды :(");
	public String name;
	public Response resumeResponse;

	public String getName() {
		return name;
	}

	public Response getResumeResponse(UserData user) {
		return resumeResponse;
	}

	public SimpleDialog(String n, HashMap<String, Response> words) {
		name = n;
		keyWords = words;
	}

	public SimpleDialog(String n) {
		name = n;
		keyWords = new HashMap<String, Response>();
	}

	public Response respond(UserData user, String[] words) {
		for (String word : words) {
			if (keyWords.containsKey(word))
				return keyWords.get(word);
		}
		return defaultResponse;
	}

	public void addAction(String[] words, Response act) {
		for (String word : words) {
			keyWords.put(word, act);
		}
	}
}
