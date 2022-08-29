package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.db.repository.VehicleRepository;
import de.starappeal.laderaumauslastung.exception.VehicleAlreadyExistsException;
import de.starappeal.laderaumauslastung.exception.VehicleNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private static final  Logger logger = Logger.getLogger(VehicleService.class);

    private final VehicleRepository repository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle create(Vehicle vehicle) {
        logger.info("VehicleService.create has been called with parameters (%s)".formatted(vehicle));
        if (repository.existsById(vehicle.getId())) {
            throw new VehicleAlreadyExistsException(
                    "Vehicle with ID %d already exists, a current existing vehicle would be updated!"
                            .formatted(vehicle.getId())
            );
        }
        Vehicle vehicleCreated = repository.save(vehicle);
        logger.info("Vehicle with id %d has been created".formatted(vehicleCreated.getId()));
        return vehicleCreated;
    }

    public List<Vehicle> bulkCreate(List<Vehicle> vehicles) {
        logger.info("VehicleService.bulkCreate has been called with parameters (%s)".formatted(vehicles));
        if (vehicles.stream().anyMatch(d -> repository.existsById(d.getId()))) {
            throw new VehicleAlreadyExistsException("List contains a vehicle with an id which is already present, it would update the existing one!");
        }
        List<Vehicle> vehiclesCreated = repository.saveAll(vehicles);
        vehiclesCreated.forEach(v -> logger.info("Vehicle with id %d has been created".formatted(v.getId())));
        return vehiclesCreated;
    }

    public List<Vehicle> findAll() {
        logger.info("VehicleService.findAll has been called.");
        return repository.findAll();
    }

    public Vehicle findById(Long id) {
        logger.info("VehicleService.findById with parameters (%d) has been called".formatted(id));
        return repository.findById(id)
                .orElseThrow(
                        () -> new VehicleNotFoundException("Vehicle with ID %d not found!".formatted(id))
                );
    }

    public Vehicle update(Vehicle vehicle) {
        logger.info("VehicleService.update with parameters (%s) has been called".formatted(vehicle));
        if (!repository.existsById(vehicle.getId())) {
            throw new VehicleNotFoundException("Vehicle with ID %d not found, a new vehicle would be created!!".formatted(vehicle.getId()));
        }
        return repository.save(vehicle);
    }

    public void delete(Long id) {
        logger.info("VehicleService.delete with parameters (%d) has been called".formatted(id));
        repository.deleteById(id);
    }

}
