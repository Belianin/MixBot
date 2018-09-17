import java.util.HashMap;
import java.util.Map;

public class Dialog {

	public Map<String, Action> keyWords;
	
	public Dialog(HashMap<String, Action> words)
	{
		keyWords = words;
	}
	public Dialog()
	{
		keyWords = new HashMap<String, Action>();;
	}
}
