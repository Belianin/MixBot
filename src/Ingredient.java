import java.util.ArrayList;
import java.util.List;

public class Ingredient {
	public String name;
	public String ingClass;
	public List<Food> possibleFood = new ArrayList<Food>();
	
	public Ingredient(String sName, String sClass, ArrayList<Food> food)
	{
		name = sName;
		ingClass = sClass;
		possibleFood = food;
	}
	
	public Ingredient()
	{
		name = "";
		ingClass = "";
		possibleFood = new ArrayList<>();
	}

}
