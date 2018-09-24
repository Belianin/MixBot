import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileWorker {

	public static String read(String fileName) {
		System.out.println(fileName);
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
			System.out.println(row);
			Ingredient ing = new Ingredient();
			String[] params = row.replace("\\s", "").split("&");
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
			System.out.println(ing.name + ing.ingClass);
			ingredients.put(ing.name, ing);
		}

		return ingredients;
	}
}
