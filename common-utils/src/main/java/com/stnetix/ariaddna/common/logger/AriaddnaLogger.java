package com.stnetix.ariaddna.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is wrapper of standard SLF4J framework.
 * Typical usage pattern:
 * public class A{
 * private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(A.class);
 * //or private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(A.class.getName());
 * void doSomething(Object arg){
 * LOGGER.trace(Begin of method {{doSomething}});;
 * LOGGER.info("Execution method {{doSomething}} with argument {}", arg);
 * }
 * }
 *
 * @author Kotov Alexandr
 */
public class AriaddnaLogger {

    public static AriaddnaLogger getLogger(String className) {
        if (className == null) {
            throw new IllegalArgumentException("Variable className cannot be null.");
        }
        return new AriaddnaLogger(className);
    }

    public static AriaddnaLogger getLogger(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Variable clazz cannot be null.");
        }
        return new AriaddnaLogger(clazz);
    }

    private Logger logger;

    private AriaddnaLogger(String className) {
        logger = LoggerFactory.getLogger(className);
    }

    private AriaddnaLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public boolean isTraceEnabled(){
        return logger.isTraceEnabled();
    }

    public boolean isTraceEnabled(Marker marker){
        return logger.isTraceEnabled(marker);
    }

    public void trace(String message) {
        logger.trace(message);
    }

    public void trace(Marker marker, String message) {
        logger.trace(marker, message);
    }

    public void trace(String formatMessage, Object arg) {
        logger.trace(formatMessage, arg);
    }

    public void trace(Marker marker, String formatMessage, Object arg) {
        logger.trace(marker, formatMessage, arg);
    }

    public void trace(String formatMessage, Object arg1, Object arg2) {
        logger.trace(formatMessage, arg1, arg2);
    }

    public void trace(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.trace(marker, formatMessage, arg1, arg2);
    }

    public void trace(String formatMessage, Object... arguments) {
        logger.trace(formatMessage, arguments);
    }

    public void trace(Marker marker, String formatMessage, Object... arguments) {
        logger.trace(marker, formatMessage, arguments);
    }

    public void trace(String message, Throwable throwable) {
        logger.trace(message, throwable);
    }

    public void trace(Marker marker, String message, Throwable throwable) {
        logger.trace(marker, message, throwable);
    }

    public boolean isInfoEnabled(){
        return logger.isInfoEnabled();
    }

    public boolean isInfoEnabled(Marker marker){
        return logger.isInfoEnabled(marker);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void info(Marker marker, String message) {
        logger.info(marker, message);
    }

    public void info(String formatMessage, Object arg) {
        logger.info(formatMessage, arg);
    }

    public void info(Marker marker, String formatMessage, Object arg) {
        logger.info(marker, formatMessage, arg);
    }

    public void info(String formatMessage, Object arg1, Object arg2) {
        logger.info(formatMessage, arg1, arg2);
    }

    public void info(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.info(marker, formatMessage, arg1, arg2);
    }

    public void info(String formatMessage, Object... arguments) {
        logger.info(formatMessage, arguments);
    }

    public void info(Marker marker, String formatMessage, Object... arguments) {
        logger.info(marker, formatMessage, arguments);
    }

    public void info(String message, Throwable throwable) {
        logger.info(message, throwable);
    }

    public void info(Marker marker, String message, Throwable throwable) {
        logger.info(marker, message, throwable);
    }
}
