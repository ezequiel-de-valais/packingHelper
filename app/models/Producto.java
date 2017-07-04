package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.*;

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
  
  @ManyToMany
  @JsonManagedReference
  public List<Insumo> insumos;

  @OneToMany(mappedBy="producto")
  List<Venta> ventas;
  
  public static Finder<Long,Producto> find = new Finder<Long,Producto>(
    Long.class, Producto.class
  ); 
}