package com.india.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.Customer_Mysql;

public interface Customer_Repo_Mysql extends JpaRepository<Customer_Mysql, Long> {
}
