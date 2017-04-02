package com.ipa.ip2.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by amit on 03/06/16.
 */
public class PropertiesReader {

    private Properties properties = new Properties();
    private InputStream stream = null;

    public PropertiesReader(String filename){
        try {
            InputStream stream = new FileInputStream(System.getProperty("user.dir") + File.separator + filename);
            properties.load(stream);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


}
