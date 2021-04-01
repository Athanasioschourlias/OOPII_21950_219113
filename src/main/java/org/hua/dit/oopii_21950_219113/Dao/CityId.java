package org.hua.dit.oopii_21950_219113.Dao;

import java.io.Serializable;
import java.util.Objects;

public class CityId implements Serializable {

    private String cityName;

    private String country;

    /**
     * Required no arg constructor form spring docs.
     */
    public CityId(){

    }

    /**
     *
     * @param cityName setting the city's name
     * @param country setting the country's id code.
     */
    public CityId(String cityName, String country){
        this.cityName = cityName;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityId cityId = (CityId) o;
        return cityName.equals(cityId.cityName) && country.equals(cityId.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, country);
    }

}
