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
    public void returnToStart_After_FinishingBasket() {
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "сельдерей");
        mixBot.respond("test", "всё");
        assertEquals("start", mixBot.users.get("test").dialog);
    }
    
    @Test
    public void returnToStart_After_FinishingFoodd() {
        mixBot.respond("test", "2");
        mixBot.respond("test", "некролог");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void doNotThrowExcteption_When_IncorrectIngredientInBasket(){
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "ром");
        mixBot.respond("test", "гуано");
        mixBot.respond("test", "всё");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void goToStart_When_IncorrectFood(){
        mixBot.respond("test", "коктейль");
        mixBot.respond("test", "некробиолог");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void helpMessage_When_AskingForHelp(){
        String response = mixBot.respond("test", "памагити").message;
        assertEquals("Если вы хотите получить информацию по конкретному коктейлю, напишите \"2\";" +
						"\nЕсли же вам нужна помощь по приготовлению из имеющихся у вас ингредиентов, " +
						"напишите \"1\"", response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void defaultResponse_At_IncorrectCommandAtStart_(){
        String response = mixBot.respond("test", "kdjfbns").message;
        assertEquals(SimpleDialog.defaultResponse.message, response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }

}