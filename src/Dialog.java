import java.util.HashMap;
import java.util.Map;

public class Dialog {

	public Map<String, Action> KeyWords;
	
	public Dialog(HashMap<String, Action> keyWords)
	{
		KeyWords = keyWords;
	}
	public Dialog()
	{
		KeyWords = new HashMap<String, Action>();;
	}
}
