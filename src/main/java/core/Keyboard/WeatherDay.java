package core.Keyboard;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class WeatherDay extends ReplyKeyboardMarkup {

    private List<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();

    private final String BACK_BUTTON = "Go back";

    private final String TODAY_BUTTON = "Weather for today";
    private final String TOMORROW_BUTTON = "Weather for tomorrow";

    public WeatherDay(){
        setResizeKeyboard(true);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(BACK_BUTTON);
        keyboardRow.add(TODAY_BUTTON);
        keyboardRow.add(TOMORROW_BUTTON);
        keyboardRows.add(keyboardRow);

        setKeyboard(keyboardRows);
    }

}
