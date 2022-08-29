package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.db.entity.Fahrzeug;
import de.starappeal.laderaumauslastung.db.entity.Laderaum;
import de.starappeal.laderaumauslastung.db.repository.FahrzeugRepository;
import de.starappeal.laderaumauslastung.exception.FahrzeugNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FahrzeugServiceTest {

    private FahrzeugRepository repository;
    private FahrzeugService service;

    @BeforeEach
    void setUp() {
        this.repository = mock(FahrzeugRepository.class);
        this.service = new FahrzeugService(repository);
    }

    @Test
    void findAll() {
        Laderaum laderaum = new Laderaum(1L, 1d, 2d, 3d);
        List<Fahrzeug> mockedFahrzeuge =
                List.of(
                        new Fahrzeug(1L, "Fahrzeugkennung 2", 1, 2, new Laderaum(), "a", false),
                        new Fahrzeug(2L, "Fahrzeugkennung 2", 12, 2, laderaum, "a", true)
        );

        when(repository.findAll()).thenReturn(
                mockedFahrzeuge
        );

        List<Fahrzeug> fahrzeuge = service.findAll();

        assertEquals(mockedFahrzeuge.size(), fahrzeuge.size());
        assertEquals(mockedFahrzeuge, fahrzeuge);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(new Fahrzeug()));

        Fahrzeug fahrzeug = service.findById(1L);

        assertNotNull(fahrzeug);
    }

    @Test
    void findById_FahrzeugNotFoundException() {
        assertThrows(FahrzeugNotFoundException.class, () -> service.findById(1L));
    }

}