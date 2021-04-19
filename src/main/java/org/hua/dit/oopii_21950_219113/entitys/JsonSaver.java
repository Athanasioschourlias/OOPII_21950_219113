package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
        Gson gson = new Gson();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("./JsonTravellers/travellers.json"), travellerlist);

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
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Traveller>  out_arraylist = new ArrayList<>();

//            List tr = mapper.readValue(new File("./JsonTravellers/travellers.json"), List.class);
            // parsing file "JSONExample.json"
            ArrayList obj = (ArrayList) new JSONParser().parse(new FileReader("./JsonTravellers/travellers.json"));

            //Constracting the objects
            for(Object o : obj){
                // typecasting obj to JSONObject
                JSONObject jo = (JSONObject) o;

                Long age = (Long) jo.get("age");
                String name = (String) jo.get("name");
                String city = (String) jo.get("cityName");
                String country = (String) jo.get("country");
                Long timestamp = (Long) jo.get("timeStamp");

                //getting visit
                Map visit = ((Map)jo.get("visit"));

                // iterating address Map
                Iterator<Map.Entry> itr1 = visit.entrySet().iterator();

                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }

                //TODO: Finish constracting the object.


            }



            return out_arraylist;
        } catch (IOException e) {
            System.out.println("There was a problem, try to reload the page.");
            //TODO: log the output
            //e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
