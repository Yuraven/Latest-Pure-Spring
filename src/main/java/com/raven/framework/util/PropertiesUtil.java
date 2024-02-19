package com.raven.framework.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author Raven
 */
@UtilityClass
public class PropertiesUtil {
    private final String DEFAULT_PROPERTY_STR = "/common.properties";

    public String get(String key, String... str) {
        String propertyStr = DEFAULT_PROPERTY_STR;
        if (str.length != 0) {
            propertyStr = str[0];
        }
        Properties prop = new Properties();
        try {
            prop.load(PropertiesUtil.class.getResourceAsStream(propertyStr));
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
