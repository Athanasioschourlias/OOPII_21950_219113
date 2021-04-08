package org.hua.dit.oopii_21950_219113;

import org.hua.dit.oopii_21950_219113.entitys.ElderTraveller;
import org.hua.dit.oopii_21950_219113.entitys.MiddleTraveller;
import org.hua.dit.oopii_21950_219113.entitys.YoungTraveller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class AddTraveller {

    private HashMap<Integer, ArrayList<String>> hm;

    public AddTraveller(){

        hm = new HashMap<>();

        JSONParser parser = new JSONParser();
        JSONArray a = null;
        try {
            a = (JSONArray) parser.parse(new FileReader("./other/city.list.json"));
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        int j = 0;
        ArrayList<String> values;
        assert a != null;
        for (Object o : a)
        {
            JSONObject city = (JSONObject) o;

            String name = (String) city.get("name");
            String country = (String) city.get("country");
            values = new ArrayList<>();
            values.add(name);
            values.add(country);
            hm.put(j, values);
            j++;

        }
    }

    public YoungTraveller makeYt(){
        return null;
    }

    public MiddleTraveller makeMt(){
        return null;
    }

    public ElderTraveller makeEt(){
        return null;
    }

    /**
     * RANDOM NAME GENERATOR
     */
    // class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    final Set<String> identifiers = new HashSet<>();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
