package org.hua.dit.oopii_21950_219113.Exceptions;

public class CityAlreadyExistsException extends Exception{

    /**
     * The serialization runtime associates with each serializable class a version number,
     * called a serialVersionUID, which is used during deserialization to verify that the sender and receiver
     * of a serialized object have loaded classes for that object that are compatible with respect to serialization
     */
    private static final long serialVersionUID = 10000000016L;

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
