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
  
  @Constraints.Required
  public Long itemProductoId;
  
  @Constraints.Required
  public String itemNombre;
  
  @Constraints.Required
  @Constraints.Min(1)
  public Integer cantidad;
  
  public static Finder<Long,Venta> find = new Finder<Long,Venta>(
    Long.class, Venta.class
  );

  public Venta(String aComprador, Integer aCantidad, Producto aProducto) {
    comprador = aComprador;
    cantidad = aCantidad;
    itemProductoId = aProducto.id;
    itemNombre = aProducto.nombre;
  }
  
}