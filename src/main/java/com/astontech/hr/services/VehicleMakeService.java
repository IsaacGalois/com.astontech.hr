package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleMake;

public interface VehicleMakeService {

    Iterable<VehicleMake> listAllVehicleMakes();

    VehicleMake getVehicleMakeById(int id);

    VehicleMake saveVehicleMake(VehicleMake VehicleMake);

    Iterable<VehicleMake> saveVehicleMake(Iterable<VehicleMake> VehicleMakeIterable);

    void deleteVehicleMake(Integer id);
}
