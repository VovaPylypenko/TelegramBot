package core.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {

    private static List<HourForecast> tomorrowForecast = new ArrayList<>(8);
    private static List<HourForecast> todayForecast = new ArrayList<>();

    public void addTomorrowForecast(final HourForecast threeHourForecast){
        tomorrowForecast.add(threeHourForecast);
    }
    public void addTodayForecast(final HourForecast threeHourForecast){
        todayForecast.add(threeHourForecast);
    }

    public  List<HourForecast> getTomorrowForecast() {
        return tomorrowForecast;
    }

    public  List<HourForecast> getTodayForecast() {
        return todayForecast;
    }

    public void cleanAll()
    {
        todayForecast.clear();
        tomorrowForecast.clear();
    }
}
