package com.angelamadjar.airbnbproject.repository;


import com.angelamadjar.airbnbproject.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// The Repository layer is responsible for the app's communication with the Database
// JPA makes SQL queries much easier by creating them in the background depending on the function names
// No code repositories
@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findAllByCity(String city);

}
