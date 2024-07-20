package com.evy.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging using Log4j2.
 */
public final class LoggerUtils {

    /**
     * Private constructor to prevent instantiation of LoggerUtils.
     * Utility classes should not have public constructors.
     */
    private LoggerUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Retrieves a logger instance for the specified class.
     */
    private static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Logs an info message.
     */
    public static void info(Class<?> clazz, String message) {
        getLogger(clazz).info(message);
    }

    /**
     * Logs a warning message with an associated exception.
     */
    public static void warn(Class<?> clazz, String message, Throwable e) {
        getLogger(clazz).warn(message, e);
    }

    /**
     * Logs a debug message with an associated exception.
     */
    public static void debug(Class<?> clazz, String message, Throwable e) {
        getLogger(clazz).debug(message, e);
    }

    /**
     * Logs an error message with an associated exception.
     */
    public static void error(Class<?> clazz, String message, Throwable e) {
        getLogger(clazz).error(message, e);
    }
}
