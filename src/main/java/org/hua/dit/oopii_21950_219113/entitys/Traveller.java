package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//TODO: Add documentation & SPRING TAGS
public abstract class Traveller
{
    private int age;
    private String name;
    private String cityName;
    private String country;

    //termVector [cafe = 0,sea = 1,museums = 2, restaurants = 3, stadiums = 4, mountains = 5, hotel = 6, metro = 7, bars = 8, sun = 9]
    private int[] termVector = new int[10];
    //geodesicVector [lat = 0 , lon = 0]
    private double[] geodesicVector = new double[2];

    /* CONSTRUCTORS START */

    public Traveller(int age, String name, String cityName, String country, int cafe, int sea, int museums, int restaurants, int stadiums, int mountains, int hotel, int metro, int bars, int sun) throws IOException {
        this.age = age;
        this.name = name;
        this.cityName = cityName;
        this.country = country;
        termVector[0] = cafe;
        termVector[1] = sea;
        termVector[2] = museums;
        termVector[3] = restaurants;
        termVector[4] = stadiums;
        termVector[5] =mountains;
        termVector[6] =hotel;
        termVector[7] =metro;
        termVector[8] =bars;
        termVector[9] =sun;
        ObjectMapper mapper = new ObjectMapper();
        OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + country + "&APPID=4abb3288d8abfd8b3b72670196c0175f"+""), OpenWeatherMap.class);
        geodesicVector[0] = weather_obj.getCoord().getLat();
        geodesicVector[1] = weather_obj.getCoord().getLon();
    }

    /* CONSTRUCTORS END */

    /*START GETTERS AND SETTERS FOR termVector*/

    //TODO: ERROR CHECKING/EXCEPTIONS
    public int getCafe() {
        return termVector[0];
    }

    public void setCafe(int cafe) {
        termVector[0] = cafe;
    }

    public int getSea() {
        return termVector[1];
    }

    public void setSea(int sea) {
        termVector[1] = sea;
    }

    public int getMuseums() {
        return termVector[2];
    }

    public void setMuseums(int museums) {
        termVector[2] = museums;
    }


    public int getRestaurants() {
        return termVector[3];
    }

    public void setRestaurants(int restaurants) {
        termVector[3] = restaurants;
    }

    public int getStadiums() {
        return termVector[4];
    }

    public void setStadiums(int stadiums) {
        termVector[4] = stadiums;
    }

    public int getMountains() {
        return termVector[5];
    }

    public void setMountains(int mountains) {
        termVector[5] = mountains;
    }

    public int getHotel() {
        return termVector[6];
    }

    public void setHotel(int hotel) {
        termVector[6] = hotel;
    }

    public int getMetro() {
        return termVector[7];
    }

    public void setMetro(int metro) {
        termVector[7] = metro;
    }

    public int getBars() {
        return termVector[8];
    }

    public void setBars(int bars) {
        termVector[8] = bars;
    }

    public int getSun() {
        return termVector[9];
    }

    public void setSun(int sun) {
        termVector[9] = sun;
    }

    public int[] getTermVector()
    {
        return termVector;
    }

    public void setTermVector(int[] termVector) {
        this.termVector = termVector;
    }

    /*END GETTERS AND SETTERS FOR termVector*/

    /*START GETTERS AND SETTERS FOR geodesicVector*/

    public double getLat() {
        return geodesicVector[0];
    }

    public void setLat(double lat) {
        geodesicVector[0] = lat;
    }

    public double getLon() {
        return geodesicVector[1];
    }

    public void setLon(double lon) {
        geodesicVector[1] = lon;
    }

    public double[] getGeodesicVector()
    {
        return geodesicVector;
    }

    public void setGeodesicVector(double[] geodesicVector) {
        this.geodesicVector = geodesicVector;
    }

    /*END GETTERS AND SETTERS FOR geodesicVectorr*/

    //TODO: Check for visibility issues, if some method does not need to be public.
    public abstract double similarityTermVector (City city);

    public double similarityGeodesicVector(City city)
    {
        double intoLog=0;
        double distanceCityTraveller;
        int maxdist = 15317;
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        distanceCityTraveller=distanceCalculator.distance(city.getLat(),city.getLon(),getLat(),getLon(),"k");
        intoLog=(2/(2-(distanceCityTraveller/maxdist)));
        double finalResult = (Math.log(intoLog) / Math.log(2));
        return finalResult;
    }

    public abstract double calculate_similarity(City city);
    //similarity (user,city) = p*similarity_terms_vector () + (1-p)  similarity_geodesic_vector ()

    public City compareCities(ArrayList<City> cities)
    {
        double maxSimilarity= calculate_similarity(cities.get(0));
        City bestCity=cities.get(0);
        for (City city : cities)
        {
            if(calculate_similarity(city)>maxSimilarity)
            {
                maxSimilarity= calculate_similarity(city);
                bestCity=city;
            }
        }
        return bestCity;
    }

}

