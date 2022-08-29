package de.starappeal.laderaumauslastung.db.entity;

import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import static org.springframework.data.annotation.AccessType.Type.*;

@Entity
@Table(name = "VEHICLE")
@AccessType(FIELD)
public class Vehicle implements Serializable {

    @Serial
    private static final long serialVersionUID = -51676233172L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "VEHICLE_ID", nullable = false)
    private String vehicleId;

    @Column(name = "LOADING_TIME", nullable = false)
    //TODO: belade and entladezeit to another entity (something like duration, with value and timeunit)
    // or what if it takes 2 minutes and 30 seconds? a table with columns each? (seconds, minutes, hours...)
    // or just calculate it in milliseconds, like this? is Integer even correct type, or rather Long?
    private Integer loadingTime;

    @Column(name = "DISCHARGE_TIME", nullable = false)
    private Integer dischargeTime;

    @OneToOne(targetEntity = Storage.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "STORAGE_ID", nullable = false)
    private Storage storage;

    @Column(name = "LOAD_FACTOR", nullable = false)
    private Double loadFactor;

    @Column(name = "BLOCKED", nullable = false)
    private boolean blocked;

    public Vehicle() {
        // for JPA
    }

    public Vehicle(Long id, String vehicleId, Integer loadingTime, Integer dischargeTime, Storage storage, Double loadFactor, boolean blocked) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.loadingTime = loadingTime;
        this.dischargeTime = dischargeTime;
        this.storage = storage;
        this.loadFactor = loadFactor;
        this.blocked = blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(Double loadFactor) {
        this.loadFactor = loadFactor;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (blocked != vehicle.blocked) return false;
        if (!id.equals(vehicle.id)) return false;
        if (!vehicleId.equals(vehicle.vehicleId)) return false;
        if (!loadingTime.equals(vehicle.loadingTime)) return false;
        if (!dischargeTime.equals(vehicle.dischargeTime)) return false;
        if (!storage.equals(vehicle.storage)) return false;
        return loadFactor.equals(vehicle.loadFactor);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + vehicleId.hashCode();
        result = 31 * result + loadingTime.hashCode();
        result = 31 * result + dischargeTime.hashCode();
        result = 31 * result + storage.hashCode();
        result = 31 * result + loadFactor.hashCode();
        result = 31 * result + (blocked ? 1 : 0);
        return result;
    }
}
