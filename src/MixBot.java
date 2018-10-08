import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MixBot {
	public static Map<String, Dialog> dialogs = new HashMap<String, Dialog>();
	public static Map<String, Ingredient> ingredients;
	public static Map<String, Food> food;
	private static Scanner input = new Scanner(System.in);
	private static Dialog currentDialog;
	
	public static void main(String[] args) {
		initialize();
		
		System.out.println("Доброго времени суток! Что вы хотите делать?");
		while (true)
		{			
			String[] words = getWords();
			if (words.length > 0 && words[0].equals("пока")) {
			    System.out.println("До свидания!");
                break;
            }
			Response response = currentDialog.respond(words);
			Act(response);
		}
	}
	public static String[] getWords() {
		return input.nextLine().toLowerCase().replaceAll(",",  "").split(" ");// .replaceAll("\\s","");
	}
	public static void Act(Response response)
	{
		if (response.nextDialog != null)
			 currentDialog = response.nextDialog;
		 if (response.message != null)
		 System.out.println(response.message);
	}
	private static void initialize()
	{
		ingredients = FileWorker.parseIngredients(FileWorker.read("data/ingredients.mbd"));
		food = FileWorker.parseFood(FileWorker.read("data/food.mbd"), ingredients);
		
		dialogs.put("start", new SimpleDialog());
		dialogs.put("basket", new BasketDialog());
		dialogs.put("food", new FoodDialog());
		
		currentDialog = dialogs.get("start");
		
		dialogs.get("start").addAction(new String[] {"инфо", "помощь", "что", "информация"}, new Response("MixBot может подсказать вам что приготовить, готовы?"));
		dialogs.get("start").addAction(new String[] {"да", "начать", "готовить", "го"}, new Response("Что у вас есть?", dialogs.get("basket")));
		dialogs.get("start").addAction(new String[] { "хочу" }, new Response("Что вы хотите приготовить?", dialogs.get("food")));
	}

}
