package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.Dao.CityRepository;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.hua.dit.oopii_21950_219113.Service.TravellersService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class JsonSaver extends Thread {
    ArrayList<Traveller>  out_arraylist = new ArrayList<>();
    CityRepository cityRepository;
    TravellersService travellersService= new TravellersService(cityRepository);

    public JsonSaver() {}

    @Override
    public void run()
    {
        travellersService.travellers=readJSON();
    }
    /**
     *
     * @param travellerlist The list with all of the Traveller(it's children) objects
     */
    public void writeJSON(ArrayList<Traveller> travellerlist)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("JsonTravellers/travellers.json"), travellerlist);

        } catch (IOException e) {
            System.out.println("exception " + e.getMessage());
            //TODO: LOG THE EXIT
//            e.printStackTrace();
        }
    }

    /**
     *
     * @return An array list of the objects we read from the json file.
     */
    public ArrayList<Traveller> readJSON()
    {

            try
            {

            ArrayList obj = (ArrayList) new JSONParser().parse(new FileReader("JsonTravellers/travellers.json"));

            //Constracting the objects
            for(Object o : obj){
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;
                JsonSaver jsonSaver = new JsonSaver();
                long age = (long) jo.get("age");
                Map visit = ((Map)jo.get("visit"));
                Long cafe = null;
                Long sea=null;
                Long museums=null;
                Long restaurants=null;
                Long stadiums=null;
                Long mountaints=null;
                Long hotel=null;
                Long metro=null;
                Long bars=null;
                Long sun=null;
                if(visit!=null)
                {
                    cafe= (Long) visit.get("cafe");
                    sea= (Long) visit.get("sea");
                    museums= (Long) visit.get("museums");
                    restaurants= (Long) visit.get("restaurants");
                    stadiums= (Long) visit.get("stadiums");
                    mountaints= (Long) visit.get("mountains");
                    hotel= (Long) visit.get("hotel");
                    metro= (Long) visit.get("metro");
                    bars= (Long) visit.get("bars");
                    sun= (Long) visit.get("sun");
                }

                if (age >= 16 && age <= 25)
                {
                    YoungTraveller youngTraveller = new YoungTraveller();
                    Long trAge = (Long) jo.get("age");
                    youngTraveller.setAge(trAge.intValue());
                    youngTraveller.setName((String) jo.get("name"));
                    youngTraveller.setCityName((String) jo.get("cityName"));
                    youngTraveller.setCountry((String) jo.get("country"));
                    youngTraveller.setTimeStamp((long) jo.get("timeStamp"));
                    if(visit!=null)
                    {
                        String visitCityName=((String) visit.get("cityName"));
                        String visitCityCountry=((String) visit.get("country"));
                        City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                        JSONArray Vector = (JSONArray) visit.get("termVector");
                        visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                        Vector = (JSONArray) visit.get("geodesicVector");
                        visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                        youngTraveller.setVisit(visitCity);
                    }
                    JSONArray Vector = (JSONArray) jo.get("termVector");
                    youngTraveller.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) jo.get("geodesicVector");
                    youngTraveller.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    out_arraylist.add(youngTraveller);
                } else if (age > 25 && age <= 60)
                {
                    MiddleTraveller middleTraveller = new MiddleTraveller();
                    Long trAge = (Long) jo.get("age");
                    middleTraveller.setAge(trAge.intValue());
                    middleTraveller.setName((String) jo.get("name"));
                    middleTraveller.setCityName((String) jo.get("cityName"));
                    middleTraveller.setCountry((String) jo.get("country"));
                    middleTraveller.setTimeStamp((long) jo.get("timeStamp"));
                    if(visit!=null)
                    {
                        String visitCityName=((String) visit.get("cityName"));
                        String visitCityCountry=((String) visit.get("country"));
                        City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                        JSONArray Vector = (JSONArray) visit.get("termVector");
                        visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                        Vector = (JSONArray) visit.get("geodesicVector");
                        visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                        middleTraveller.setVisit(visitCity);
                    }
                    JSONArray Vector = (JSONArray) jo.get("termVector");
                    middleTraveller.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) jo.get("geodesicVector");
                    middleTraveller.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    out_arraylist.add(middleTraveller);
                } else if (age > 60 && age <= 115)
                {
                    ElderTraveller elderTraveller = new ElderTraveller();
                    Long trAge = (Long) jo.get("age");
                    elderTraveller.setAge(trAge.intValue());
                    elderTraveller.setName((String) jo.get("name"));
                    elderTraveller.setCityName((String) jo.get("cityName"));
                    elderTraveller.setCountry((String) jo.get("country"));
                    elderTraveller.setTimeStamp((long) jo.get("timeStamp"));
                    if(visit!=null)
                    {
                        String visitCityName=((String) visit.get("cityName"));
                        String visitCityCountry=((String) visit.get("country"));
                        City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                        JSONArray Vector = (JSONArray) visit.get("termVector");
                        visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                        Vector = (JSONArray) visit.get("geodesicVector");
                        visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                        elderTraveller.setVisit(visitCity);
                    }
                    JSONArray Vector = (JSONArray) jo.get("termVector");
                    elderTraveller.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) jo.get("geodesicVector");
                    elderTraveller.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    out_arraylist.add(elderTraveller);
                } else
                {
                    System.out.println("Error Age");
                }

            }
            return out_arraylist;
        } catch (IOException e) {
            System.out.println("There was a problem, try to reload the page.");
            //TODO: log the output
            //e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NoSuchOpenWeatherCityException e) {
                    e.printStackTrace();
                }

        return null;
    }

    public int[] convertJSONArrayToInt (JSONArray jsonArray)
    {
        Long[] array = new Long [jsonArray.size()];
        int[] finalArray = new int[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); ++i) {
            array[i] = (Long) jsonArray.get(i);
        }
        for(int i = 0 ; i <array.length; i++)
        {
            finalArray[i]=array[i].intValue();
        }
        return finalArray;

    }
    public double[] convertJSONArrayToDouble (JSONArray jsonArray)
    {
        double[] array = new double [jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); ++i) {
            array[i] = (double) jsonArray.get(i);
        }
        return array;

    }

}
