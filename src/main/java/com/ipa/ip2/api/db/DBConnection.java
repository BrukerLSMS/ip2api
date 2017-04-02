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
    private String host;
    private String port;
    private String schema;
    private String username;
    private String password;
    private StringBuilder connectionString = new StringBuilder();

    private DBConnection() throws APIException{
        databaseProps = propertiesReader.getProperties();
        //Supported drivers map
        databaseNameDriversMap.put("MYSQL", "com.mysql.jdbc.Driver");
        databaseNameDriversMap.put("ORACLE", "oracle.jdbc.driver.OracleDriver");

        database = databaseProps.getProperty("DATABASE");
        host = databaseProps.getProperty("DB_HOST");
        port = databaseProps.getProperty("DB_PORT");
        schema = databaseProps.getProperty("DB_SCHEMA");
        username = databaseProps.getProperty("DB_USERNAME");
        password = databaseProps.getProperty("DB_USERNAME");

        if (database == null || database.equals("")) {
            throw new APIException("Database not found. Please provide DATABASE in the jdbc.properties");
        }
        if (host == null || host.equals("")) {
            throw new APIException("Host not found. Please provide DB_HOST in the jdbc.properties");
        }
        if (port == null || port.equals("")) {
            throw new APIException("Port not found. Please provide DB_PORT in the jdbc.properties");
        }
        if (schema == null || schema.equals("")) {
            throw new APIException("Schema not found. Please provide DB_SCHEMA in the jdbc.properties");
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
        connectionString.setLength(0);
        if(driver.equals("MYSQL")) {
            connectionString.append("jdbc:mysql://");
            connectionString.append(host);
            connectionString.append(":");
            connectionString.append(port);
            connectionString.append("/");
            connectionString.append(schema);
        } else if (driver.equals("ORACLE")) {
            connectionString.append("jdbc:oracle:thin:@");
            connectionString.append(host);
            connectionString.append(":");
            connectionString.append(port);
            connectionString.append(":");
            connectionString.append(schema);
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
                        .getConnection(connectionString.toString(), username, password);
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
