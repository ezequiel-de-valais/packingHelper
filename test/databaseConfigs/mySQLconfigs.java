/*
 * Copyright 2016, Debtech S.R.L.
 *
 * http://www.debtech.com.ar
 * http://www.debmedia.com
 */
package databaseConfigs;

import com.avaje.ebean.config.dbplatform.MySqlPlatform;

/**
 * DB settings for mysql database
 *
 * @author ppascua
 * @since v1.1.0
 */
public class mySQLconfigs extends DatabaseConfig {

    public mySQLconfigs() {
	super();
	this.dbPlatform = new MySqlPlatform();
    }

    @Override
    protected String getDriver() {
	return "com.mysql.jdbc.Driver";
    }

    @Override
    protected String getUrl() {
	return "jdbc:mysql://localhost/debqtest?#characterEncoding=UTF-8";
    }

    @Override
    protected String getUser() {
	return "root";
    }

    @Override
    protected String getPassword() {
	return "qmsroot";
    }
}
