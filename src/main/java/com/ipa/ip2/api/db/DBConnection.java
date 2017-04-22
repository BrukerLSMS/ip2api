package com.ipa.ip2.api.db;

import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by amit on 13/02/17.
 */
public class DBConnection {
    private PropertiesReader propertiesReader = new PropertiesReader("jdbc.properties");
    private Properties databaseProps = null;
    private Map<String, String> databaseNameDriversMap = new HashMap<String, String>();
    private static DBConnection dbConnection = null;
    private Connection connection = null;
    private String database;
    private String url;
    private String username;
    private String password;

    private DBConnection() throws APIException{
        databaseProps = propertiesReader.getProperties();
        //Supported drivers map
        databaseNameDriversMap.put("MYSQL", "com.mysql.jdbc.Driver");
        databaseNameDriversMap.put("ORACLE", "oracle.jdbc.driver.OracleDriver");

        database = databaseProps.getProperty("DATABASE");
        url = databaseProps.getProperty("URL");
        username = databaseProps.getProperty("DB_USERNAME");
        password = databaseProps.getProperty("DB_USERNAME");

        if (database == null || database.equals("")) {
            throw new APIException("Database not found. Please provide DATABASE in the jdbc.properties");
        }
        if (url == null || url.equals("")) {
            throw new APIException("URL not found. Please provide URL in the jdbc.properties");
        }
        if (username == null || username.equals("")) {
            throw new APIException("Username not found. Please provide DB_USERNAME in the jdbc.properties");
        }
        if (password == null || password.equals("")) {
            throw new APIException("Password not found. Please provide DB_PASSWORD in the jdbc.properties");
        }
        String driver = databaseNameDriversMap.get(database);
        if(driver == null || driver.equals("")){
            throw new APIException("Not valid and supported database! (IP2API only supports MYSQL and ORACLE)");
        }
    }

    public static DBConnection getInstance() throws APIException{
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public void openConnection() throws APIException{
        try {
            if (connection.isClosed()) {
                connection = DriverManager
                        .getConnection(url, username, password);
                System.out.println("Connection open successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw new APIException("Not able to open the connection with database.");
        }
    }

    public void closeConnection() throws APIException{
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            System.out.println("Connection closed successfully!");
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            throw new APIException("Error while closing the database connection.");
        }
    }
}
