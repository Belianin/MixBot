import java.util.Scanner;

public class MixBot {

	private static Scanner input = new Scanner(System.in);
	private static Dialog currentDialog = new Dialog();
	
	public static void main(String[] args) {
		
		currentDialog.KeyWords.put("инфо", new Action("Это тестовый бот"));
		
		System.out.println("Hello, my name is JokeBot.\nI am a chat-bot. I live to serve humans and tell hilarious jokes!\nWanna some?");
		
		while (true)
		{			
			String[] words = getWords();
			
			for (String word : words)
			{
				 if (currentDialog.KeyWords.containsKey(word))
				 {
					 Act(currentDialog.KeyWords.get(word));
				 }
			}
		}

	}
	public static String[] getWords() {
		return input.nextLine().toLowerCase().split(" ");// .replaceAll("\\s","");
	}
	public static void Act(Action action)
	{
		if (action.nextDialog != null)
			 currentDialog = action.nextDialog;
		 if (action.message != null)
		 System.out.println(action.message);
	}

}
