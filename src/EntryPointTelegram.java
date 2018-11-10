import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntryPointTelegram extends TelegramLongPollingBot {
	static MixBot mixBot;
	public static void main(String[] args) {
		System.out.println("Starting...");
		mixBot = new MixBot("data/user telegram/");
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new EntryPointTelegram());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		System.out.println("Settled");
	}
	
	@Override
	public String getBotUsername() {
		return "IngriBot";
                //возвращаем юзера
	}

	@Override
	public void onUpdateReceived(Update e) {
		if (e.hasMessage() && e.getMessage().hasText()) {
		Message msg = e.getMessage();
		String text = msg.getText();		
		
		System.out.println(text);
		//sendMsg(msg, text);
		if (text.equals("/start"))
			sendMsg(msg, mixBot.initializeSession(msg.getChatId().toString()));
		else
			sendMsg(msg, mixBot.respond(msg.getChatId().toString(), text));
		}
		else if (e.hasCallbackQuery())
		{
			EditMessageText msg = new EditMessageText()
                    .setChatId(e.getCallbackQuery().getMessage().getChatId())
                    .setMessageId(e.getCallbackQuery().getMessage().getMessageId())
                    .setText(e.getCallbackQuery().getData());
			try {
				execute(msg);
			} catch (TelegramApiException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public void sendMsg(Message msg, String text) {
		SendMessage s = new SendMessage();
		s.setChatId(msg.getChatId());
		s.setText(text);
		s.setReplyMarkup(getMarkup());
		
		try {
			execute(s);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private InlineKeyboardMarkup getMarkup()
	{

		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
		

		InlineKeyboardButton button = new InlineKeyboardButton();
		button.setText("КНУПОЧКА");
		button.setCallbackData("test_Back");
		
		keyboard.add(Arrays.asList(button));
		markup.setKeyboard(keyboard);
		return markup;
	}

	@Override
	public String getBotToken() {
		return System.getenv("MIXBOT_TOKEN").replace("\"", "");
	}
}
