package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     *
     * @return travellers age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return travellers name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return cafe
     */
    public int getCafe() {
        return termVector[0];
    }

    /**
     *
     * @param cafe Value [0-10] of how desired the specific feature is.
     */
    public void setCafe(int cafe) {
        termVector[0] = cafe;
    }

    /**
     *
     * @return sea
     */
    public int getSea() {
        return termVector[1];
    }

    /**
     *
     * @param sea Value [0-10] of how desired the specific feature is.
     */
    public void setSea(int sea) {
        termVector[1] = sea;
    }

    /**
     *
     * @return museums
     */
    public int getMuseums() {
        return termVector[2];
    }

    /**
     *
     * @param museums Value [0-10] of how desired the specific feature is.
     */
    public void setMuseums(int museums) {
        termVector[2] = museums;
    }

    /**
     *
     * @return restaurant
     */
    public int getRestaurants() {
        return termVector[3];
    }

    /**
     *
     * @param restaurants Value [0-10] of how desired the specific feature is.
     */
    public void setRestaurants(int restaurants) {
        termVector[3] = restaurants;
    }

    /**
     *
     * @return stadiums
     */
    public int getStadiums() {
        return termVector[4];
    }

    /**
     *
     * @param stadiums Value [0-10] of how desired the specific feature is.
     */
    public void setStadiums(int stadiums) {
        termVector[4] = stadiums;
    }

    /**
     *
     * @return mountains
     */
    public int getMountains() {
        return termVector[5];
    }

    /**
     *
     * @param mountains Value [0-10] of how desired the specific feature is.
     */
    public void setMountains(int mountains) {
        termVector[5] = mountains;
    }

    /**
     *
     * @return hotel
     */
    public int getHotel() {
        return termVector[6];
    }

    /**
     *
     * @param hotel Value [0-10] of how desired the specific feature is.
     */
    public void setHotel(int hotel) {
        termVector[6] = hotel;
    }

    /**
     *
     * @return metro
     */
    public int getMetro() {
        return termVector[7];
    }

    /**
     *
     * @param metro Value [0-10] of how desired the specific feature is.
     */
    public void setMetro(int metro) {
        termVector[7] = metro;
    }

    /**
     *
     * @return bars
     */
    public int getBars() {
        return termVector[8];
    }

    /**
     *
     * @param bars Value [0-10] of how desired the specific feature is.
     */
    public void setBars(int bars) {
        termVector[8] = bars;
    }

    /**
     *
     * @return sun
     */
    public int getSun() {
        return termVector[9];
    }

    /**
     *
     * @param sun Value [0-10] of how desired the specific feature is.
     */
    public void setSun(int sun) {
        termVector[9] = sun;
    }

    /**
     *
     * @return Vector with all of the users preferences
     */
    public int[] getTermVector()
    {
        return termVector;
    }

    /**
     * Setting the user preferences table.
     *
     * @param termVector Setting the vector's "pointer", "point" at a new termVector
     */
    public void setTermVector(int[] termVector) {
        this.termVector = termVector;
    }

    /*END GETTERS AND SETTERS FOR termVector*/

    /*START GETTERS AND SETTERS FOR geodesicVector*/

    /**
     *
     * @return lat
     */
    public double getLat() {
        return geodesicVector[0];
    }

    /**
     *
     * @param lat Setting the vector's "pointer", "point" at a new latitude vector
     */
    public void setLat(double lat) {
        geodesicVector[0] = lat;
    }

    /**
     *
     * @return lon
     */
    public double getLon() {
        return geodesicVector[1];
    }

    /**
     *
     * @param lon Setting the vector's "pointer", "point" at a new longitude vector
     */
    public void setLon(double lon) {
        geodesicVector[1] = lon;
    }

    /**
     *
     * @return Vector with the coordinates of the calculated suitable city for the user
     */
    public double[] getGeodesicVector()
    {
        return geodesicVector;
    }

    /**
     * Setting the vector with the users best fitted city
     *
     * @param geodesicVector Setting the vector's "pointer", "point" at a new vector
     */
    public void setGeodesicVector(double[] geodesicVector) {
        this.geodesicVector = geodesicVector;
    }

    /*END GETTERS AND SETTERS FOR geodesicVectorr*/

    /**
     *
     * @param city City object on which we will do our calculations
     * @return A value from 0(min) to 1(max) which is how suitable is for the client the city based on the tremsVector(his preferences).
     */
    public abstract double similarityTermVector (City city);

    /**
     * Every Type of traveler is implementing the method with it's respective way.
     *
     * @param city The city we want to calculate the similarity of.
     * @return A value between 0(min) and 1(max) that shows how suitable is the city for the user by taking to account
     * both termsVector & geodesicVector
     */
    public abstract double calculate_similarity(City city);
    //similarity (user,city) = p*similarity_terms_vector () + (1-p)  similarity_geodesic_vector ()

    public Traveller calculate_free_ticket(City city, ArrayList<Traveller> travellers){

        double max = -1;
        Traveller winner = null;
        for(Traveller traveller:travellers){
            double tmpsimculc = traveller.calculate_similarity(city);
            if(max < tmpsimculc) {
                max = tmpsimculc;
                winner = traveller;
            }
        }

        return winner;

    }

    /**
     *
     * @param city The city object which we will do our calculations
     * @return A value from 0(min) to 1(max) of how suitable if the city for the client based on geodesicVector(The distance to the city).
     */
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

    /**
     * Iterates through the Array list to find the most suitasble city for the client. We keep a list
     *
     * @param cities A list with all the cities in our database
     * @return Only the best fitted city for the client
     */
    public List<City> compareCities(ArrayList<City> cities)
    {
        HashMap<City, Double> hashMapCities = new HashMap<>();
        for (City city : cities)
        {
            hashMapCities.put(city,calculate_similarity(city));
        }
        List<City> bestCities = hashMapCities.entrySet().stream().sorted(Map.Entry.<City, Double>comparingByValue().reversed()).limit(cities.size()).map(Map.Entry::getKey).collect(Collectors.toList());

        /*DEV BACK-UP CODE */
//        double maxSimilarity= calculate_similarity(cities.get(0));
//        City bestCity=cities.get(0);
//        for (City city : cities)
//        {
//            if(calculate_similarity(city)>maxSimilarity)
//            {
//                maxSimilarity= calculate_similarity(city);
//                bestCity=city;
//            }
//        }

//        return bestCity;

        return bestCities;
    }


    /**
     * If the client does not want the first option we will give him, he can enter an intiger between [2,5] in order to
     * give him back a list with the number of the next <int>choice</int> best fitted cities for him.
     *
     * @param choice Int [2,5], that indicates how many cities to return
     * @return bestCities
     * @throws
     */
    public List<City> compareCities(int choice,List<City> sortedCities){

        sortedCities.remove(0); //it removes the best choice that we dont want

        //Keep removing from the end of the list till we reach the desired list length.
        while (sortedCities.size()!=choice)
        {
            sortedCities.remove(sortedCities.size()-1);
        }
        return sortedCities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Traveller traveller = (Traveller) o;
        return Objects.equals(name, traveller.name);
    }


}
