package org.hua.dit.oopii_21950_219113.entitys;

import org.hua.dit.oopii_21950_219113.Exceptions.NoSuchOpenWeatherCityException;

import java.io.IOException;

public class YoungTraveller extends Traveller
{

    /* CONSTRUCTORS START */

    /**
     * The basic custom constructor the children classes can use to be created
     *
     * @param age  Value [0-10] of how desired the specific feature is.
     * @param name Value [0-10] of how desired the specific feature is.
     * @param cityName Value [0-10] of how desired the specific feature is.
     * @param country Value [0-10] of how desired the specific feature is.
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
    public YoungTraveller(int age, String name, String cityName, String country, int cafe, int sea, int museums, int restaurants, int stadiums, int mountains, int hotel, int metro, int bars, int sun) throws IOException, NoSuchOpenWeatherCityException {
        super(age, name, cityName, country, cafe, sea, museums, restaurants, stadiums, mountains, hotel, metro, bars, sun);
    }

    public YoungTraveller(){

    }

    public YoungTraveller(String name, String country) throws IOException, NoSuchOpenWeatherCityException {
        super(name,country);
    }

    /* CONSTRUCTORS END */

    /**
     * Implemeting the logic for calculating how suitable is a city for a client based on his preferences and how we calculate it for a
     * young clinet.
     *
     * @param city
     * @return Value between 0(min) and 1(max).
     */
    @Override
    public double similarityTermVector(City city)
    {
        int[] cityTermVectorVector = city.getTermVector();
        int[] travellerTermVector = getTermVector();
        double[] cityGeodesicVector = city.getGeodesicVector();
        double[] travellerGeodesicVector = getGeodesicVector();
        double finalResult=0;
        int intResult=0;
        for(int i=0;i<10;i++)
        {
            //that's the Σ((user(i)-city(i))
            intResult+=(travellerTermVector[i]-cityTermVectorVector[i])*(travellerTermVector[i]-cityTermVectorVector[i]);
        }
        finalResult=1/(1+Math.sqrt(intResult));
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
        double p=0.95;
        double firstParametre;
        double secondParametre;
        firstParametre=p*similarityTermVector(city);
        secondParametre=(1-p)*similarityGeodesicVector(city);
        return firstParametre+secondParametre;
    }

}
