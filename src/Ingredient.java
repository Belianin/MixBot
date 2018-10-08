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
		
	}
	@Override
	public int hashCode() {
		double hash = possibleFood.size() * 17;
		for (char a : (name + ingClass).toCharArray())
		{
			hash = Math.pow(hash, (int)a) * 31;
		}
			
		return (int)hash;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (!(other instanceof Ingredient))
			return false;
		
		Ingredient otherIng = (Ingredient)other;
		return otherIng.name.equals(name) && otherIng.ingClass.equals(ingClass) && otherIng.possibleFood.equals(possibleFood);
	}

}
