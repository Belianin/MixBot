import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BasketDialog implements Dialog {
	private Random random = new Random();
	private HashSet<String> endWords = new HashSet<>();
	private List<String> elseWords = new ArrayList<>();
	
	public String getName()
	{
		return "basket";
	}

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

	public Response respond(UserData user, String[] words) {
		for (String word : words) {
			if (endWords.contains(word)) {
				if (user.basket.size() == 0)
					return new Response("Вы пока еще ничего не добавили!");
				else {
					HashSet<String> params = user.basket;
					user.basket = new HashSet<String>();
					return new Response(matchFood(params), MixBot.dialogs.get("start"));
				}
			}
		}
		for (String word : words) {
			user.basket.add(word);
		}
		if (user.basket.size() > 6)
			return new Response(elseWords.get(random.nextInt(elseWords.size() / 2) + elseWords.size() / 2));
		return new Response(elseWords.get(random.nextInt(elseWords.size() / 2)));
	}

	private String matchFood(HashSet<String> params) {
		Map<String, PossibleFood> possibleFood = new HashMap<>();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		// отсеиваем еду от бреда
		for (String ing : params) {
			Ingredient posIng = MixBot.ingredients.get(ing);
			if (posIng != null)
				ingredients.add(posIng);
		}
		// добавляем все возможные блюда
		for (Ingredient ing : ingredients) {
			for (Food food : ing.possibleFood) {
				PossibleFood posFood = possibleFood.get(food.name);
				if (posFood != null)
					posFood.checkList.put(ing, true);
				else
					possibleFood.put(food.name, new PossibleFood(food, ing));
			}
		}
		//System.out.println(possibleFood.size());
		// сравниваем какие сравнения больше подходят , смотрим хватате ли им
		// ингридинетов и что добавить :(
		PossibleFood result = null;
		double rate = 0;
		for (Map.Entry<String, PossibleFood> entry : possibleFood.entrySet()) {
			if (entry.getValue().getPercentage() > rate) {
				rate = entry.getValue().getPercentage();
				result = entry.getValue();
			}
		}
		params.clear();
		if (result == null)
			return "Из этого ничего не приготовить!";
		// более подбробно чего не хватает и тд
		StringBuilder builder = new StringBuilder();
		builder.append("Вы можете приготовить " + possibleFood.size() + " коктейлей:");
		//builder.append(" продуктов для пригтотвления коктейля \"" + result.food.name + "\"");
		for (PossibleFood posFod : possibleFood.values())
		    builder.append("\n" + posFod.food.name + " " + posFod.getCount() + "/" + posFod.checkList.size() + " ингредиентов");
		//builder.append(result.getCount() + "\\" + result.checkList.size() + " продуктов!");
		//builder.append("Что будем делать дальше?");

		return builder.toString();
	}

	class PossibleFood {
		public Food food;
		public Map<Ingredient, Boolean> checkList = new HashMap<>();

		public PossibleFood(Food f, Ingredient ing) {
			food = f;
			for (Ingredient i : food.ingrList)
				checkList.put(i, false);

			checkList.put(ing, true);
		}

		public int getCount() {
			int count = 0;
			for (Ingredient ing : food.ingrList)
				if (checkList.get(ing))
					count++;
			return count;
		}

		public double getPercentage() {
			return (getCount() / (double)checkList.size()) * 100;
		}
	}

	public void addAction(String[] words, Response act) {

	}
}
