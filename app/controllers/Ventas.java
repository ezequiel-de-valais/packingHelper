package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import models.Producto;
import models.Venta;
import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

/**
 *
 * @author devalais
 */
public class Ventas extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result all() {
        List<Venta> ventas = Venta.find.all();
        return ok(Json.toJson(ventas));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        JsonNode json = request().body().asJson();
        try{
            String comprador = json.findPath("comprador").textValue();
            Long productoId = Long.parseLong(json.findPath("producto").textValue());
            Integer cantidad = Integer.parseInt(json.findPath("cantidad").textValue());
            Producto producto = Producto.find.byId(productoId);
            Venta venta = new Venta(comprador, cantidad, producto);        
            Ebean.save(venta);
            return ok(Json.toJson(venta));
        }catch(Exception e){       
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            result.put("message", e.getMessage());
            return badRequest(result);
        }
        
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result get(Long id) {
        Venta venta = Venta.find.byId(id);
        return ok(Json.toJson(venta));
    }
}