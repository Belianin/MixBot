import java.io.FileReader;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileWorker {

	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader(fileName);
			Scanner scan = new Scanner(fr);

			while (scan.hasNextLine()) {
				sb.append(scan.nextLine());
			}

			fr.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return sb.toString();
	}

	public static HashMap<String, Ingredient> parseIngredients(String rowString) {
		HashMap<String, Ingredient> ingredients = new HashMap<String, Ingredient>();

		rowString = rowString.toLowerCase();
		String[] rowIng = rowString.split("!");
		for (String row : rowIng) {
			Ingredient ing = new Ingredient();
			String[] params = row.split("&");
			for (String param : params) {
				String[] pair = param.split(":");
				switch (pair[0]) {
				case "name": {
					ing.name = pair[1];
					break;
				}
				case "class": {
					ing.ingClass = pair[1];
					break;
				}
				}
			}
			ingredients.put(ing.name, ing);
		}
		return ingredients;
	}

	public static HashMap<String, Food> parseFood(String rowString, Map<String, Ingredient> ingredients) {
		HashMap<String, Food> foodDict = new HashMap<String, Food>();

		rowString = rowString.toLowerCase();
		String[] rowFood = rowString.split("!");
		for (String row : rowFood) {
			Food food = new Food();
			String[] params = row.split("&");
			for (String param : params) {
				String[] pair = param.split(":");
				switch (pair[0]) {
				case "name": {
					food.name = pair[1];
					break;
				}
				case "ing": {
					String[] ings = pair[1].split(",");
					for (int i = 0; i < ings.length; i++) {
						Ingredient ing = ingredients.get(ings[i]);
						if (ing != null) {
							food.ingrList.add(ing);
							ing.possibleFood.add(food);
						} else
							System.out.println(ings[i] + " отсутвует в базе!");
					}
				}
				}
			}
			foodDict.put(food.name, food);
		}
		return foodDict;
	}
}
