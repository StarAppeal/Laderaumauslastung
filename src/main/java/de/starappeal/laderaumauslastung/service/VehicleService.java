package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.api.SearchRequest;
import de.starappeal.laderaumauslastung.api.SearchSpecification;
import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.db.repository.VehicleRepository;
import de.starappeal.laderaumauslastung.exception.VehicleAlreadyExistsException;
import de.starappeal.laderaumauslastung.exception.VehicleNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private static final Logger logger = LogManager.getLogger(VehicleService.class);

    private final VehicleRepository repository;

    @Autowired
    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    public Vehicle create(Vehicle vehicle) {
        logger.info("VehicleService.create has been called with parameters ({})", vehicle);
        if (repository.existsById(vehicle.getId())) {
            throw new VehicleAlreadyExistsException(
                    "Vehicle with ID %d already exists, a current existing vehicle would be updated!"
                            .formatted(vehicle.getId())
            );
        }
        Vehicle vehicleCreated = repository.save(vehicle);
        logger.info("Vehicle with id {} has been created", vehicleCreated.getId());
        return vehicleCreated;
    }

    public List<Vehicle> bulkCreate(List<Vehicle> vehicles) {
        logger.info("VehicleService.bulkCreate has been called with parameters ({})", vehicles);
        if (vehicles.stream().anyMatch(d -> repository.existsById(d.getId()))) {
            throw new VehicleAlreadyExistsException("List contains a vehicle with an id which is already present, it would update the existing one!");
        }
        List<Vehicle> vehiclesCreated = repository.saveAll(vehicles);
        vehiclesCreated.forEach(v -> logger.info("Vehicle with id {} has been created", v.getId()));
        return vehiclesCreated;
    }

    public List<Vehicle> findAll() {
        logger.info("VehicleService.findAll has been called.");
        return repository.findAll();
    }

    public Vehicle findById(Long id) {
        logger.info("VehicleService.findById with parameters ({}) has been called", id);
        return repository.findById(id)
                .orElseThrow(
                        () -> new VehicleNotFoundException("Vehicle with ID %d not found!".formatted(id))
                );
    }

    public Vehicle update(Vehicle vehicle) {
        logger.info("VehicleService.update with parameters ({}) has been called", vehicle);
        if (!repository.existsById(vehicle.getId())) {
            throw new VehicleNotFoundException("Vehicle with ID %d not found, a new vehicle would be created!!".formatted(vehicle.getId()));
        }
        return repository.save(vehicle);
    }

    public void delete(Long id) {
        logger.info("VehicleService.delete with parameters ({}) has been called", id);
        repository.deleteById(id);
    }

    public Page<Vehicle> findByFilter(SearchRequest request) {
        SearchSpecification<Vehicle> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.page(), request.size());
        return repository.findAll(specification, pageable);
    }

}
