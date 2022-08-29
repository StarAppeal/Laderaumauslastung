package de.starappeal.laderaumauslastung.db.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

import static javax.persistence.AccessType.FIELD;

@Entity
@Table(name = "LADERAUM")
@Access(FIELD)
public class Laderaum implements Serializable {

    @Serial
    private static final long serialVersionUID = 945109214217L;

    public Laderaum() {
        // for JPA
    }

    public Laderaum(Long id, Double hoehe, Double breite, Double tiefe) {
        this.id = id;
        this.hoehe = hoehe;
        this.breite = breite;
        this.tiefe = tiefe;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "HOEHE", nullable = false)
    private Double hoehe;

    @Column(name = "BREITE", nullable = false)
    private Double breite;

    @Column(name = "TIEFE", nullable = true)
    private Double tiefe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHoehe() {
        return hoehe;
    }

    public void setHoehe(Double hoehe) {
        this.hoehe = hoehe;
    }

    public Double getBreite() {
        return breite;
    }

    public void setBreite(Double breite) {
        this.breite = breite;
    }

    public Double getTiefe() {
        return tiefe;
    }

    public void setTiefe(Double tiefe) {
        this.tiefe = tiefe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Laderaum laderaum = (Laderaum) o;

        if (!id.equals(laderaum.id)) return false;
        if (!hoehe.equals(laderaum.hoehe)) return false;
        if (!breite.equals(laderaum.breite)) return false;
        return tiefe.equals(laderaum.tiefe);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + hoehe.hashCode();
        result = 31 * result + breite.hashCode();
        result = 31 * result + tiefe.hashCode();
        return result;
    }
}
