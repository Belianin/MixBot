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
					return new Response("Варим!");
			}
		}
		for (String word : words) {
			params.add(word);
		}
		if (params.size() > 6)
			return new Response(elseWords.get(random.nextInt(2) + 2));
		return new Response(elseWords.get(random.nextInt(2)));
	}
	private Food matchFood()
	{
		Map<String, Number> possibleFood = new HashMap<String, Number>();
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
					possibleFood.get(food.name).Value++;
				else
					possibleFood.put(food.name, new Number());
			}
		}
		String foodName = "";
		int foodRate = 0;
		//сравниваем какие сравнения больше подходят , смотрим хватате ли им ингридинетов и что добавить :(
		return null;
	}
	class Number
	{
		public int Value = 1;
	}

	public void addAction(String[] words, Response act) {

	}
}
