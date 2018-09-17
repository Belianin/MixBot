import java.util.List;
import java.util.Scanner;

public class MixBot {
	private static List<Ingredient> ingredients;
	private static Scanner input = new Scanner(System.in);
	private static Dialog currentDialog = new Dialog();
	
	public static void main(String[] args) {
		ingredients = FileWorker.parseIngredients(FileWorker.read("data/base.mbd"));
		
		for (Ingredient ing : ingredients)
			System.out.println(ing.ingClass + " " + ing.name);
		
		currentDialog.keyWords.put("инфо", new Action("Это тестовый бот"));
		
		System.out.println("Hello");
		
		while (true)
		{			
			String[] words = getWords();
			
			for (String word : words)
				 if (currentDialog.keyWords.containsKey(word))
					 Act(currentDialog.keyWords.get(word));
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
