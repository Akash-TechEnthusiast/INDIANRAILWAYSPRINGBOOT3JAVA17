package com.india.railway.controller.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.mysql.Passenger;
import com.india.railway.model.mysql.Student_Mysql;
import com.india.railway.service.mysql.PassengerServiceImpl;
import com.india.railway.service.mysql.StudentService_Mysql;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
// Allow React Frontend
public class PassengerController_Mysql {

    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    @PostMapping("/create")
    public String addPassenger(@RequestBody Passenger passenger) throws IllegalAccessException {
        return passengerServiceImpl.addPassenger(passenger);
    }

    @GetMapping("/fetch_all_passenger")
    public List<Passenger> getAllPassengers() {
        return passengerServiceImpl.getAllPassengers();

    }

    @GetMapping("getPassengerById/{id}")
    public Optional<Passenger> getPassengerById(@PathVariable Long id) {
        return passengerServiceImpl.getPassenger(id);
    }

    @PutMapping("updatePassenger/{id}")
    public String updatePassenger(@RequestBody Passenger passenger) {
        return passengerServiceImpl.updatePassenger(passenger);
    }

}
