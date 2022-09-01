package de.starappeal.laderaumauslastung.db.entity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "STORAGE")
@Access(FIELD)
public class Storage implements Serializable {

    @Serial
    private static final long serialVersionUID = 945109214217L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "HEIGHT", nullable = false)
    private Double height;

    @Column(name = "WIDTH", nullable = false)
    private Double width;

    @Column(name = "DEPTH", nullable = false)
    private Double depth;

    public Storage() {
        // for JPA
    }

    public Storage(Long id, Double height, Double width, Double depth) {
        this.id = id;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Storage storage = (Storage) o;

        if (!id.equals(storage.id)) return false;
        if (!height.equals(storage.height)) return false;
        if (!width.equals(storage.width)) return false;
        return depth.equals(storage.depth);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + height.hashCode();
        result = 31 * result + width.hashCode();
        result = 31 * result + depth.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                '}';
    }
}
