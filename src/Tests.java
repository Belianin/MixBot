import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tests {

	@Test
	void testDialogChanging() {
		MixBot.initialize();
		MixBot.deleteUser("test");
		
		MixBot.respond("test", new String[] {"готовить"});
		assertEquals("basket", MixBot.users.get("test").dialog);
		MixBot.respond("test", new String[] {"всё"});
		//ничего не добавили, из баскета не уходим
		assertEquals("basket", MixBot.users.get("test").dialog);
		MixBot.respond("test", new String[] {"томат"});
		MixBot.respond("test", new String[] {"всё"});
		//уходим в начало
		assertEquals("start", MixBot.users.get("test").dialog);
	}

}
