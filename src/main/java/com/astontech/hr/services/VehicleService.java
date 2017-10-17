package com.astontech.hr.services;

import com.astontech.hr.domain.Vehicle;

public interface VehicleService {

    Iterable<Vehicle> listAllVehicles();

    Vehicle getVehicleById(int id);

    Vehicle saveVehicle(Vehicle vehicle);

    Iterable<Vehicle> saveVehicle(Iterable<Vehicle> vehicleIterable);

    void deleteVehicle(Integer id);
}
