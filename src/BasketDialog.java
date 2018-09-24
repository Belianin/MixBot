import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BasketDialog implements Dialog {

	public List<Food> food = new ArrayList<Food>();
	private Random random = new Random();
	public List<String> params = new ArrayList<String>();
	public List<String> endWords = new ArrayList<String>();
	public List<String> elseWords = new ArrayList<String>();

	public BasketDialog() {
		endWords.add("все");
		endWords.add("всё");
		endWords.add("готово");
		endWords.add("конец");
		endWords.add("да");
		
		elseWords.add("Что нибудь еще или все?");
		elseWords.add("Интересный выбор, что нибудь еще?");
		elseWords.add("Еще и это? Ну ладно...");
		elseWords.add("Может уже хватит? :)");
	}

	public Response respond(String[] words) {
		for (String word : words) {
			if (endWords.contains(word)) {
				if (params.size() == 0)
					return new Response("Вы пока еще ничего не добавили!");
				else
					return new Response(matchFood(), MixBot.dialogs.get("start"));
			}
		}
		for (String word : words) {
			params.add(word);
		}
		if (params.size() > 6)
			return new Response(elseWords.get(random.nextInt(2) + 2), MixBot.dialogs.get("start"));
		return new Response(elseWords.get(random.nextInt(2)), MixBot.dialogs.get("start"));
	}
	private String matchFood()
	{
		Map<String, PossibleFood> possibleFood = new HashMap<String, PossibleFood>();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		//отсеиваем еду от бреда
		for (String ing : params)
		{
			if (MixBot.ingredients.containsKey(ing))
				ingredients.add(MixBot.ingredients.get(ing));
		}
		//добавляем все возможные блюда
		for (Ingredient ing : ingredients)
		{
			for (Food food : ing.possibleFood)
			{
				if (possibleFood.containsKey(food.name))
					possibleFood.get(food.name).checkList.put(ing, true);
				else
					possibleFood.put(food.name, new PossibleFood(food, ing));
			}
		}
		//сравниваем какие сравнения больше подходят , смотрим хватате ли им ингридинетов и что добавить :(
		Food result = null;
		double rate = 0;
		for (Map.Entry<String, PossibleFood> entry : possibleFood.entrySet()) {
		    if (entry.getValue().getPercentage() > rate)
		    {
		    	rate = entry.getValue().getPercentage();
		    	result = entry.getValue().food;
		    }
		}
		if (result == null)
			return "Из этого ничего не приготовить!";
		//более подбробно
		return result.name + "у вас есть " + rate + "продуктов!";
	}
	class PossibleFood
	{
		public Food food;
		public Map<Ingredient, Boolean> checkList = new HashMap<Ingredient, Boolean>();
		public PossibleFood(Food f, Ingredient ing)
		{
			food = f;
			for (Ingredient i : food.ingrList)
				checkList.put(i, false);
			
			checkList.put(ing, true);
		}
		public double getPercentage()
		{
			int count = 0;
			for (Ingredient ing : food.ingrList)
				if (checkList.get(ing))
					count++;
			
			return (0 / checkList.size()) * 100;
		}
	}

	public void addAction(String[] words, Response act) {

	}
}
