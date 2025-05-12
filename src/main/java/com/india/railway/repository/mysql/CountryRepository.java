package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.master.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}