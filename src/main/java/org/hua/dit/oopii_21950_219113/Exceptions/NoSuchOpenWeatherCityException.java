package org.hua.dit.oopii_21950_219113.Exceptions;

/**
 * Exception for when there is no such article found in wiki API for the desired city.
 */
public class NoSuchOpenWeatherCityException extends Exception{

    /**
     * The serialization runtime associates with each serializable class a version number,
     * called a serialVersionUID, which is used during deserialization to verify that the sender and receiver
     * of a serialized object have loaded classes for that object that are compatible with respect to serialization
     */
    private static final long serialVersionUID = 10000002127L;


    static int numExcepetions=0;

    private String cityName;

    public NoSuchOpenWeatherCityException(String in_cityName) {
        numExcepetions++;
        this.cityName=in_cityName;
    }

    public String getMessage() {

        return "It was not possible to retrieve the city "+cityName+" please check for spelling errors and try again.";
    }


}
