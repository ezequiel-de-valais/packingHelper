package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Elemento extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String nombre;

    @Constraints.Required
    private Integer pesoIndividual;

    @Constraints.Required
    private boolean deMano;

    @Constraints.Required
    private boolean consumible;

    public static Finder<Long, Elemento> FIND = new Finder<Long, Elemento>(
            Long.class, Elemento.class
    );

    public static Elemento findById(Long id) {
        return FIND.byId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPesoIndividual() {
        return pesoIndividual;
    }

    public void setPesoIndividual(Integer pesoIndividual) {
        this.pesoIndividual = pesoIndividual;
    }

    public boolean isDeMano() {
        return deMano;
    }

    public void setDeMano(boolean deMano) {
        this.deMano = deMano;
    }

    public boolean isConsumible() {
        return consumible;
    }

    public void setConsumible(boolean consumible) {
        this.consumible = consumible;
    }

    public Elemento(String nombre, Integer pesoIndividual, boolean deMano, boolean consumible) {
        this.nombre = nombre;
        this.pesoIndividual = pesoIndividual;
        this.deMano = deMano;
        this.consumible = consumible;
    }
}