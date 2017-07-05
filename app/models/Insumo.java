package models;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
public class Insumo extends Model {

  @Id
  @Constraints.Min(10)
  public Long id;
  
  @Constraints.Required
  public String nombre;
  
  @Constraints.Required
  @Constraints.Min(0)
  public Integer stock;
  
  @ManyToMany(mappedBy = "insumos")
  @JsonBackReference
  public List<Producto> productos;
  
  public static Finder<Long,Insumo> find = new Finder<Long,Insumo>(
    Long.class, Insumo.class
  ); 

    public Insumo(String aNombre, Integer aStock) throws Exception {
        validateData(aNombre, aStock);
        nombre = aNombre;
        stock = aStock;
        Ebean.save(this);
    }

    void reduceStock(Integer aCantidad) {
        stock -= aCantidad;
        Ebean.save(this);
    }

    private void validateData(String aNombre, Integer aStock) throws Exception {
        if(aNombre == null){
            throw new Exception("nombre incorrecto");
        }
        if(aStock == null || aStock < 0){
            throw new Exception("stock incorrecto");
        }
    }
    
    public void updateInsumo(String aNombre, Integer aStock) throws Exception {
        validateData(aNombre, aStock);
        nombre = aNombre;
        stock = aStock;
        Ebean.save(this);
    }

}