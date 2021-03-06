package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
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
        ObjectNode result = Json.newObject(); 
        try{
            String nombre = json.findPath("nombre").textValue();
            String descripcion = json.findPath("descripcion").textValue();
            ArrayNode insumosNode = (ArrayNode)json.findPath("insumos");            
            List<Insumo> insumos = parseInsumos(insumosNode);
            Producto producto = new Producto(nombre, descripcion, insumos);
            return ok(Json.toJson(producto));
        }catch(Exception e){
            result.put("status", "KO");
            result.put("message", e.getMessage());
            return badRequest(result);
        }
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
        ObjectNode result = Json.newObject(); 
        try{
            String nombre = json.findPath("nombre").textValue();
            String descripcion = json.findPath("descripcion").textValue();
            ArrayNode insumosNode = (ArrayNode)json.findPath("insumos");            
            List<Insumo> insumos = parseInsumos(insumosNode);
            producto.updateProduct(nombre, descripcion, insumos);
            return ok(Json.toJson(producto));
        }catch(Exception e){
            result.put("status", "KO");
            result.put("message", e.getMessage());
            return badRequest(result);
        }
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

    private static List<Insumo> parseInsumos(ArrayNode insumosNode) {
        List<Insumo> insumos= new ArrayList();
        for (int i = 0; i < insumosNode.size(); i++) {
            Long insumoId = insumosNode.get(i).longValue();
            Insumo insumo = Insumo.find.byId(insumoId);
            insumos.add(insumo);
        }
        return insumos;
    }
}