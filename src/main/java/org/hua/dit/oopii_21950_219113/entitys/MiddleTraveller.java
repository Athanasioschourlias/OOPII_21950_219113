package org.hua.dit.oopii_21950_219113.entitys;

import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;

import java.io.IOException;


public class MiddleTraveller extends Traveller
{

    /* CONSTRUCTORS START */

    /**
     * The basic custom constructor the children classes can use to be created
     *
     * @param age The age of the traveller
     * @param name Name of the traveller
     * @param cityName The Name of the city of residence
     * @param country Country code of residence
     * @param cafe Value [0-10] of how desired the specific feature is.
     * @param sea Value [0-10] of how desired the specific feature is.
     * @param museums Value [0-10] of how desired the specific feature is.
     * @param restaurants Value [0-10] of how desired the specific feature is.
     * @param stadiums Value [0-10] of how desired the specific feature is.
     * @param mountains Value [0-10] of how desired the specific feature is.
     * @param hotel Value [0-10] of how desired the specific feature is.
     * @param metro Value [0-10] of how desired the specific feature is.
     * @param bars Value [0-10] of how desired the specific feature is.
     * @param sun Value [0-10] of how desired the specific feature is.
     * @throws IOException
     */
    public MiddleTraveller(int age, String name, String cityName, String country, int cafe, int sea, int museums, int restaurants, int stadiums, int mountains, int hotel, int metro, int bars, int sun) throws IOException, NoSuchOpenWeatherCityException {
        super(age, name, cityName, country, cafe, sea, museums, restaurants, stadiums, mountains, hotel, metro, bars, sun);
    }

    public MiddleTraveller() {

    }

    public MiddleTraveller(String name, String country) throws IOException, NoSuchOpenWeatherCityException {
        super(name,country);
    }

    /* CONSTRUCTORS END */

    /**
     * Implemeting the logic for calculating how suitable is a city for a client based on how we calculate it for a
     * middle aged client.
     *
     * @param city
     * @return Value between 0(min) and 1(max).
     */
    @Override
    public double similarityTermVector(City city)
    {
        int[] cityTermVectorVector = city.getTermVector();
        int[] travellerTermVector = getTermVector();
        int arithmitis=0;
        int paranomastisA=0;
        int paranomastisB=0;
        double finalResult;
        for (int i =0 ; i<10;i++)
        {
            arithmitis+=(cityTermVectorVector[i]*travellerTermVector[i]);
            paranomastisA+=(cityTermVectorVector[i]*cityTermVectorVector[i]);
            paranomastisB+=(travellerTermVector[i]*travellerTermVector[i]);
        }
        finalResult=(arithmitis/(Math.sqrt(paranomastisA)*Math.sqrt(paranomastisB)));
        return finalResult;
    }

    /**
     * Implementing the logic how we calculate the final suitability value for the client and the city.
     *
     * @param city
     * @return Value between 0(min) and 1(max).
     */
    @Override
    public double calculate_similarity(City city) {
        //similarity (user,city) = p*similarity_terms_vector () + (1-p)  similarity_geodesic_vector ()
        double p=0.5;
        double firstParametre;
        double secondParametre;
        firstParametre=p*similarityTermVector(city);
        secondParametre=(1-p)*similarityGeodesicVector(city);
        return firstParametre+secondParametre;
    }

}
