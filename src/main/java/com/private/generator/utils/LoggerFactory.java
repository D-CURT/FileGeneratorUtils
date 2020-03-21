package com.gmail.estrynvladislav.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;

public final class LoggerFactory {

    private static final String LOGGER_CONFIG =
            String.join(File.separator,
                    System.getProperty("user.dir"), "src", "main", "resources", "log4j.properties");

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> target) {
        PropertyConfigurator.configure(LOGGER_CONFIG);
        return Logger.getLogger(target);
    }

}
