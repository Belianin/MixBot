import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class EntryPointTelegram extends TelegramLongPollingBot {
	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botapi = new TelegramBotsApi();
		try {
			botapi.registerBot(new EntryPointTelegram());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getBotUsername() {
		return "USER";
                //возвращаем юзера
	}

	@Override
	public void onUpdateReceived(Update e) {
		e.getMessage().
	}

	@Override
	public String getBotToken() {
		return "YOUR_BOT_TOKEN";
                //Токен бота
	}
}
