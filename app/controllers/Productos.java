package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import models.Insumo;
import models.Producto;
import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

/**
 *
 * @author devalais
 */
public class Productos extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result all() {
        List<Producto> productos = Producto.find.all();
        return ok(Json.toJson(productos));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        JsonNode json = request().body().asJson();
        String nombre = json.findPath("nombre").textValue();
        String descripcion = json.findPath("descripcion").textValue();
        ArrayNode insumosNode = (ArrayNode)json.findPath("insumos");
        ObjectNode result = Json.newObject();
        Producto producto = new Producto();
        if(nombre == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
        }
        producto.nombre = nombre;
        producto.descripcion = descripcion;
        
        for (int i = 0; i < insumosNode.size(); i++) {
          Long insumoId = insumosNode.get(i).longValue();
          Insumo insumo = Insumo.find.byId(insumoId);
          if(insumo.id == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
          }
          producto.insumos.add(insumo);
        }

        Ebean.save(producto);
        return ok(Json.toJson(producto));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result get(Long id) {
        Producto producto = Producto.find.byId(id);
        return ok(Json.toJson(producto));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result edit(Long id) {
        Producto producto = Producto.find.byId(id);
        JsonNode json = request().body().asJson();
        String nombre = json.findPath("nombre").textValue();
        String descripcion = json.findPath("descripcion").textValue();
        ArrayNode insumosNode = (ArrayNode)json.findPath("insumos");
        ObjectNode result = Json.newObject();
        if(nombre == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
        }
        producto.nombre = nombre;
        producto.descripcion = descripcion;
        
        for (int i = 0; i < insumosNode.size(); i++) {
          Long insumoId = insumosNode.get(i).longValue();
          Insumo insumo = Insumo.find.byId(insumoId*100);
          if(insumo.id == null) {
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
          }
          producto.insumos.add(insumo);
        }

        Ebean.save(producto);
        return ok(Json.toJson(producto));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result delete(Long id) {
        Producto producto = Producto.find.byId(id);
        ObjectNode result = Json.newObject();
        if(producto.id == null) {
            result.put("status", "KO");
            return badRequest(result);
        }
        producto.delete();
        result.put("status", "OK");
        return ok(result);
    }
}