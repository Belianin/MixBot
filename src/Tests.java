
//import junit;
//class Tests {
//
//	@Test
//	void testDialogChanging() {
//		MixBot.initialize();
//		MixBot.deleteUser("test");
//
//		MixBot.respond("test", "готовить");
//		assertEquals("basket", MixBot.users.get("test").dialog);
//		MixBot.respond("test", "всё");
//		//ничего не добавили, из баскета не уходим
//		assertEquals("basket", MixBot.users.get("test").dialog);
//		MixBot.respond("test", "томат");
//		MixBot.respond("test", "всё");
//		//уходим в начало
//		assertEquals("start", MixBot.users.get("test").dialog);
//	}
//
//}
