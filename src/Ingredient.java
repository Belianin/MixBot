import java.util.ArrayList;
import java.util.List;

public class Ingredient {
	public String name;
	public String ingClass;
<<<<<<< HEAD
	public List<Food> possibleFood = new ArrayList<Food>();
=======
	public ArrayList<Food> possibleFood;
>>>>>>> 8a5ba77a3213869f03a97cb07c3714d3783dd511
	
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
