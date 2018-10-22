import java.util.Scanner;

public class EntryPointConsole {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		MixBot.initialize();
		System.out.println("Как вас зовут?");
		String name = input.nextLine();
		System.out.println(MixBot.initializeSession(name));
		while (true)
		{			
			String request = getWords();
			if (request.equals("пока") || request.equals("убейся"))
                break;
			String response = MixBot.respond(name, request);
			if (response != null)
				System.out.println(response);
		}
		System.out.println("До свидания!");
		//MixBot.finishSession(name);
		MixBot.saveUsers();
	}

	public static String getWords() {
		return input.nextLine();
	}
}
