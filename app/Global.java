import com.avaje.ebean.Ebean;
import play.*;
import play.libs.*;
import java.util.*;
import models.*;

public class Global extends GlobalSettings {

    public void onStart(Application app) {

        InitialData.insert(app);
    }

    static class InitialData {
        public static void insert(Application app) {
            Map<String, List<Object>> all
                    = (Map<String, List<Object>>) Yaml.
                            load("initial-data.yml");
            if (Ebean.find(Insumo.class).findRowCount() == 0) {
                Ebean.save(all.get("insumos"));
            }

            if (Ebean.find(Producto.class).findRowCount() == 0) {
                Ebean.save(all.get("productos"));
            }
        }

        private static List<Insumo> parseInsumos(List<Integer> insumosNode) {
            List<Insumo> insumos= new ArrayList();
            for (int i = 0; i < insumosNode.size(); i++) {
                Long insumoId = insumosNode.get(i).longValue();
                Insumo insumo = Insumo.find.byId(insumoId);
                insumos.add(insumo);
            }
            return insumos;
        }
    }
}