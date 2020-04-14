package com.maths.microservice1.maths.service;

import com.maths.microservice1.maths.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    public Vehicle createVehicle(final String type,final String modelCode, final String brandName, final String launchDate) {


        return null;
    }

    public List<Vehicle> getAllVehicles(final int count) {

        Vehicle vehicle = new Vehicle(1,"test","test","test","test");
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicleList.add(vehicle);
        return vehicleList;
    }
   /* @Transactional(readOnly = true) */
    public Optional<Vehicle> getVehicle(final int id) {
        return null;
    }
}
