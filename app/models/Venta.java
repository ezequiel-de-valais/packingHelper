package models;

import com.avaje.ebean.Ebean;
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

  public Venta(String aComprador, Integer aCantidad, Producto aProducto) throws Exception {
    if(aComprador == null){
        throw new Exception("comprador incorrecto");
    }
    if(aProducto == null){
        throw new Exception("producto incorrecto");
    } 
    if(aCantidad < 1) {
        throw new Exception("cantidad incorrecta");
    }
    if(aProducto.calculateStock() < aCantidad) {
        throw new Exception("elstock del producto es insuficiente");
    }
    aProducto.reduceStock(aCantidad);
    comprador = aComprador;
    cantidad = aCantidad;
    itemProductoId = aProducto.id;
    itemNombre = aProducto.nombre;
    Ebean.save(this);
  }
  
}