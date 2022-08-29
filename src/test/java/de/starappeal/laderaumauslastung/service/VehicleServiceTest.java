package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.db.entity.Vehicle;
import de.starappeal.laderaumauslastung.db.entity.Storage;
import de.starappeal.laderaumauslastung.db.repository.VehicleRepository;
import de.starappeal.laderaumauslastung.exception.VehicleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VehicleServiceTest {

    private VehicleRepository repository;
    private VehicleService service;

    @BeforeEach
    void setUp() {
        this.repository = mock(VehicleRepository.class);
        this.service = new VehicleService(repository);
    }

    @Test
    void findAll() {
        Storage storage = new Storage(1L, 1d, 2d, 3d);
        List<Vehicle> mockedVehicles =
                List.of(
                        new Vehicle(1L, "Fahrzeugkennung 2", 1, 2, new Storage(), 3.3, false),
                        new Vehicle(2L, "Fahrzeugkennung 2", 12, 2, storage, 4.0, true)
        );

        when(repository.findAll()).thenReturn(
                mockedVehicles
        );

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

}