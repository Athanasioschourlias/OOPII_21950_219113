package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonSaver {


    /**
     *
     * @param travellerlist The list with all of the Traveller(it's children) objects
     * @throws IOException In case there is an error opening, finding, or writing to the file
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
     * @throws IOException In case there is an error opening, finding, or writing to the file
     */
    public ArrayList<Traveller> readJSON(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File("./JsonTravellers/travellers.json"), ArrayList.class);
        } catch (IOException e) {
            System.out.println("There was a problem, try to reload the page.");
            //TODO: log the output
            //e.printStackTrace();
        }

        return null;
    }

}
