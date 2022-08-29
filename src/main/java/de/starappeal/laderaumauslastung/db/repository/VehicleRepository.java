package de.starappeal.laderaumauslastung.db.repository;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
