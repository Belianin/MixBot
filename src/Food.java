import java.util.ArrayList;
import java.util.List;

public class Food {

	public List<Ingredient> ingrList = new ArrayList<Ingredient>();
	public String name;
	public String type;

	public Food(String sType, String sName, Ingredient[] ings) {
		type = sType;
		name = sName;
		for (Ingredient ing : ings)
			ingrList.add(ing);

	}

	public boolean checkIngr(Ingredient ingr) {
		return ingrList.contains(ingr);
	}

}
