package core.Weather;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WeatherParser {

    private static final String API_CALL  = "http://api.openweathermap.org/data/2.5/forecast?";

    private static final String CITY_NAME = "q=";
    private static final String CITY_LAT = "lat=";
    private static final String CITY_LON = "&lon=";
    private static final String API_KEY  = "&APPID=cb524a9a73927b723dba98540ff0a3f0";
    private static final String MODE = "&mode=xml";
    private static final String LANGUAGE_EN = "&lang=en";
    private static final String LANGUAGE_UA = "&lang=ua";
    private static final String UNITS = "&units=metric";

    public boolean parser(final String location){

        String query = API_CALL + CITY_NAME + location + MODE + LANGUAGE_EN + UNITS + API_KEY;

        query = query.replace(" ", "%20");

        return parse(query);
    }


    public boolean parser(final float latitude, final float longitude){
        String query = API_CALL + CITY_LAT + latitude + CITY_LON + longitude + MODE + LANGUAGE_EN + UNITS + API_KEY;

        query = query.replace(" ", "%20");

        return parse(query);
    }

    private boolean parse(String query){
        try {
            URL urlObject = new URL(query);

            InputStream in = urlObject.openStream();

            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            OpenWeatherMapHandler ourSpecialHandler = new OpenWeatherMapHandler();
            xmlReader.setContentHandler(ourSpecialHandler);

            InputSource inputSource = new InputSource(in);
            xmlReader.parse(inputSource);

            return true;
        }catch (IOException | SAXException e) {
            return false;
        }
    }

}
