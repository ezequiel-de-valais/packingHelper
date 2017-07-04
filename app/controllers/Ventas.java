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
        String comprador = json.findPath("comprador").textValue();
        Long productoId = json.findPath("producto").longValue();
        Integer cantidad = json.findPath("cantidad").intValue();
        Producto producto = Producto.find.byId(productoId);
        
        if(comprador == null || producto == null || cantidad < 1) {
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            return badRequest(result);
        }
        
        Venta venta = new Venta(comprador, cantidad, producto);
        Ebean.save(venta);
        return ok(Json.toJson(venta));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result get(Long id) {
        Venta venta = Venta.find.byId(id);
        return ok(Json.toJson(venta));
    }
}