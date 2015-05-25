package com.alex.javastudy.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jApp {
    private static Logger logger = LogManager.getLogger(Log4jApp.class);

    public void launch() {
        logger.trace("This is a trace message.");
        logger.debug("This is a debug message.");
        logger.warn("This is a warn message.");
        logger.error("This is a error message.");
        logger.fatal("This is a fatal message.");
    }
}
