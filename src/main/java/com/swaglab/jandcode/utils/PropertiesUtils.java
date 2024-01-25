package com.swaglab.jandcode.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtils {

    private static Properties properties;

    /**
     * This method read only .properties files and save this information on static variable properties
     * @param namePathFile path that we can find our file .properties
     */
    private static void configureProperties(String namePathFile){
        try{
            properties = new Properties();
            properties.load(new BufferedReader(new FileReader(namePathFile)));

        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "Error! the file cannot read our system checking the path "
                            + namePathFile + ".\n" + e.getMessage());
        }
    }

    /**
     * This method saves all changes that we made on files .properties
     * @param namePathFile path that we can find our file .properties
     */
    private static void saveChangesProperties(String namePathFile){
        try{
            properties.store(new BufferedWriter(new FileWriter(namePathFile)),
                    "Changes information on file properties");
        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "Error! the file cannot read our system checking the path "
                            + namePathFile + ".\n" + e.getMessage());
        }
    }

    /**
     * This method return a specific value of the file .properties
     * @param namePathFile path that we can find our file .properties
     * @param key name that our properties read: example name=jandcode; 'name' is the key.
     * @return value of properties file
     */
    public static String getValueProperties(String namePathFile, String key){
        try{
            configureProperties(namePathFile);
            return properties.getProperty(key);

        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "Error! System cannot get value on properties: key - " + key + " to file path - "
                            + namePathFile + ".\n" + e.getMessage());
            return "Cannot get value on properties: ".concat(key);
        }
    }

    /**
     * This method modify a value as we select key on file .properties
     * @param namePathFile path that we can find our file .properties
     * @param key name that our properties read: example name=jandcode; 'name' is the key.
     * @param value is an information that customer will send.
     */
    public static void setValueProperties(String namePathFile, String key, String value){
        try{
            configureProperties(namePathFile);
            properties.setProperty(key,value);
            saveChangesProperties(namePathFile);
        }catch (Exception e){
            Logger.getAnonymousLogger().log(Level.WARNING,
                    "Error! System cannot write value on properties: " + key +": "+ value +" to file path - "
                            + namePathFile + ".\n" + e.getMessage());
        }
    }
}
