package org.hua.dit.oopii_21950_219113.Exceptions;

/**
 * Excetpion that indicates there isn't a city with the specified details. Wrong I/O or there are no info for it.
 */
public class NoSuchWikipediaCityException extends Exception{

    /**
     * The serialization runtime associates with each serializable class a version number,
     * called a serialVersionUID, which is used during deserialization to verify that the sender and receiver
     * of a serialized object have loaded classes for that object that are compatible with respect to serialization
     */
    private static final long serialVersionUID = 10000000222L;

    public NoSuchWikipediaCityException(String message) {
        super(message);
    }

    public NoSuchWikipediaCityException() {

    }
}
