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
        try{
            String nombre = json.findPath("nombre").textValue();
            Integer stock = json.findPath("stock").intValue();
            Insumo insumo = new Insumo(nombre, stock);
            return ok(Json.toJson(insumo));
        }catch(Exception e){
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            result.put("message", e.getClass().getName());
            return badRequest(result);
        }
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
        try{
            String nombre = json.findPath("nombre").textValue();
            Integer stock = json.findPath("stock").intValue();
            insumo.updateInsumo(nombre, stock);
            return ok(Json.toJson(insumo));
        }catch(Exception e){
            ObjectNode result = Json.newObject();
            result.put("status", "KO");
            result.put("message", e.getMessage());
            return badRequest(result);
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result delete(Long id) {
        Insumo insumo = Insumo.find.byId(id);
        ObjectNode result = Json.newObject();
        if(insumo.id == null || insumo.productos.size() > 0) {
            result.put("status", "KO");
            result.put("message", "el Insumo no se puede borrar");
            return badRequest(result);
        }
        insumo.delete();
        result.put("status", "OK");
        return ok(result);
    }
}