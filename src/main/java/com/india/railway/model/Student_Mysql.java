package com.india.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_mysql")
public class Student_Mysql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String gender;
    private int age;
    private String dob;
    private boolean agreeTerms;
    private boolean notifications;
    private String country;

    /*
     * @PostMapping
     * public String receiveStudentData(@RequestBody Student_Mysql Person) {
     * System.out.println("Received Student_Mysql data: " + Person);
     * return "Student_Mysql data received successfully!";
     * }
     */

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isAgreeTerms() {
        return agreeTerms;
    }

    public void setAgreeTerms(boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Student_Mysql{name='" + name + "', email='" + email + "', country='" + country + "'}";
    }
}
