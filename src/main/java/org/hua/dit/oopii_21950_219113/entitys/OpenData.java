package org.hua.dit.oopii_21950_219113.entitys;

import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;
import org.hua.dit.oopii_21950_219113.entitys.wikipedia.MediaWiki;

/**City description and weather information using OpenData with Jackson JSON processor.
* @since 29-2-2020
* @version 1.0
* @author John Violos */
public class OpenData {


/**Retrieves weather information, geotag (lan, lon) and a Wikipedia article for a given city.
* @param city The Wikipedia article and OpenWeatherMap city. 
*/
 public static String RetrieveData(String city) throws  IOException {
	 ObjectMapper mapper = new ObjectMapper();

	 MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);

	 System.out.println(city+" Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());

	 String article = mediaWiki_obj.getQuery().getPages().get(0).getExtract();

	 //Dev Code - Jast print the lat and lon
//	 OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&APPID="+appid), OpenWeatherMap.class);
//
//	 System.out.println(city+" temperature: " + (weather_obj.getMain()).getTemp());
//	 System.out.println(city+" lat: " + weather_obj.getCoord().getLat()+" lon: " + weather_obj.getCoord().getLon());

	 return article;
}


}