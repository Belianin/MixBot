import java.util.ArrayList;


public class Food 
{
	
	public ArrayList<Ingredient> ingrList;
	public String name;
	public String type;
	
	public Food(String sType, String sName, Ingredient... a) 
	{
		type = sType;
		name = sName;
		for(Ingredient e : a) 
		{
			ingrList.add(e);	
		}
	}
	
	public boolean checkIngr(Ingredient ingr) 
	{
		return ingrList.contains(ingr);
	}

}
