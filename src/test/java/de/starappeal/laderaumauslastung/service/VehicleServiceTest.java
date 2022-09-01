package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.db.entity.Storage;
import de.starappeal.laderaumauslastung.db.repository.VehicleRepository;
import de.starappeal.laderaumauslastung.exception.VehicleCreateException;
import de.starappeal.laderaumauslastung.exception.VehicleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VehicleServiceTest {

    private VehicleRepository repository;
    private VehicleService service;

    private final Storage storage = new Storage(1L, 1d, 2d, 3d);

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        this.repository = mock(VehicleRepository.class);
        this.service = new VehicleService(repository);
        this.vehicle = new Vehicle(1L, "vehicleID", 2, 5, storage, 5.5, false);

    }

    @Test
    void create() {
        vehicle.setId(null);
        when(repository.save(eq(vehicle))).thenReturn(vehicle);

        Vehicle created = service.create(vehicle);

        assertEquals(vehicle, created);
    }

    @Test
    void create_VehicleCreateException() {
        assertThrows(VehicleCreateException.class, () -> service.create(vehicle));
    }

    @Test
    void bulkCreate() {
        List<Vehicle> vehicles = List.of(
                vehicle,
                new Vehicle(2L, "Vehicle 2", 1, 3, storage, 1.3, false),
                new Vehicle(3L, "Vehicle 3", 5, 13, storage, 1.3, true)
        );

        when(repository.existsById(any())).thenReturn(false);
        when(
                repository.saveAll(eq(vehicles))
        ).thenReturn(vehicles);

        List<Vehicle> created = service.bulkCreate(vehicles);

        assertEquals(vehicles.size(), created.size());
        assertEquals(vehicles, created);
    }

    @Test
    void bulkCreate_VehicleAlreadyExistsException() {
        when(repository.existsById(any())).thenReturn(true);

        assertThrows(VehicleCreateException.class, () -> service.bulkCreate(Collections.singletonList(vehicle)));
    }

    @Test
    void findAll() {
        List<Vehicle> mockedVehicles =
                List.of(
                        new Vehicle(1L, "Fahrzeugkennung 2", 1, 2, new Storage(), 3.3, false),
                        new Vehicle(2L, "Fahrzeugkennung 2", 12, 2, storage, 4.0, true)
                );

        when(
                repository.findAll()
        ).thenReturn(mockedVehicles);

        List<Vehicle> vehicles = service.findAll();

        assertEquals(mockedVehicles.size(), vehicles.size());
        assertEquals(mockedVehicles, vehicles);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Vehicle()));

        Vehicle vehicle = service.findById(1L);

        assertNotNull(vehicle);
    }

    @Test
    void findById_VehicleNotFoundException() {
        assertThrows(VehicleNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void update() {
        when(repository.existsById(eq(vehicle.getId()))).thenReturn(true);
        when(repository.save(eq(vehicle))).thenReturn(vehicle);

        Vehicle created = service.update(vehicle, vehicle.getId());

        assertEquals(vehicle, created);
    }

    @Test
    void update_VehicleNotFoundException() {
        when(repository.existsById(any())).thenReturn(false);

        assertThrows(VehicleNotFoundException.class, () -> service.update(vehicle, 213L));
    }

    //testing delete with mocked data wouldn't make any sense.
}