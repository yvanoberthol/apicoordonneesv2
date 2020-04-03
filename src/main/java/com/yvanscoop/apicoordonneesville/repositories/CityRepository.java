package com.yvanscoop.apicoordonneesville.repositories;

import com.yvanscoop.apicoordonneesville.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("select c from City c where FORMAT(c.coordinate.latitude,4) = FORMAT(:lat,4) and FORMAT(c.coordinate.longitude,4) = FORMAT(:lng,4)")
    City findByCoordinateLatitudeAndCoordinateLongitude(@Param("lat") Float lat, @Param("lng") Float lng);
}
