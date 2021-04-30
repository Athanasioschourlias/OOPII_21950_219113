package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class JsonSaver {

    /**
     *
     * @param travellerlist The list with all of the Traveller(it's children) objects
     */
    public void writeJSON(ArrayList<Traveller> travellerlist){
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
    public ArrayList<Traveller> readJSON(){
        ArrayList<Traveller>  out_arraylist = new ArrayList<>();

            try {

            ArrayList obj = (ArrayList) new JSONParser().parse(new FileReader("JsonTravellers/travellers.json"));

            //Constracting the objects
            for(Object o : obj){
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;
                JsonSaver jsonSaver = new JsonSaver();
                long age = (long) jo.get("age");
                Map visit = ((Map)jo.get("visit"));
                Long cafe= (Long) visit.get("cafe");
                Long sea= (Long) visit.get("sea");
                Long museums= (Long) visit.get("museums");
                Long restaurants= (Long) visit.get("restaurants");
                Long stadiums= (Long) visit.get("stadiums");
                Long mountaints= (Long) visit.get("mountains");
                Long hotel= (Long) visit.get("hotel");
                Long metro= (Long) visit.get("metro");
                Long bars= (Long) visit.get("bars");
                Long sun= (Long) visit.get("sun");
                if (age >= 16 && age <= 25)
                {
                    YoungTraveller youngTraveller = new YoungTraveller();
                    Long trAge = (Long) jo.get("age");
                    youngTraveller.setAge(trAge.intValue());
                    youngTraveller.setName((String) jo.get("name"));
                    youngTraveller.setCityName((String) jo.get("cityName"));
                    youngTraveller.setCountry((String) jo.get("country"));
                    youngTraveller.setTimeStamp((long) jo.get("timeStamp"));
                    String visitCityName=((String) visit.get("cityName"));
                    String visitCityCountry=((String) visit.get("country"));
                    City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                    JSONArray Vector = (JSONArray) visit.get("termVector");
                    visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) visit.get("geodesicVector");
                    visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    youngTraveller.setVisit(visitCity);
                    Vector = (JSONArray) jo.get("termVector");
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
                    String visitCityName=((String) visit.get("cityName"));
                    String visitCityCountry=((String) visit.get("country"));
                    City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                    JSONArray Vector = (JSONArray) visit.get("termVector");
                    visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) visit.get("geodesicVector");
                    visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    middleTraveller.setVisit(visitCity);
                    Vector = (JSONArray) jo.get("termVector");
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
                    String visitCityName=((String) visit.get("cityName"));
                    String visitCityCountry=((String) visit.get("country"));
                    City visitCity = new City(visitCityName,visitCityCountry,cafe.intValue(),sea.intValue(),museums.intValue(),restaurants.intValue(),stadiums.intValue(),mountaints.intValue(),hotel.intValue(),metro.intValue(),bars.intValue(),sun.intValue());
                    JSONArray Vector = (JSONArray) visit.get("termVector");
                    visitCity.setTermVector(jsonSaver.convertJSONArrayToInt(Vector));
                    Vector = (JSONArray) visit.get("geodesicVector");
                    visitCity.setGeodesicVector(jsonSaver.convertJSONArrayToDouble(Vector));
                    elderTraveller.setVisit(visitCity);
                    Vector = (JSONArray) jo.get("termVector");
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
