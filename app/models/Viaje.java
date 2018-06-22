package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity 
public class Viaje extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private Date fechaInicio;

    @Constraints.Required
    private Date fechaFin;

    @Constraints.Required
    private Integer espacioLibreEnVuelta;

    @ManyToOne
    private Viajero viajero;

    @ManyToOne
    private Lugar destino;

    @OneToMany(mappedBy = "viaje", cascade= CascadeType.ALL)
    private List<Item> items = new ArrayList<Item>();


    public static Finder<Long, Viaje> FIND = new Finder<Long, Viaje>(
            Long.class, Viaje.class
    );

    public static Viaje findById(Long id) {
        return FIND.byId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEspacioLibreEnVuelta() {
        return espacioLibreEnVuelta;
    }

    public void setEspacioLibreEnVuelta(Integer espacioLibreEnVuelta) {
        this.espacioLibreEnVuelta = espacioLibreEnVuelta;
    }

    public Viajero getViajero() {
        return viajero;
    }

    public void setViajero(Viajero viajero) {
        this.viajero = viajero;
    }

    public Lugar getDestino() {
        return destino;
    }

    public void setDestino(Lugar destino) {
        this.destino = destino;
    }

    public Viaje(Date fechaInicio, Date fechaFin, Integer espacioLibreEnVuelta, Viajero viajero, Lugar destino) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.espacioLibreEnVuelta = espacioLibreEnVuelta;
        this.viajero = viajero;
        this.destino = destino;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item remera) {
        items.add(remera);
    }
}