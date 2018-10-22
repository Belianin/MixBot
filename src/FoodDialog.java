import java.util.Map;

public class FoodDialog implements Dialog {
	private Dialog returnDialog;
	private Map<String, Food> allFood;
	public String getName() {
		return "food";
	}

	public String getResumeMessage(UserData user) {
		return "Здраствуйте, вы хотели выбрать коктейль.";
	}

	@Override
	public Response respond(UserData user, String[] words) {
		if (words.length == 1)
			if (allFood.containsKey(words[0])) {
				StringBuilder builder = new StringBuilder();
				builder.append("Для коктейля " + words[0] + " вам понадобятся следующие ингредиенты:");
				for (Ingredient ing : allFood.get(words[0]).ingrList)
					builder.append("\n" + ing.name);

				return new Response(builder.toString(), returnDialog);
			}
		return new Response("Я не знаю такого блюда!", returnDialog);
	}
	
	public FoodDialog(Dialog backDialog, Map<String, Food> food) {
		returnDialog = backDialog;
		allFood = food;
	}

	@Override
	public void addAction(String[] words, Response response) {
		// TODO Auto-generated method stub

	}

}
