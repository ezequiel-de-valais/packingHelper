/*
 * Copyright 2016, Debtech S.R.L.
 *
 * http://www.debtech.com.ar
 * http://www.debmedia.com
 */
package databaseConfigs;

import com.avaje.ebean.config.dbplatform.DatabasePlatform;
import java.util.HashMap;

/**
 * this is the parent for all database configs. it declares the attribute settings (url, driver, user,
 * password) and the database platforms
 *
 * @author ppascua
 * @since v1.1.0
 */
public abstract class DatabaseConfig {

    protected HashMap<String, String> settings;
    protected DatabasePlatform dbPlatform;

    public DatabaseConfig() {
	this.settings = new HashMap<>();
	this.settings.put("ebean.test", "models.*");
	this.settings.put("evolutionplugin", "disabled");

	this.settings.put("db.test.driver", getDriver());
	this.settings.put("db.test.url", getUrl());
	this.settings.put("db.test.user", getUser());
	this.settings.put("db.test.password", getPassword());
    }

    protected abstract String getDriver();

    protected abstract String getUrl();

    protected abstract String getUser();

    protected abstract String getPassword();

    public HashMap<String, String> getSettings() {
        return settings;
    }

    public DatabasePlatform getDbPlatform() {
        return dbPlatform;
    }
}
