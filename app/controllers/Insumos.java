package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import models.Insumo;
import play.*;
import play.mvc.*;
import play.libs.Json;
import views.html.*;

/**
 *
 * @author devalais
 */
public class Insumos extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result all() {
        List<Insumo> insumos = Insumo.find.all();
        return ok(Json.toJson(insumos));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result create() {
        JsonNode json = request().body().asJson();
        String nombre = json.findPath("nombre").textValue();
        Integer stock = Integer.parseInt(json.findPath("stock").textValue());
        if(nombre == null) {
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
        }
        Insumo insumo = new Insumo();
        insumo.nombre = nombre;
        insumo.stock = stock;
        Ebean.save(insumo);
        return ok(Json.toJson(insumo));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result get(Long id) {
        Insumo insumo = Insumo.find.byId(id);
        return ok(Json.toJson(insumo));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result edit(Long id) {
        Insumo insumo = Insumo.find.byId(id);
        JsonNode json = request().body().asJson();
        String nombre = json.findPath("nombre").textValue();
        Integer stock = Integer.parseInt(json.findPath("stock").textValue());
        if(nombre == null) {
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            result.put("message", "Missing parameter");
            return badRequest(result);
        }
        insumo.nombre = nombre;
        insumo.stock = stock;
        Ebean.save(insumo);
        return ok(Json.toJson(insumo));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result delete(Long id) {
        Insumo insumo = Insumo.find.byId(id);
        ObjectNode result = Json.newObject();
        if(insumo.id == null || insumo.productos.size() > 0) {
            result.put("status", "KO");
            return badRequest(result);
        }
        insumo.delete();
        result.put("status", "OK");
        return ok(result);
    }
}