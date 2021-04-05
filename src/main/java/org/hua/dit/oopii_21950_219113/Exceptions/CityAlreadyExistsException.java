package org.hua.dit.oopii_21950_219113.Exceptions;

public class CityAlreadyExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    private String CityName;

    public CityAlreadyExistsException() {

    }

    public CityAlreadyExistsException(String CityName){
        this.CityName = CityName;
    }

    @Override
    public String toString() {
        return "A Citi with the following name is already stored in our database{" +
                "CityName='" + CityName + '\'' +
                '}';
    }
}
