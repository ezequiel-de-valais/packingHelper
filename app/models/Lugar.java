package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Lugar extends Model {

    @Id
    private Long id;

    @Constraints.Required
    private String nombre;

    @Constraints.Required
    private Integer limiteEquipajeDeMano;

    @Constraints.Required
    private Integer limiteEquipajeDeBodega;

    public static Finder<Long, Lugar> FIND = new Finder<Long, Lugar>(
            Long.class, Lugar.class
    );

    public static Lugar findById(Long id) {
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

    public Integer getLimiteEquipajeDeMano() {
        return limiteEquipajeDeMano;
    }

    public void setLimiteEquipajeDeMano(Integer limiteEquipajeDeMano) {
        this.limiteEquipajeDeMano = limiteEquipajeDeMano;
    }

    public Integer getLimiteEquipajeDeBodega() {
        return limiteEquipajeDeBodega;
    }

    public void setLimiteEquipajeDeBodega(Integer limiteEquipajeDeBodega) {
        this.limiteEquipajeDeBodega = limiteEquipajeDeBodega;
    }

    public Lugar(String nombre, Integer limiteEquipajeDeMano, Integer limiteEquipajeDeBodega) {
        this.nombre = nombre;
        this.limiteEquipajeDeMano = limiteEquipajeDeMano;
        this.limiteEquipajeDeBodega = limiteEquipajeDeBodega;
    }
}