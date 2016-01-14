package com.yang.spinach.common.utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigUtil {

    private static PropertiesConfiguration config = null;

    static {
        try {
            config = new PropertiesConfiguration("resources.properties");
        } catch (Exception e) {
        }
    }

    public static String get(String key) {
        return config.getString(key);
    }

    public static int getInt(String key) {
        return config.getInt(key);
    }

    public static long getLong(String key) {
        return config.getLong(key);
    }

}
