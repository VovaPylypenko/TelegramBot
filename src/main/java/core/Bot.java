package core;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.List;

public class Bot extends TelegramLongPollingBot{

    private static final String BOT_NAME = "everydayhelpbot";
    private static final String BOT_TOKEN = "427144383:AAEtk7MbtsIDc2ea9TiiPzJGCyvEzhxaOCc";

    public Bot(){

    }

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if(message.hasText()){
            if(message.getText().equals("/start")){

                String msg = "Hello," + message.getChat().getFirstName() + "!" +
                        "\nI can help you in everyday life." +
                        "\nNow I can:" +
                        "\nSay hello.";

                SendMessage sendMsg = new SendMessage()
                        .setChatId(message.getChatId().toString())
                        .setText(msg);

                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
