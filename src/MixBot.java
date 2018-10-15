import java.util.HashMap;
import java.util.Map;

public class MixBot {
	public static Map<String, Dialog> dialogs = new HashMap<String, Dialog>();
	public static Map<String, Ingredient> ingredients;
	public static Map<String, Food> food;
	private static Map<String, UserData> users;

	public static String respond(String name, String[] words) {
		UserData user = users.get(name);
		if (user == null) {
			user = FileWorker.loadUser(name);
		}
		if (user == null) {
			user = new UserData(name);
			users.put(user.name, user);
		}
		Dialog currentDialog = dialogs.get(user.dialog);

		Response response = currentDialog.respond(words);
		if (response.nextDialog != null)
			user.dialog = response.nextDialog.getName();
		return response.message;
	}

	public static void saveUsers() {
		for (UserData user : users.values()) {
			FileWorker.saveUser(user);
		}
	}

	public static void initialize() {
		users = new HashMap<String, UserData>();
		ingredients = FileWorker.parseIngredients(FileWorker.read("data/ingredients.mbd"));
		food = FileWorker.parseFood(FileWorker.read("data/food.mbd"), ingredients);

		dialogs.put("start", new SimpleDialog("start"));
		dialogs.put("basket", new BasketDialog());
		dialogs.put("food", new FoodDialog());

		// currentDialog = dialogs.get("start");
		dialogs.get("start").addAction(new String[] { "инфо", "помощь", "делать", "информация" },
				new Response("MixBot может подсказать вам что приготовить, готовы?"));
		dialogs.get("start").addAction(new String[] { "да", "начать", "готовить", "го" },
				new Response("Что у вас есть?", dialogs.get("basket")));
		dialogs.get("start").addAction(new String[] { "хочу" },
				new Response("Что вы хотите приготовить?", dialogs.get("food")));
	}

}
