package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleModel;

public interface VehicleModelService {

    Iterable<VehicleModel> listAllVehicleModels();

    VehicleModel getVehicleModelById(int id);

    VehicleModel saveVehicleModel(VehicleModel vehicleModel);

    Iterable<VehicleModel> saveVehicleModel(Iterable<VehicleModel> vehicleModelIterable);

    void deleteVehicleModel(Integer id);

    //custom methods
    VehicleModel findByVehicleModelName(String vehicleModelName);
}
