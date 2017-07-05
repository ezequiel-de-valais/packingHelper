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

    public Producto(String aNombre, String aDescripcion, List<Insumo> aInsumos) throws Exception {
        validateData(aNombre, aDescripcion, aInsumos);
        nombre = aNombre;
        descripcion = aDescripcion;
        insumos = aInsumos;
        Ebean.save(this);
    }

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

    private void validateData(String aNombre, String aDescripcion, List<Insumo> aInsumos) throws Exception {
        if(aNombre == null){
            throw new Exception("nombre incorrecto");
        }
        if(aDescripcion == null || aDescripcion.length() > 500){
            throw new Exception("descripcion incorrecto");
        } 
        if(aInsumos.isEmpty()){
            throw new Exception("selecciones 1 insumo incorrecta");
        }
    }

    public void updateProduct(String aNombre, String aDescripcion, List<Insumo> aInsumos) throws Exception {
        validateData(aNombre, aDescripcion, aInsumos);
        nombre = aNombre;
        descripcion = aDescripcion;
        insumos = aInsumos;
        Ebean.save(this);
    }
}