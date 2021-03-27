package org.hua.dit.oopii_21950_219113.entitys;

import java.io.IOException;


//TODO: Add documentation
public class MiddleTraveller extends Traveller
{

    /* CONSTRUCTORS START */

    public MiddleTraveller(int age, String name, String cityName, String county, int cafe, int sea, int museums, int restaurants, int stadiums, int mountains, int hotel, int metro, int bars, int sun) throws IOException {
        super(age, name, cityName, county, cafe, sea, museums, restaurants, stadiums, mountains, hotel, metro, bars, sun);
    }

    /* CONSTRUCTORS END */

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
        finalResult=(arithmitis/(paranomastisA*paranomastisB));
        return finalResult;
    }

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
