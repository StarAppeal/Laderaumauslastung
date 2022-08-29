package de.starappeal.laderaumauslastung.service;

import de.starappeal.laderaumauslastung.db.entity.Fahrzeug;
import de.starappeal.laderaumauslastung.db.repository.FahrzeugRepository;
import de.starappeal.laderaumauslastung.exception.FahrzeugNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FahrzeugService {

    private final FahrzeugRepository repository;

    @Autowired
    public FahrzeugService(FahrzeugRepository repository) {
        this.repository = repository;
    }

    public List<Fahrzeug> findAll() {
        return repository.findAll();
    }

    public Fahrzeug findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new FahrzeugNotFoundException("Fahrzeug with ID %d not found!".formatted(id))
                );
    }

}
