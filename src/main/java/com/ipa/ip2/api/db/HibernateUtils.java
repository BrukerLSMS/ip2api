package com.ipa.ip2.api.db;

import com.ipa.ip2.api.exception.APIException;
import com.ipa.ip2.api.util.PropertiesReader;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by amit on 14/02/17.
 */
public class HibernateUtils {

    private PropertiesReader propertiesReader = new PropertiesReader("jdbc.properties");
    private Properties databaseProps = null;
    private Map<String, String> databaseNameDriversMap = new HashMap<String, String>();
    private static HibernateUtils hibernateUtils = null;
    private SessionFactory sessionFactory = null;
    private String database;
    private String host;
    private String port;
    private String schema;
    private String username;
    private String password;
    private String dialect;
    private String driverClass;
    private StringBuilder connectionString = new StringBuilder();

    private HibernateUtils() throws APIException {
        databaseProps = propertiesReader.getProperties();
        //Supported drivers map
        databaseNameDriversMap.put("MYSQL", "com.mysql.jdbc.Driver");
        databaseNameDriversMap.put("ORACLE", "oracle.jdbc.driver.OracleDriver");

        database = databaseProps.getProperty("DATABASE");
        host = databaseProps.getProperty("DB_HOST");
        port = databaseProps.getProperty("DB_PORT");
        schema = databaseProps.getProperty("DB_SCHEMA");
        username = databaseProps.getProperty("DB_USERNAME");
        password = databaseProps.getProperty("DB_PASSWORD");

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
        driverClass = databaseNameDriversMap.get(database);
        if(driverClass == null || driverClass.equals("")){
            throw new APIException("Not valid and supported database! (IP2API only supports MYSQL and ORACLE)");
        }
        connectionString.setLength(0);
        if(database.equals("MYSQL")) {
            connectionString.append("jdbc:mysql://");
            connectionString.append(host);
            connectionString.append(":");
            connectionString.append(port);
            connectionString.append("/");
            connectionString.append(schema);
            dialect = "org.hibernate.dialect.MySQLDialect";
        } else if (database.equals("ORACLE")) {
            connectionString.append("jdbc:oracle:thin:@");
            connectionString.append(host);
            connectionString.append(":");
            connectionString.append(port);
            connectionString.append(":");
            connectionString.append(schema);
            dialect = "org.hibernate.dialect.Oracle12cDialect";
        }
    }

    public static HibernateUtils getInstance() throws APIException{
        if (hibernateUtils == null){
            hibernateUtils = new HibernateUtils();
        }
        return hibernateUtils;
    }

    public SessionFactory getSessionFactory() throws APIException{
        try {
            if (sessionFactory == null || sessionFactory.isClosed()) {
                Configuration configuration = new Configuration().configure();
                Properties props = configuration.getProperties();
                props.setProperty("hibernate.dialect", dialect);
                props.setProperty("hibernate.connection.driver_class", driverClass);
                props.setProperty("hibernate.connection.url", connectionString.toString());
                props.setProperty("hibernate.connection.username", username);
                props.setProperty("hibernate.connection.password", password);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                configuration.setProperties(props);
                sessionFactory = configuration
                        .buildSessionFactory();
                System.out.println("Session factory created successfully!");
            }
        } catch (Exception e) {
            System.err.println("Failed to create session factory! Check output console");
            e.printStackTrace();
            throw new APIException("Failed to create session factory.");
        }
        return sessionFactory;
    }

    public void closeSessionFactory() throws APIException{
        try {
            if (sessionFactory != null && !sessionFactory.isClosed()) {
                sessionFactory.close();
            }
            System.out.println("SessionFactory closed successfully!");
        } catch (Exception e) {
            System.err.println("Error while closing session factory ! Check output console");
            e.printStackTrace();
            throw new APIException("Error while closing the session factory.");
        }
    }
}
