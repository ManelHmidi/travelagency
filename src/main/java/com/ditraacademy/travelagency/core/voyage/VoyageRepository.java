package com.ditraacademy.travelagency.core.voyage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
    List<Voyage> findAllByPrixIsBetweenAndNbPlacesNot (float min,float max, int a);

    @Query(value = "SELECT * from voyage where nb_places != ?2 and prix > ?1", nativeQuery = true)
    List<Voyage> findAllByQuery(@Param("price")float price, @Param("nbPlaces") Integer nbPlaces);



}
