package com.india.railway.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // @EntityGraph(attributePaths = "trains")
    // Optional<Passenger> findById(Long id);
}