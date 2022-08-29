package de.starappeal.laderaumauslastung.model;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.db.entity.Storage;

public class VehicleModel {

    private String vehicleId;

    private Integer loadingTime;

    private Integer dischargeTime;

    private Double freeCapacity;

    private Double loadFactor;

    private boolean blocked;

    public VehicleModel(Vehicle vehicle) {
        Storage storage = vehicle.getStorage();
        this.vehicleId = vehicle.getVehicleId();
        this.loadingTime = vehicle.getLoadingTime();
        this.dischargeTime = vehicle.getDischargeTime();
        this.loadFactor = vehicle.getLoadFactor();
        this.freeCapacity = storage.getWidth() * storage.getDepth() / 2.4 - this.loadFactor;
        this.blocked = vehicle.isBlocked();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(Integer loadingTime) {
        this.loadingTime = loadingTime;
    }

    public Integer getDischargeTime() {
        return dischargeTime;
    }

    public void setDischargeTime(Integer dischargeTime) {
        this.dischargeTime = dischargeTime;
    }

    public Double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(Double loadFactor) {
        this.loadFactor = loadFactor;
    }

    public Double getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(Double freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
