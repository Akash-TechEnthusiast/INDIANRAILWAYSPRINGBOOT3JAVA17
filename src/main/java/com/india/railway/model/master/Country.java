package com.india.railway.model.master;

import java.util.List;

import com.india.railway.model.mysql.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "country_state", joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "state_id", referencedColumnName = "id"))

    private List<State> state;

    public List<State> getState() {
        return state;
    }

    public void setState(List<State> state) {
        this.state = state;
    }

    // Default constructor
    public Country() {
    }

    // Constructor with fields
    public Country(String name, String code) {
        this.name = name;
        this.code = code;

    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
