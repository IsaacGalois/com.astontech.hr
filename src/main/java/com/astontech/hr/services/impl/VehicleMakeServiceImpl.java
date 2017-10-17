package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.repositories.VehicleMakeRepository;
import com.astontech.hr.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService{

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Override
    public Iterable<VehicleMake> listAllVehicleMakes() {
        return vehicleMakeRepository.findAll();
    }

    @Override
    public VehicleMake getVehicleMakeById(int id) {
        return vehicleMakeRepository.findOne(id);
    }

    @Override
    public VehicleMake saveVehicleMake(VehicleMake vehicleMake) {
        return vehicleMakeRepository.save(vehicleMake);
    }

    @Override
    public Iterable<VehicleMake> saveVehicleMake(Iterable<VehicleMake> vehicleMakeIterable) {
        return vehicleMakeRepository.save(vehicleMakeIterable);
    }

    @Override
    public void deleteVehicleMake(Integer id) {
        vehicleMakeRepository.delete(id);
    }
}
