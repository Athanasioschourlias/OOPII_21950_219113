package org.hua.dit.oopii_21950_219113.entitys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Columns;
import org.hua.dit.oopii_21950_219113.Dao.CityId;
import org.hua.dit.oopii_21950_219113.entitys.weather.OpenWeatherMap;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;

@Entity
@Table(name = "CITY")
@IdClass(CityId.class)
public class City {

    /**
     * With the help of the CityId class and the annotations of @IdClass & @Id's we create unique composite primary key's
     * for our database.
     */
    @Id
    @Column(nullable = false)
    private String cityName;

    @Id
    @Column(nullable = false)
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
     * @param cityName Name of the city
     * @param country Country code
     * @param cafe How many times the word cafe is referenced in the wiki text for the country
     * @param sea How many times the word sea is referenced in the wiki text for the country
     * @param museums How many times the word museums is referenced in the wiki text for the country
     * @param restaurants How many times the word restaurants is referenced in the wiki text for the country
     * @param stadiums How many times the word stadiums is referenced in the wiki text for the country
     * @param mountains How many times the word mountains is referenced in the wiki text for the country
     * @param hotel How many times the word hotel is referenced in the wiki text for the country
     * @param metro How many times the word metro is referenced in the wiki text for the country
     * @param bars How many times the word bars is referenced in the wiki text for the country
     * @param sun How many times the word sun is referenced in the wiki text for the country
     * @throws IOException Failed or interrupted I/O operation.
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

    /**
     *
     * @return cafe
     */
    public int getCafe() {
        return this.cafe;
    }

    /**
     *
     * @param cafe setting the number of times the word appeared in the wiki text
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
     * @param sea setting the number of times the word appeared in the wiki text
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
     * @param museums setting the number of times the word appeared in the wiki text
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
     * @param restaurants setting the number of times the word appeared in the wiki text
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
     * @param stadiums setting the number of times the word appeared in the wiki text
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
     * @param mountains setting the number of times the word appeared in the wiki text
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
     * @param hotel setting the number of times the word appeared in the wiki text
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
     * @param metro setting the number of times the word appeared in the wiki text
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
     * @param bars setting the number of times the word appeared in the wiki text
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
     * @param sun setting the number of times the word appeared in the wiki text
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
     *
     * @param termVector a vector that has all the parametric values of a City object.
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
     * @param lat latitude of the city
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
     * @param lon longitude of the city
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
     *
     * @param geodesicVector Setting the users city latitude and longitude(At their respective positions). Of the calculated result.
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
