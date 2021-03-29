package org.hua.dit.oopii_21950_219113.Dao;

import org.hua.dit.oopii_21950_219113.entitys.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    //Here you add querys for extra methods the repo does not implimnets already, for example findCityByName().
}
