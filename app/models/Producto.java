package models;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.REMOVE;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
public class Producto extends Model {

    @Id
    @Constraints.Min(10)
    public Long id;

    @Constraints.Required
    public String nombre;

    @Constraints.Required
    @Column(length=500) 
    @Constraints.Max(500)
    public String descripcion;

    @ManyToMany(cascade=REMOVE)
    @JsonManagedReference
    public List<Insumo> insumos;

    public static Finder<Long,Producto> find = new Finder<Long,Producto>(
        Long.class, Producto.class
    );

    public Integer calculateStock(){
        int stock = insumos.get(0).stock;
        for (Insumo insumo : insumos) {
            if(insumo.stock < stock){
                stock = insumo.stock;
            }
        }
        return stock;
    }

    public void reduceStock(Integer aCantidad) {
        for (Insumo insumo : insumos) {
            insumo.reduceStock(aCantidad);
        }
    }
}