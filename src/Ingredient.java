import java.util.ArrayList;

public class Ingredient {
	public String name;
	public String ingClass;
	public ArrayList<Food> possibleFood;
	
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
