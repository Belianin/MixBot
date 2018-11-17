import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class MixBotTest {

	MixBot mixBot;
	@Before
	public void setUp() throws Exception {
		mixBot = new MixBot("data/user test/");
        mixBot.deleteUser("test");
	}
    @Test
    public void changeDialogToFood_FromStart() {
        mixBot.respond("test", "коктейль");
        assertEquals("food", mixBot.users.get("test").dialog);
    }

    @Test
    public void changeDialogToBasket_FromStart() {
        mixBot.respond("test", "ингредиент");
        assertEquals("basket", mixBot.users.get("test").dialog);
    }

    @Test
    public void stayInBasketDialog_When_SendingIngredients() {
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "томат");
        mixBot.respond("test", "водка");
        assertEquals("basket", mixBot.users.get("test").dialog);
    }
    
    @Test
    public void returnFood_When_IngredientsMatch() {
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "томат");
        mixBot.respond("test", "водка");
        Response response = mixBot.respond("test", "все");
        assertTrue(response.message.contains("кровавая мэри"));
    }

    @Test
    public void returnToStart_After_FinishingBasket() {
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "сельдерей");
        mixBot.respond("test", "всё");
        assertEquals("start", mixBot.users.get("test").dialog);
    }
    
    @Test
    public void returnFoodRecepie_When_ItExists() {
    	mixBot.respond("test", "2");
        Response response = mixBot.respond("test", "некролог");
        assertTrue(response.message.contains("вермут"));
    }
    
    @Test
    public void returnToStart_After_FinishingFood() {
        mixBot.respond("test", "2");
        mixBot.respond("test", "некролог");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    //спорное название
    @Test
    public void doNotThrowExcteption_When_IncorrectIngredientInBasket(){
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "ром");
        mixBot.respond("test", "гуано");
        assertEquals("basket", mixBot.users.get("test").dialog);
    }

    @Test
    public void goToStart_When_IncorrectFood(){
        mixBot.respond("test", "коктейль");
        mixBot.respond("test", "некробиолог");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void showHelp_When_AskingForHelp(){
        String response = mixBot.respond("test", "памагити").message;
        assertEquals("Если вы хотите получить информацию по конкретному коктейлю, напишите \"2\";" +
						"\nЕсли же вам нужна помощь по приготовлению из имеющихся у вас ингредиентов, " +
						"напишите \"1\"", response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void defaultResponse_At_IncorrectCommandAtStart(){
        String response = mixBot.respond("test", "kdjfbns").message;
        assertEquals(SimpleDialog.defaultResponse.message, response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }
    
    @Test
    public void usersDoNotIntersect() {
    	mixBot.respond("test", "1");
    	mixBot.respond("vasya", "ы");
    	mixBot.respond("petya", "2");
    	assertEquals("basket", mixBot.users.get("test").dialog);
    }

}