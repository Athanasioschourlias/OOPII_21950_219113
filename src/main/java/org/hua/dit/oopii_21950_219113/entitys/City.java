package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Columns;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;

//TODO: Add documentation
@Entity
@Table
public class City {

    //ID
    @Id
    @Column(nullable = false, length = 100)
    private String cityName;

    @Column(nullable = false, length = 50)
    private String country;

    private int cafe;
    private int sea;
    private int museums;
    private int restaurants;
    private int stadiums;
    private int mountains;
    private int hotel;
    private int metro;
    private int bars;
    private int sun;
    private double lat;
    private double lon;


    //termVector [cafe = 0,sea = 1,museums = 2, restaurants = 3, stadiums = 4, mountains = 5, hotel = 6, metro = 7, bars = 8, sun = 9]
    @Transient
    private int[] termVector = new int[10];
    //geodesicVector [lat = 0 , lon = 0]

    @Transient
    private double[] geodesicVector = new double[2];

    /* CONSTRUCTORS START */

    /**
     * Creating a custom constructor to initialize all the values/criteria the user gave(not for the 1st-3rd deliverables).
     * We also extracting the latitude and longitude of the city from wiki API.
     *
     * @param cityName
     * @param country
     * @param cafe
     * @param sea
     * @param museums
     * @param restaurants
     * @param stadiums
     * @param mountains
     * @param hotel
     * @param metro
     * @param bars
     * @param sun
     * @throws IOException
     */
    public City(String cityName,String country, int cafe, int sea, int museums, int restaurants, int stadiums,int mountains,int hotel,int metro,int bars,int sun) throws IOException {
        this.cityName = cityName;
        this.country=country;
        this.cafe = cafe;
        this.sea = sea;
        this.museums = museums;
        this.restaurants = restaurants;
        this.stadiums = stadiums;
        this.mountains=mountains;
        this.hotel=hotel;
        this.metro=metro;
        this.bars=bars;
        this.sun=sun;
        ObjectMapper mapper = new ObjectMapper();
        OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "," + country + "&APPID=4abb3288d8abfd8b3b72670196c0175f"+""), OpenWeatherMap.class);
        this.lat = weather_obj.getCoord().getLat();
        this.lon = weather_obj.getCoord().getLon();
    }

    /**
     * One no argument constructor is needed by spring.
     */
    public City() {

    }

    /* CONSTRUCTORS END */

    /*START GETTERS AND SETTERS FOR termVector*/

    //TODO: ERROR CHECKING/EXCEPTIONS
    //TODO: Value checking for the setters?!?

    /**
     *
     * @return cafe
     */
    public int getCafe() {
        return this.cafe;
    }

    /**
     *
     * @param cafe
     */
    public void setCafe(int cafe) {
        this.cafe = cafe;
    }

    /**
     *
     * @return sea
     */
    public int getSea() {
        return this.sea;
    }

    /**
     *
     * @param sea
     */
    public void setSea(int sea) {
        this.sea = sea;
    }

    /**
     *
     * @return museums
     */
    public int getMuseums() {
        return this.museums;
    }

    /**
     *
     * @param museums
     */
    public void setMuseums(int museums) {
        this.museums = museums;
    }

    /**
     *
     * @return restaurants
     */
    public int getRestaurants() {
        return this.restaurants;
    }

    /**
     *
     * @param restaurants
     */
    public void setRestaurants(int restaurants) {
        this.restaurants = restaurants;
    }

    /**
     *
     * @return stadiums
     */
    public int getStadiums() {
        return this.stadiums;
    }

    /**
     *
     * @param stadiums
     */
    public void setStadiums(int stadiums) {
        this.stadiums = stadiums;
    }

    /**
     *
     * @return mountains
     */
    public int getMountains() {
        return this.mountains;
    }

    /**
     *
     * @param mountains
     */
    public void setMountains(int mountains) {
        this.mountains = mountains;
    }

    /**
     *
     * @return hotel
     */
    public int getHotel() {
        return this.hotel;
    }

    /**
     *
     * @param hotel
     */
    public void setHotel(int hotel) {
        this.hotel = hotel;
    }

    /**
     *
     * @return metro
     */
    public int getMetro() {
        return this.metro;
    }

    /**
     *
     * @param metro
     */
    public void setMetro(int metro) {
        this.metro = metro;
    }

    /**
     *
     * @return bars
     */
    public int getBars() {
        return this.bars;
    }

    /**
     *
     * @param bars
     */
    public void setBars(int bars) {
        this.bars = bars;
    }

    /**
     *
     * @return sun
     */
    public int getSun() {
        return this.sun;
    }

    /**
     *
     * @param sun
     */
    public void setSun(int sun) {
        this.sun = sun;
    }

    /**
     *
     * @return A vector with metrics for every criteria the user has chosen(setted).
     */
    public int[] getTermVector()
    {
        termVector[0]=getCafe();
        termVector[1]=getSea();
        termVector[2]=getMuseums();
        termVector[3]=getRestaurants();
        termVector[4]=getStadiums();
        termVector[5]=getMountains();
        termVector[6]=getHotel();
        termVector[7]=getMetro();
        termVector[8]=getBars();
        termVector[9]=getSun();
        return termVector;
    }

    /**
     * setting the vector to the values of the given parameter vector.
     *
     * @param termVector
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
        return this.lat;
    }

    /**
     *
     * @param lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     *
     * @return lon
     */
    public double getLon() {
        return this.lon;
    }

    /**
     *
     * @param lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return A vector with the values of the latitude and longitude(at the respective positions) of the city we have calculated for the uses
     */
    public double[] getGeodesicVector()
    {
        geodesicVector[0]=getLat();
        geodesicVector[1]=getLon();
        return geodesicVector;
    }

    /**
     * Setting the users city latitude and longitude(At their respective positions). Of the calculated result.
     * @param geodesicVector
     */
    public void setGeodesicVector(double[] geodesicVector) {
        this.geodesicVector = geodesicVector;
    }

    /*END GETTERS AND SETTERS FOR geodesicVectorr*/

    /*Start GETTERS AND SETTERS FOR other City object variables*/

    /**
     *
     * @return cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     *
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /*END GETTERS AND SETTERS FOR other City object variables*/




}
