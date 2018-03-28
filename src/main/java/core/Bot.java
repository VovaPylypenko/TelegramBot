package core;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.List;

public class Bot extends TelegramLongPollingBot{

    private static final String BOT_NAME = "everydayhelpbot";
    private static final String BOT_TOKEN = "427144383:AAEtk7MbtsIDc2ea9TiiPzJGCyvEzhxaOCc";

    public void onUpdateReceived(Update update) {

    }

    public void onUpdatesReceived(List<Update> updates) {

    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
