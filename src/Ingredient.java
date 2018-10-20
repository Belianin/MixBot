import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Ingredient {
	public String name;
	public String ingClass;
	public List<Food> possibleFood = new ArrayList<Food>();
	public HashSet<String> synonyms = new HashSet<String>();
	public char emoji; 
	
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
		int hash = 1 + possibleFood.size() * 17;
		for (char a : (name + ingClass).toCharArray())
		{
			//hash = (int)Math.pow(hash, a);// * 31;
			hash *= a;
		}
			
		return hash;
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
