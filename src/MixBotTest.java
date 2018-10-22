import org.junit.Test;

import static org.junit.Assert.*;

public class MixBotTest {

    @Test
    public void startToFoodDialogChanging() {
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "коктейль");
        assertEquals("food", mixBot.users.get("test").dialog);
    }

    @Test
    public void startToBasketDialogChanging() {
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "ингредиент");
        assertEquals("basket", mixBot.users.get("test").dialog);
    }

    @Test
    public void stillInBasket() {
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "томат");
        mixBot.respond("test", "водка");
        assertEquals("basket", mixBot.users.get("test").dialog);
    }

    @Test
    public void returnToStart() {
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "сельдерей");
        mixBot.respond("test", "всё");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void defunctIngredient(){
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "ингредиент");
        mixBot.respond("test", "ром");
        mixBot.respond("test", "гуано");
        mixBot.respond("test", "всё");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void defunctCocktail(){
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        mixBot.respond("test", "коктейль");
        mixBot.respond("test", "некробиолог");
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void cryForHelp(){
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        String response = mixBot.respond("test", "памагити");
        assertEquals("Если вы хотите получить информацию по конкретному коктейлю, напишите \"2\";" +
						"\nЕсли же вам нужна помощь по приготовлению из имеющихся у вас ингредиентов, " +
						"напишите \"1\"", response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }

    @Test
    public void incorrectCommandAtStart(){
    	MixBot mixBot = new MixBot();
        mixBot.deleteUser("test");
        String response = mixBot.respond("test", "kdjfbns");
        assertEquals(SimpleDialog.defaultResponse.message, response);
        assertEquals("start", mixBot.users.get("test").dialog);
    }

}