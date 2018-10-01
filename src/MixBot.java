import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MixBot {
	public static Map<String, Dialog> dialogs = new HashMap<String, Dialog>();
	public static Map<String, Ingredient> ingredients;
	public static Map<String, Food> food = new HashMap<String, Food>();
	private static Scanner input = new Scanner(System.in);
	private static Dialog currentDialog;
	
	public static void main(String[] args) {
		initialize();
		
		System.out.println("Hello");
		while (true)
		{			
			Response response = currentDialog.respond(getWords());
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
		ingredients = FileWorker.parseIngredients(FileWorker.read("data/base.mbd"));
		for (Ingredient ing : ingredients.values())
		{
			ArrayList<Food> newFood = new ArrayList<Food>();
			for (Food posFood : ing.possibleFood)
			{
				if (food.containsKey(posFood.name))
				{
					food.get(posFood.name).ingrList.add(ing);
				}
				else
				{
					food.put(posFood.name, new Food(posFood.type, posFood.name, new Ingredient[] {ing}));
				}
				newFood.add(food.get(posFood.name));
			}
			ing.possibleFood = newFood;
		}
		//for (Food f : food.values())
		//{
		//	System.out.println("Блюдо " + f.name);
		//	for (Ingredient ing : f.ingrList)
		///	{
		//		System.out.println(ing.name);
		//		for (Food posFood : ing.possibleFood)
		//			System.out.println(posFood.name);
		//	}
		//}
		
		//for (Ingredient ing : ingredients.values())
		//{
		//	System.out.println("Ингредиент " + ing.name);
		//	for (Food posFood : ing.possibleFood)
		//		System.out.println(posFood.name);
		//}
		
		//food.put("кровавая мэри", new Food("коктейль", "кровавая мэри", new Ingredient[] {ingredients.get("томат"), ingredients.get("водка"), ingredients.get("сельдерей")}));
		//for (Ingredient ing : food.get("кровавая мэри").ingrList)
		//{
		//	ing.possibleFood.add(food.get("кровавая мэри"));
		//}
		
		dialogs.put("start", new SimpleDialog());
		dialogs.put("basket", new BasketDialog());
		
		currentDialog = dialogs.get("start");
		
		dialogs.get("start").addAction(new String[] {"инфо", "помощь", "что", "информация"}, new Response("MixBot может подсказать вам что приготовить, готовы?"));
		dialogs.get("start").addAction(new String[] {"да", "начать", "готовить"}, new Response("Что у вас есть?", dialogs.get("basket")));
	}

}
