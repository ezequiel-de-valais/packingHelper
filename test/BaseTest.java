import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.typesafe.config.ConfigFactory;
import databaseConfigs.DatabaseConfig;
import databaseConfigs.H2configs;
import databaseConfigs.mySQLconfigs;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import play.mvc.Http;
import play.test.FakeApplication;
import play.test.Helpers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static play.test.Helpers.fakeGlobal;

/**
 *
 * @author Diego Mole
 * @author Sebastián Schiavinato
 */
public class BaseTest {

    public static FakeApplication app;
    public static DdlGenerator ddl;
    protected static final DatabaseConfig DBNAME = DatabaseConfigsFactory();

    @BeforeClass
    public static void initDB() throws Exception {
        app = Helpers.fakeApplication(DBNAME.getSettings(), fakeGlobal());
        Helpers.start(app);
        ddlConfiguration();
        cleanDb();
        EbeanServerShutDown();
        Helpers.stop(app);

    }

    @Before
    public void startApp() throws Exception {

        app = Helpers.fakeApplication(DBNAME.getSettings());
        Helpers.start(app);
        ddlConfiguration();
    }

    @After
    public void stopApp() throws Exception {
        cleanDb();
        EbeanServerShutDown();
        Helpers.stop(app);

    }

    public static void EbeanServerShutDown() {
        Set<String> keys = play.Play.application().configuration().getConfig("db").subKeys();
        for (String key : keys) {
            EbeanServer server = Ebean.getServer(key);
            server.shutdown(true, true);
        }

    }

    public static void ddlConfiguration() {
        String serverName = "test";
        EbeanServer server = Ebean.getServer(serverName);
        ServerConfig config = new ServerConfig();
        config.setDefaultServer(true);
        ddl = new DdlGenerator();
        ddl.setup((SpiEbeanServer) server, DBNAME.getDbPlatform(), config);
    }

    public static void cleanDb() {

        try {
            // Drop
            ddl.runScript(false, ddl.generateDropDdl());

        } catch (Exception e) {
            // Esta excepción se tira la primera vez que se corre porque no
            // se encuentran las tablas.
        }

        // Create
        ddl.runScript(false, ddl.generateCreateDdl());
    }

    /**
     * Load from the application.conf the database to be used
     *
     * @return an instance of DatabaseConfig
     */
    private static DatabaseConfig DatabaseConfigsFactory() {
        String dbName = ConfigFactory.load().getString("testDBname").toUpperCase();
        switch (dbName) {
            case "H2":
                return new H2configs();
            case "MYSQL":
                return new mySQLconfigs();
            default:
                return null;

        }
    }

    /**
     * @fn logStep(int stepNumber, String stepDescription)
     * @param stepNumber
     * @param stepDescription
     */
    protected static void logStep(int stepNumber, String stepDescription) {
        //System.out.println("Step " + stepNumber + " - " + stepDescription);
    }

/*
    protected static Http.Cookie loginByAPI(String email, String password,
            uBranch branch, qWorker worker) {
        Http.Cookie playSession;
        Result result;
        uUser user = uUser.findUserByEmail(email);

        assertThat(user).isNotNull();
        assertThat(user.getBranches()).isNotNull();
        //Autentificacion
        {
            ObjectNode authNode = JsonNodeFactory.instance.objectNode();
            authNode.put("email", email);
            authNode.put("password", password);
            if (branch != null) {
                authNode.put("branch.id", branch.getId());
            }
            if (worker != null) {
                authNode.put("worker.id", worker.getId());
            }
            result = route(fakeRequest(POST, "/api/authenticate").withJsonBody(authNode));
            if (status(result) != OK) {
                System.out.println("Status: " + status(result) + " || Content: " + new String(contentAsBytes(result)));
            }
            //Evaluo que la respuesta sea un redirect y la ruta de redireccion
            assertThat(status(result)).isEqualTo(OK);
            //Obtengo la cookie
            playSession = Helpers.cookie("PLAY_SESSION", result);
            assertThat(playSession).isNotNull();
        }
        return playSession;
    }*/

    /*protected static Http.Cookie loginAPIUser(uBranch branch) {
        Http.Cookie playSession;
        Result result;
        //Autentificacion
        {
            ObjectNode authNode = JsonNodeFactory.instance.objectNode();
            authNode.put("email", "api." + companyName + "@" + companyName + ".com");
            authNode.put("password", companyName);
            authNode.put("branch.id", branch.getId());
            result = route(fakeRequest(POST, controllers.debq.api.routes.qAuthentication.authenticate().url()).withJsonBody(authNode));
            if (status(result) != OK) {
                System.out.println("Status: " + status(result) + " || Content: " + new String(contentAsBytes(result)));
            }
            //Evaluo que la respuesta sea un redirect y la ruta de redireccion
            assertThat(status(result)).isEqualTo(OK);
            //Obtengo la cookie
            playSession = Helpers.cookie("PLAY_SESSION", result);
            assertThat(playSession).isNotNull();
        }
        return playSession;
    }*/

    public static Map<String, String> parseCookie(Http.Cookie cookie) {
        String value = cookie.value();
        Map<String, String> map = new LinkedHashMap<String, String>();
        value = value.substring(value.indexOf("-") + 1, value.length());
        for (String keyValue : value.split(" *& *")) {
            String[] pairs = keyValue.split(" *= *", 2);
            map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
        }
        return map;
    }
}
