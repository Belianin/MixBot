
public class Response {
	public String message;
	public Dialog nextDialog = null;

	public Response(String msg, Dialog next) {
		message = msg;
		nextDialog = next;
	}

	public Response(String msg) {
		message = msg;
	}
}
