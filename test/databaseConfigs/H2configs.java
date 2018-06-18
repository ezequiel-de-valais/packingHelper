/*
 * Copyright 2016, Debtech S.R.L.
 *
 * http://www.debtech.com.ar
 * http://www.debmedia.com
 */
package databaseConfigs;

import com.avaje.ebean.config.dbplatform.H2Platform;

/**
 * DB settings for H2 database
 *
 * @author ppascua
 * @since v1.1.0
 */
public class H2configs extends DatabaseConfig {

    public H2configs() {
        super();
        this.dbPlatform = new H2Platform();
    }

    @Override
    protected String getDriver() {
        return "org.h2.Driver";
    }

    @Override
    protected String getUrl() {
        return "jdbc:h2:mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1";
//        return "jdbc:h2:tcp://localhost:9092/mem:play;MODE=MYSQL;DB_CLOSE_DELAY=-1";

    }

    @Override
    protected String getUser() {
        return "sa";
    }

    @Override
    protected String getPassword() {
        return "";
    }
}
