package models;

import java.util.List;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;

@Entity 
public class Venta extends Model {

  @Id
  @Constraints.Min(10)
  public Long id;
  
  @Constraints.Required
  public String comprador;
  
  @ManyToOne(optional=false)
  public Producto producto;
  
  @Constraints.Required
  @Constraints.Min(1)
  public Integer cantidad;
  
  public static Finder<Long,Venta> find = new Finder<Long,Venta>(
    Long.class, Venta.class
  ); 
}