package de.starappeal.laderaumauslastung.db.repository;

import de.starappeal.laderaumauslastung.db.entity.Fahrzeug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface FahrzeugRepository extends JpaRepository<Fahrzeug, Long> {

}
