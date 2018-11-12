import java.util.ArrayList;
import java.util.List;

public class Response {
	public String message;
	public Dialog nextDialog = null;
	public List<String> buttons;

	public Response(String msg, Dialog next) {
		message = msg;
		nextDialog = next;
	}
	
	public Response(String msg, Dialog next, List<String> buttons) {
		message = msg;
		nextDialog = next;
		this.buttons = buttons;
	}
	
	public Response(String msg, Dialog next, String button) {
		message = msg;
		nextDialog = next;
		this.buttons =  new ArrayList<String>();
		buttons.add(button);
	}

	public Response(String msg) {
		message = msg;
	}
}
