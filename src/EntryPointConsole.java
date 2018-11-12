import java.util.Scanner;

public class EntryPointConsole {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		MixBot mixBot = new MixBot("data/user/");
		System.out.println("Как вас зовут?");
		String name = input.nextLine();
		System.out.println(mixBot.initializeSession(name));
		while (true)
		{			
			String request = getWords();
			if (request.equals("пока") || request.equals("убейся"))
                break;
			String response = mixBot.respond(name, request).message;
			if (response != null)
				System.out.println(response);
		}
		System.out.println("До свидания!");
		//MixBot.finishSession(name);
		mixBot.saveUsers();
	}

	public static String getWords() {
		return input.nextLine();
	}
}
