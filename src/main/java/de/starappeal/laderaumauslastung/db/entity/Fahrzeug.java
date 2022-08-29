package de.starappeal.laderaumauslastung.db.entity;

import org.springframework.data.annotation.AccessType;

import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;

import static org.springframework.data.annotation.AccessType.Type.*;

@Entity
@Table(name = "FAHRZEUG")
@AccessType(FIELD)
public class Fahrzeug implements Serializable {

    @Serial
    private static final long serialVersionUID = -51676233172L;

    public Fahrzeug() {
        // for JPA
    }

    public Fahrzeug(Long id, String fahrzeugKennung, Integer beladeZeit, Integer entladeZeit, Laderaum laderaum, String belegung, boolean blockiert) {
        this.id = id;
        this.fahrzeugKennung = fahrzeugKennung;
        this.beladeZeit = beladeZeit;
        this.entladeZeit = entladeZeit;
        this.laderaum = laderaum;
        this.belegung = belegung;
        this.blockiert = blockiert;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "FAHRZEUGKENNUNG", nullable = false)
    private String fahrzeugKennung;

    @Column(name = "BELADEZEIT", nullable = false)
    //TODO: belade and entladezeit to another entity (something like duration, with value and timeunit)
    // or what if it takes 2 minutes and 30 seconds? a table with columns each? (seconds, minutes, hours...)
    // or just calculate it in milliseconds, like this? is Integer even correct type, or rather Long?
    private Integer beladeZeit;

    @Column(name = "ENTLADEZEIT", nullable = false)
    private Integer entladeZeit;

    @OneToOne(targetEntity = Laderaum.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "LADERAUM_ID", nullable = false)
    private Laderaum laderaum;

    @Column(name = "BELEGUNG", nullable = false)
    private String belegung; //TODO : change to Enum

    @Column(name = "BLOCKIERT", nullable = false)
    private boolean blockiert;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFahrzeugKennung() {
        return fahrzeugKennung;
    }

    public void setFahrzeugKennung(String fahrzeugKennung) {
        this.fahrzeugKennung = fahrzeugKennung;
    }

    public Integer getBeladeZeit() {
        return beladeZeit;
    }

    public void setBeladeZeit(Integer beladeZeit) {
        this.beladeZeit = beladeZeit;
    }

    public Integer getEntladeZeit() {
        return entladeZeit;
    }

    public void setEntladeZeit(Integer entladeZeit) {
        this.entladeZeit = entladeZeit;
    }

    public Laderaum getLaderaum() {
        return laderaum;
    }

    public void setLaderaum(Laderaum laderaum) {
        this.laderaum = laderaum;
    }

    public String getBelegung() {
        return belegung;
    }

    public void setBelegung(String belegung) {
        this.belegung = belegung;
    }

    public boolean isBlockiert() {
        return blockiert;
    }

    public void setBlockiert(boolean blockiert) {
        this.blockiert = blockiert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fahrzeug fahrzeug = (Fahrzeug) o;

        if (blockiert != fahrzeug.blockiert) return false;
        if (!id.equals(fahrzeug.id)) return false;
        if (!fahrzeugKennung.equals(fahrzeug.fahrzeugKennung)) return false;
        if (!beladeZeit.equals(fahrzeug.beladeZeit)) return false;
        if (!entladeZeit.equals(fahrzeug.entladeZeit)) return false;
        if (!laderaum.equals(fahrzeug.laderaum)) return false;
        return belegung.equals(fahrzeug.belegung);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fahrzeugKennung.hashCode();
        result = 31 * result + beladeZeit.hashCode();
        result = 31 * result + entladeZeit.hashCode();
        result = 31 * result + laderaum.hashCode();
        result = 31 * result + belegung.hashCode();
        result = 31 * result + (blockiert ? 1 : 0);
        return result;
    }
}
