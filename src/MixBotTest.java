import org.junit.Test;

import static org.junit.Assert.*;

public class MixBotTest {

    @Test
    public void startToFoodDialogChanging() {
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "коктейль");
        assertEquals("food", MixBot.users.get("test").dialog);
    }

    @Test
    public void startToBasketDialogChanging() {
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "ингредиент");
        assertEquals("basket", MixBot.users.get("test").dialog);
    }

    @Test
    public void stillInBasket() {
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "ингредиент");
        MixBot.respond("test", "томат");
        MixBot.respond("test", "водка");
        assertEquals("basket", MixBot.users.get("test").dialog);
    }

    @Test
    public void returnToStart() {
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "ингредиент");
        MixBot.respond("test", "сельдерей");
        MixBot.respond("test", "всё");
        assertEquals("start", MixBot.users.get("test").dialog);
    }

    @Test
    public void defunctIngredient(){
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "ингредиент");
        MixBot.respond("test", "ром");
        MixBot.respond("test", "гуано");
        MixBot.respond("test", "всё");
        assertEquals("start", MixBot.users.get("test").dialog);
    }

    @Test
    public void defunctCocktail(){
        MixBot.initialize();
        MixBot.deleteUser("test");
        MixBot.respond("test", "коктейль");
        MixBot.respond("test", "некробиолог");
        assertEquals("start", MixBot.users.get("test").dialog);
    }

    @Test
    public void cryForHelp(){
        MixBot.initialize();
        MixBot.deleteUser("test");
        String response = MixBot.respond("test", "памагити");
        assertEquals("Если вы хотите получить информацию по конкретному коктейлю, напишите \"1\";" +
						"\nЕсли же вам нужна помощь по приготовлению из имеющихся у вас ингредиентов, " +
						"напишите \"2\"", response);
        assertEquals("start", MixBot.users.get("test").dialog);
    }

    @Test
    public void incorrectCommand(){
        MixBot.initialize();
        MixBot.deleteUser("test");
        String response = MixBot.respond("test", "kdjfbns");
        assertEquals(((SimpleDialog)(MixBot.dialogs.get("start"))).defaultResponse.message, response);
        assertEquals("start", MixBot.users.get("test").dialog);
    }

}