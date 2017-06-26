package models;

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
  public List<Producto> productos;
  
  public static Finder<Long,Insumo> find = new Finder<Long,Insumo>(
    Long.class, Insumo.class
  ); 

}