package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.mysql.Employee;

@Repository
public interface EmployeRepository extends JpaRepository<Employee, Long> {

}