package core.Weather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OpenWeatherMapHandler extends DefaultHandler {

    private boolean inTomorrow = false;
    private boolean inToday = false;

    private boolean byLocation = false;
    private String location;

    private boolean byGeo = false;
    private float latitude;
    private float longitude;

    private DateGetter dateGetter = new DateGetter();

    private WeatherData weatherData = new WeatherData();

    private HourForecast hourForecast;

    public OpenWeatherMapHandler() {
        super();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("time")) {
            hourForecast = new HourForecast(
                    (attributes.getValue(0).substring(0,10)),
                    (attributes.getValue(0).substring(11,16)),
                    (attributes.getValue(1).substring(11,16))
            );

            if (hourForecast.getDate().equals(dateGetter.getTomorrowDate().substring(0, 10))) {
                inTomorrow = true;
            } else {
                inTomorrow = false;
            }

            if (hourForecast.getDate().equals(dateGetter.getCurrentDate().substring(0, 10))) {
                inToday = true;
            } else {
                inToday = false;
            }

            hourForecast.setLocation(location);
        }

        if(qName.equals("name")){
            byLocation = true;
        }

        if (qName.equals("temperature")) {
            hourForecast.setTemp(attributes.getValue(1).toString());
        }

        if(qName.equals("humidity")){
            hourForecast.setHumidity(Integer.parseInt(attributes.getValue(0)));
        }

        if(qName.equals("symbol")){
            hourForecast.setSky(attributes.getValue(1));
        }

        if(inToday && qName.equals("time")){
            weatherData.addTodayForecast(hourForecast);
        }

        if(inTomorrow && qName.equals("time")){
            weatherData.addTomorrowForecast(hourForecast);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(byLocation){
            location = new String(ch, start, length);
            byLocation = false;
        }
    }
}
