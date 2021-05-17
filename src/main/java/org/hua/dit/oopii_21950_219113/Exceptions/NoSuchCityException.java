package org.hua.dit.oopii_21950_219113.Exceptions;

public class NoSuchCityException extends Throwable {

    private static final long serialVersionUID = 10000002129L;


    static int numExcepetions=0;

    private String cityName;

    public NoSuchCityException(String CityName) {
        numExcepetions++;
        this.cityName=CityName;
    }

    public String getMessage() {

        return "It was not possible to retrieve the city "+cityName+" please check for spelling errors and try again.";
    }

}
