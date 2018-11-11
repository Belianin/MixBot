import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MixBot {
	Map<String, Dialog> dialogs = new HashMap<String, Dialog>();
	Map<String, Ingredient> ingredients;
	Map<String, Food> food;
	Map<String, UserData> users;
	String userDirectory;
	FileWorker fileWorker;
	List<String> buttons;

	public Response respond(String name, String request) {
		String[] words = request.toLowerCase().replaceAll(",", "").split(" ");// .replaceAll("\\s","");

		UserData user = getUser(name);
		Dialog currentDialog = dialogs.get(user.dialog);

		Response response = currentDialog.respond(user, words);
		if (response.nextDialog != null)
			user.dialog = response.nextDialog.getName();
		
		if (user.dialog.equals("start"))
			response.buttons = buttons;
		
		return response;
	}

	public void deleteUser(String name) {
		fileWorker.deleteUser(name);
		users.remove(name);
	}

	public Response initializeSession(String name) {
		UserData user = getUser(name);
		return dialogs.get(user.dialog).getResumeResponse(user);
	}

	private UserData getUser(String name) {
		UserData user = users.get(name);
		if (user == null) {
			user = fileWorker.loadUser(name);
			if (user == null) {
				user = new UserData(name);
			}
			users.put(user.name, user);
		}
		return user;
	}

	public void finishSession(String name) {
		fileWorker.saveUser(users.get(name));
		users.remove(name);
	}

	public void saveUsers() {
		for (UserData user : users.values()) {
			fileWorker.saveUser(user);
		}
	}

	public MixBot(String userDir) {
		userDirectory = userDir;
		fileWorker = new FileWorker(userDirectory);
		users = new HashMap<String, UserData>();
		ingredients = fileWorker.parseIngredients(fileWorker.read("data/ingredients.mbd"));
		food = fileWorker.parseFood(fileWorker.read("data/food.mbd"), ingredients);

		SimpleDialog startDialog = new SimpleDialog("start");
		dialogs.put("start", startDialog);
		dialogs.put("basket", new BasketDialog(startDialog, ingredients));
		dialogs.put("food", new FoodDialog(startDialog, food));

		buttons = new ArrayList<String>();
		buttons.add("1 Коктейль по ингредиентам");
		buttons.add("2 конкретный коктейль");
		
		startDialog.resumeResponse = new Response("Здраствуйте, меня зовут MixBot, я могу помочь вам в приготовлении коктейлей."
				+ "\nЧто вы хотите, конкретный коктейль или сделать что нибудь из ваших ингредиентов?", null, buttons);
		startDialog.addAction(new String[] { "инфо", "помощь", "информация", "инструкция", "памагити", "help" },
				new Response("Если вы хотите получить информацию по конкретному коктейлю, напишите \"2\";"
						+ "\nЕсли же вам нужна помощь по приготовлению из имеющихся у вас ингредиентов, "
						+ "напишите \"1\""));
		startDialog.addAction(
				new String[] { "ингредиентов", "ингредиент", "1", "ингредиенты", "ингредиентам", "первое" },
				new Response("Пожалуйста напишите, что у вас есть", dialogs.get("basket")));
		startDialog.addAction(new String[] { "конкретный", "коктейль", "коктейлей", "2" },
				new Response("Что вы хотите приготовить?", dialogs.get("food")));
	}

}
