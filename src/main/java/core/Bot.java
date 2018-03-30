package core;

import core.Keyboard.WeatherDay;
import core.Weather.HourForecast;
import core.Weather.WeatherData;
import core.Weather.WeatherParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.vdurmont.emoji.EmojiParser;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{

    private static final String BOT_NAME = "everydayhelpbot";
    private static final String BOT_TOKEN = "427144383:AAEtk7MbtsIDc2ea9TiiPzJGCyvEzhxaOCc";

    private String location = null;
    private float latitude = 190;
    private float longitude = 190;

    private static List<HourForecast> forecast = new ArrayList<>();

    private WeatherData weatherData;
    private WeatherParser weatherParser;

    public Bot(){
        weatherData = new WeatherData();
        weatherParser = new WeatherParser();
    }

    WeatherDay weatherDayKeyboard = new WeatherDay();

    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if(message.hasText()){
            if(message.getText().equals("/start")){

                String msg = EmojiParser.parseToUnicode(
                        "Hello, " + message.getChat().getFirstName() + "! :wave:" +
                        "\nI can help you in everyday life." +
                        "\nNow I can :muscle::" +
                        "\n:one:: /weather Tell the weather for today and tomorrow:sunny:.");

                SendMessage sendMsg = new SendMessage()
                        .setChatId(message.getChatId().toString())
                        .setText(msg);

                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }else if (message.getText().equals("/weather")){
                String msg = EmojiParser.parseToUnicode(
                        ":round_pushpin: You need to select a location to view the weather." +
                                "\nTo select a location enough to write the name of the settlement. " +
                                "For example: Kiev\uD83C\uDDFA\uD83C\uDDE6 or New York, USA:us: or " +
                                "send your Geolocation:globe_with_meridians:");

                SendMessage sendMsg = new SendMessage()
                        .setChatId(message.getChatId().toString())
                        .setText(msg);

                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if(message.getText().equals("Weather for today")){
                forecast.clear();
                if(weatherParser.parser(location) || weatherParser.parser(latitude,longitude)) {
                    forecast = weatherData.getTodayForecast();
                    String msg = "Weather for today - " + forecast.get(0).getDate();
                    msg += " in " + forecast.get(0).getLocation() + ":";
                    msg += System.lineSeparator();
                    for (HourForecast threeHourForecast : forecast) {
                        msg += "    " + threeHourForecast.getTimeFrom() + " - " + threeHourForecast.getTimeTo() + ": ";
                        msg += "\n" + threeHourForecast.getSky() + " \n\uD83C\uDF21 " + threeHourForecast.getTemp() + "°C" + " \nhumidity - " +
                                threeHourForecast.getHumidity() + "%:sweat_drops:\n";
                    }
                    SendMessage sendMsg = new SendMessage()
                            .setChatId(message.getChatId().toString())
                            .setReplyMarkup(weatherDayKeyboard)
                            .setText(EmojiParser.parseToUnicode(msg));

                    try {
                        execute(sendMsg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }else if (message.getText().length() > 9){
                if ("/location".equals(message.getText().substring(0, 9))){

                    location = message.getText().substring(9,message.getText().length());
                    String msg = "You wrote " + location +
                            " like your location";

                    SendMessage sendMsg = new SendMessage()
                            .setChatId(message.getChatId().toString())
                            .setText(msg);

                    try {
                        execute(sendMsg);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }  else if (message.getText().equals("/help")){
                String msg = "For weather : /weather" +
                            "\nlocation : /location + settlement or send Geolocation";
                SendMessage sendMsg = new SendMessage()
                        .setChatId(message.getChatId().toString())
                        .setText(msg);

                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else {
                String msg = "Try again or watch /help";
                SendMessage sendMsg = new SendMessage()
                        .setChatId(message.getChatId().toString())
                        .setText(msg);

                try {
                    execute(sendMsg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (message.hasLocation()){
            latitude = message.getLocation().getLatitude();
            longitude = message.getLocation().getLongitude();

            String msg = EmojiParser.parseToUnicode(
                    ":round_pushpin: Your location:" +
                    "\n latitude is  " + latitude +
                    "\n longitude is " + longitude);

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

    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
