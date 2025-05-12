package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.master.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
