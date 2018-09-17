
public class Action {

	public Dialog nextDialog = null;
	public String message = "Default";
	public Action(Dialog next, String msg)
	{
		nextDialog = next;
		message = msg;
	}
	public Action(String msg)
	{
		message = msg;
	}
}
