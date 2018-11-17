package mixBot;
import java.util.HashSet;

public class UserData {
	public String name;
	public String dialog;
	public HashSet<String> basket = new HashSet<String>();
	
	public UserData(String n)
	{
		name = n;
		dialog = "start";
	}
	public UserData() {
		
	}
}
