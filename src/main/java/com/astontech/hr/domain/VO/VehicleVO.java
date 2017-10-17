package com.astontech.hr.domain.VO;

public class VehicleVO {

    private String newYear;
    private String newLicensePlate;
    private String newVin;
    private String newColor;

    private int newVehicleModelId;
    private int newVehicleMakeId; //<-- never used

    public String getNewYear() {
        return newYear;
    }

    public void setNewYear(String newYear) {
        this.newYear = newYear;
    }

    public String getNewLicensePlate() {
        return newLicensePlate;
    }

    public void setNewLicensePlate(String newLicensePlate) {
        this.newLicensePlate = newLicensePlate;
    }

    public String getNewVin() {
        return newVin;
    }

    public void setNewVin(String newVin) {
        this.newVin = newVin;
    }

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public int getNewVehicleModelId() {
        return newVehicleModelId;
    }

    public void setNewVehicleModelId(int newVehicleModelId) {
        this.newVehicleModelId = newVehicleModelId;
    }

    public int getNewVehicleMakeId() {
        return newVehicleMakeId;
    }

    public void setNewVehicleMakeId(int newVehicleMakeId) {
        this.newVehicleMakeId = newVehicleMakeId;
    }
}
