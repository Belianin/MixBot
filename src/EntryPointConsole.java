import java.util.Scanner;

public class EntryPointConsole {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		MixBot.initialize();
		System.out.println("Доброго времени суток! Как вас зовут?");
		String name = input.nextLine();
		System.out.println("Здраствуйте!");
		while (true)
		{			
			String[] words = getWords();
			if (words.length > 0 && words[0].equals("пока")) {
			    System.out.println("До свидания!");
                break;
            }
			String response = MixBot.respond(name, words);
			if (response != null)
				System.out.println(response);
		}
		MixBot.saveUsers();
	}

	public static String[] getWords() {
		return input.nextLine().toLowerCase().replaceAll(",",  "").split(" ");// .replaceAll("\\s","");
	}
}
