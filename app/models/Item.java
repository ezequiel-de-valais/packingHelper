package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item extends Model {

    @Id
    private Long id;

    @Constraints.Required
    @ManyToOne
    private Elemento elemento;

    @Constraints.Required
    private Integer cantidad;

    @Constraints.Required
    private Integer pesoIndividual;

    @Constraints.Required
    private boolean deMano;

    @Constraints.Required
    private boolean consumible;

    @ManyToOne
    private Viaje viaje;

    public static Finder<Long, Item> FIND = new Finder<Long, Item>(
            Long.class, Item.class
    );

    public Item(Elemento elemento, int cantidad) {
        this.elemento = elemento;
        this.cantidad = cantidad;
        this.pesoIndividual = elemento.getPesoIndividual();
        this.deMano = elemento.isDeMano();
        this.consumible = elemento.isConsumible();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public String getNombre() {
        return elemento.getNombre();
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public static Item findById(Long id) {
        return FIND.byId(id);
    }
}