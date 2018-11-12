import java.util.ArrayList;
import java.util.List;

public class Food {

	public List<Ingredient> ingrList = new ArrayList<Ingredient>();
	public String name;
	public String type;

	public Food() {

	}

	public Food(String sName) {
		name = sName;
	}

	public Food(String sType, String sName, Ingredient[] a) {
		type = sType;
		name = sName;
		for (Ingredient e : a)
			ingrList.add(e);

	}

	public boolean checkIngr(Ingredient ingr) {
		return ingrList.contains(ingr);
	}

}
