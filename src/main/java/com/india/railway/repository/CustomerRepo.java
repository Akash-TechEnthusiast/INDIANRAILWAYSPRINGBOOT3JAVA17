package com.india.railway.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Customer_Redis;

@Repository
public interface CustomerRepo extends CrudRepository<Customer_Redis, String> {

}
