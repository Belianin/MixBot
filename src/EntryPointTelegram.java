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
	}

	@Override
	public void onUpdateReceived(Update e) {
		processMessage(e);
		//ListenThread a = new ListenThread();
	}

	private synchronized void processMessage(Update e) {
		Message msg = null;
		String text = null;

		if (e.hasMessage() && e.getMessage().hasText()) {
			msg = e.getMessage();
			text = msg.getText();
		} else if (e.hasCallbackQuery()) {
			msg = e.getCallbackQuery().getMessage();
			text = e.getCallbackQuery().getData();
		}

		if (msg == null || text == null)
			return;

		System.out.println(text);

		if (text.equals("/start"))
			sendMsg(msg, mixBot.initializeSession(msg.getChatId().toString()));
		else
			sendMsg(msg, mixBot.respond(msg.getChatId().toString(), text));
	}

	public void sendMsg(Message msg, Response response) {
		SendMessage s = new SendMessage();
		s.setChatId(msg.getChatId());
		s.setText(response.message);
		
		if (response.buttons != null && response.buttons.size() != 0)
			s.setReplyMarkup(getMarkup(response.buttons));

		try {
			execute(s);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private InlineKeyboardMarkup getMarkup(List<String> buttons) {

		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

		for (String buttonName : buttons) {
			InlineKeyboardButton button = new InlineKeyboardButton();
			button.setText(buttonName);
			button.setCallbackData(buttonName);
			keyboard.add(Arrays.asList(button));
		}

		markup.setKeyboard(keyboard);
		return markup;
	}

	@Override
	public String getBotToken() {
		return System.getenv("MIXBOT_TOKEN").replace("\"", "");
	}
}
