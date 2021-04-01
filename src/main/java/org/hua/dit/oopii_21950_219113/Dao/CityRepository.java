package org.hua.dit.oopii_21950_219113.Dao;

import org.hua.dit.oopii_21950_219113.entitys.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    /**
     *
     * @param cityName The name of the city the client wants to "see"
     * @param country the country code, due to our database structure it is required to be in our query, also in order
     *                not to return the wrong city object(a city with the same name in an other country).
     * @return the requested city object.
     * @throws Exception In case something internally goes wrong or an I/O error occurs.
     */
    @Query("SELECT c FROM City c WHERE c.cityName=?1 AND c.country=?2")
    City findByName(String cityName, String country) throws Exception;

    //Here you add querys for extra methods the repo does not implimnets already, for example findCityByName().
}
