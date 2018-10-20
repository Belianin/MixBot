import java.util.Scanner;

public class EntryPointConsole {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		MixBot.initialize();
		System.out.println("Как вас зовут?");
		String name = input.nextLine();
		System.out.println(MixBot.initializeSession(name));
		//System.out.println("Здраствуйте, меня зовут MixBot, я могу помочь вам в приготовлении коктейлей.\nЧто вы хотите, конкретный коктейль или сделать что нибудь из ваших ингредиентов?");
		while (true)
		{			
			String request = getWords();
			if (request.equals("пока"))
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
