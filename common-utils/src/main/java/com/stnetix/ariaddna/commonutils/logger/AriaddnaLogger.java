/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.commonutils.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is wrapper of standard SLF4J framework.
 * Typical usage pattern:
 * public class A{
 *       private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(A.class);
 * //or  private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(A.class.getName());
 *       void doSomething(Object arg){
 *          LOGGER.trace(Begin of method {{doSomething}});;
 *          LOGGER.info("Execution method {{doSomething}} with argument {}", arg);
 *       }
 * }
 *
 * @author Kotov Alexandr
 */
public class AriaddnaLogger {
    /**
     * This method return instance of {@link AriaddnaLogger} class.
     *
     * @param className is name of logger.
     * @return new instance of AriaddnaLogger.
     */
    public static AriaddnaLogger getLogger(String className) {
        if (className == null) {
            throw new IllegalArgumentException("Variable className cannot be null.");
        }
        return new AriaddnaLogger(className);
    }

    /**
     * This method return instance of {@link AriaddnaLogger} class.
     *
     * @param clazz is instance of {@link Class} class to get logger name.
     * @return new instance of AriaddnaLogger.
     */
    public static AriaddnaLogger getLogger(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Variable clazz cannot be null.");
        }
        return new AriaddnaLogger(clazz);
    }

    /**
     * Wrapped instance of SLF4J
     */
    private Logger logger;

    private AriaddnaLogger(String className) {
        logger = LoggerFactory.getLogger(className);
    }

    private AriaddnaLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * Is the logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for the TRACE level,
     * false otherwise.
     */
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Similar to {@link #isTraceEnabled()} method except that the
     * marker data is also taken into account.
     *
     * @param marker The marker data to take into consideration
     * @return True if this Logger is enabled for the TRACE level,
     * false otherwise.
     */
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    /**
     * Log a message at the TRACE level.
     *
     * @param message the message string to be logged
     */
    public void trace(String message) {
        logger.trace(message);
    }

    /**
     * Log a message with the specific Marker at the TRACE level.
     *
     * @param marker  the marker data specific to this log statement
     * @param message the message string to be logged
     */
    public void trace(Marker marker, String message) {
        logger.trace(marker, message);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and argument.
     *
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void trace(String formatMessage, Object arg) {
        logger.trace(formatMessage, arg);
    }

    /**
     * This method is similar to {@link #trace(String, Object)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void trace(Marker marker, String formatMessage, Object arg) {
        logger.trace(marker, formatMessage, arg);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and arguments.
     *
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void trace(String formatMessage, Object arg1, Object arg2) {
        logger.trace(formatMessage, arg1, arg2);
    }

    /**
     * This method is similar to {@link #trace(String, Object, Object)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void trace(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.trace(marker, formatMessage, arg1, arg2);
    }

    /**
     * Log a message at the TRACE level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the TRACE level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for TRACE. The variants taking {@link #trace(String, Object) one} and
     * {@link #trace(String, Object, Object) two} arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void trace(String formatMessage, Object... arguments) {
        logger.trace(formatMessage, arguments);
    }

    /**
     * This method is similar to {@link #trace(String, Object...)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arguments     an array of arguments
     */
    public void trace(Marker marker, String formatMessage, Object... arguments) {
        logger.trace(marker, formatMessage, arguments);
    }

    /**
     * Log an exception (throwable) at the TRACE level with an
     * accompanying message.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void trace(String message, Throwable throwable) {
        logger.trace(message, throwable);
    }

    /**
     * This method is similar to {@link #trace(String, Throwable)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker    the marker data specific to this log statement
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void trace(Marker marker, String message, Throwable throwable) {
        logger.trace(marker, message, throwable);
    }

    /**
     * Is the logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level,
     * false otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Similar to {@link #isInfoEnabled()} method except that the marker
     * data is also taken into consideration.
     *
     * @param marker The marker data to take into consideration
     * @return true if this logger is warn enabled, false otherwise
     */
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    /**
     * Log a message at the INFO level.
     *
     * @param message the message string to be logged
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Log a message with the specific Marker at the INFO level.
     *
     * @param marker  The marker specific to this log statement
     * @param message the message string to be logged
     */
    public void info(Marker marker, String message) {
        logger.info(marker, message);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and argument.
     *
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void info(String formatMessage, Object arg) {
        logger.info(formatMessage, arg);
    }

    /**
     * This method is similar to {@link #info(String, Object)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void info(Marker marker, String formatMessage, Object arg) {
        logger.info(marker, formatMessage, arg);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     *
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void info(String formatMessage, Object arg1, Object arg2) {
        logger.info(formatMessage, arg1, arg2);
    }

    /**
     * This method is similar to {@link #info(String, Object, Object)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void info(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.info(marker, formatMessage, arg1, arg2);
    }

    /**
     * Log a message at the INFO level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the INFO level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for INFO. The variants taking
     * {@link #info(String, Object) one} and {@link #info(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void info(String formatMessage, Object... arguments) {
        logger.info(formatMessage, arguments);
    }

    /**
     * This method is similar to {@link #info(String, Object...)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void info(Marker marker, String formatMessage, Object... arguments) {
        logger.info(marker, formatMessage, arguments);
    }

    /**
     * Log an exception (throwable) at the INFO level with an
     * accompanying message.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void info(String message, Throwable throwable) {
        logger.info(message, throwable);
    }

    /**
     * except that the marker data is also taken into consideration.
     *
     * @param marker    the marker data for this log statement
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void info(Marker marker, String message, Throwable throwable) {
        logger.info(marker, message, throwable);
    }

    /**
     * Is the logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for the DEBUG level,
     * false otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Similar to {@link #isDebugEnabled()} method except that the
     * marker data is also taken into account.
     *
     * @param marker The marker data to take into consideration
     * @return True if this Logger is enabled for the DEBUG level,
     * false otherwise.
     */
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    /**
     * Log a message at the DEBUG level.
     *
     * @param message the message string to be logged
     */
    public void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log a message with the specific Marker at the DEBUG level.
     *
     * @param marker  the marker data specific to this log statement
     * @param message the message string to be logged
     */
    public void debug(Marker marker, String message) {
        logger.debug(marker, message);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and argument.
     *
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void debug(String formatMessage, Object arg) {
        logger.debug(formatMessage, arg);
    }

    /**
     * This method is similar to {@link #debug(String, Object)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void debug(Marker marker, String formatMessage, Object arg) {
        logger.debug(marker, formatMessage, arg);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     *
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void debug(String formatMessage, Object arg1, Object arg2) {
        logger.debug(formatMessage, arg1, arg2);
    }

    /**
     * This method is similar to {@link #debug(String, Object, Object)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void debug(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.debug(marker, formatMessage, arg1, arg2);
    }

    /**
     * Log a message at the DEBUG level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the DEBUG level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for DEBUG. The variants taking
     * {@link #debug(String, Object) one} and {@link #debug(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void debug(String formatMessage, Object... arguments) {
        logger.debug(formatMessage, arguments);
    }

    /**
     * This method is similar to {@link #debug(String, Object...)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void debug(Marker marker, String formatMessage, Object... arguments) {
        logger.debug(marker, formatMessage, arguments);
    }

    /**
     * Log an exception (throwable) at the DEBUG level with an
     * accompanying message.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void debug(String message, Throwable throwable) {
        logger.debug(message, throwable);
    }

    /**
     * This method is similar to {@link #debug(String, Throwable)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker    the marker data specific to this log statement
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void debug(Marker marker, String message, Throwable throwable) {
        logger.debug(marker, message, throwable);
    }

    /**
     * Is the logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level,
     * false otherwise.
     */
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    /**
     * Similar to {@link #isWarnEnabled()} method except that the marker
     * data is also taken into consideration.
     *
     * @param marker The marker data to take into consideration
     * @return True if this Logger is enabled for the WARN level,
     * false otherwise.
     */
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    /**
     * Log a message at the WARN level.
     *
     * @param message the message string to be logged
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log a message with the specific Marker at the WARN level.
     *
     * @param marker  The marker specific to this log statement
     * @param message the message string to be logged
     */
    public void warn(Marker marker, String message) {
        logger.warn(marker, message);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and argument.
     *
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void warn(String formatMessage, Object arg) {
        logger.warn(formatMessage, arg);
    }

    /**
     * This method is similar to {@link #warn(String, Object)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void warn(Marker marker, String formatMessage, Object arg) {
        logger.warn(marker, formatMessage, arg);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     *
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void warn(String formatMessage, Object arg1, Object arg2) {
        logger.warn(formatMessage, arg1, arg2);
    }

    /**
     * This method is similar to {@link #warn(String, Object, Object)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void warn(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.warn(marker, formatMessage, arg1, arg2);
    }

    /**
     * Log a message at the WARN level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the WARN level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for WARN. The variants taking
     * {@link #warn(String, Object) one} and {@link #warn(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void warn(String formatMessage, Object... arguments) {
        logger.warn(formatMessage, arguments);
    }

    /**
     * This method is similar to {@link #warn(String, Object...)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void warn(Marker marker, String formatMessage, Object... arguments) {
        logger.warn(marker, formatMessage, arguments);
    }

    /**
     * Log an exception (throwable) at the WARN level with an
     * accompanying message.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void warn(String message, Throwable throwable) {
        logger.warn(message, throwable);
    }

    /**
     * This method is similar to {@link #warn(String, Throwable)} method
     * except that the marker data is also taken into consideration.
     *
     * @param marker    the marker data for this log statement
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void warn(Marker marker, String message, Throwable throwable) {
        logger.warn(marker, message, throwable);
    }

    /**
     * Is the logger instance enabled for the ERROR level?
     *
     * @return True if this Logger is enabled for the ERROR level,
     * false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    /**
     * Similar to {@link #isErrorEnabled()} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker The marker data to take into consideration
     * @return True if this Logger is enabled for the ERROR level,
     * false otherwise.
     */
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    /**
     * Log a message at the ERROR level.
     *
     * @param message the message string to be logged
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * Log a message with the specific Marker at the ERROR level.
     *
     * @param marker  The marker specific to this log statement
     * @param message the message string to be logged
     */
    public void error(Marker marker, String message) {
        logger.error(marker, message);
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and argument.
     * <p/>
     * <p>This form avoids superfluous object creation when the logger
     * is disabled for the ERROR level. </p>
     *
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void error(String formatMessage, Object arg) {
        logger.error(formatMessage, arg);
    }

    /**
     * This method is similar to {@link #error(String, Object)} method except that the
     * marker data is also taken into consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg           the argument
     */
    public void error(Marker marker, String formatMessage, Object arg) {
        logger.error(marker, formatMessage, arg);
    }

    /**
     * Log a message at the ERROR level according to the specified format
     * and arguments.
     *
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void error(String formatMessage, Object arg1, Object arg2) {
        logger.error(formatMessage, arg1, arg2);
    }

    /**
     * This method is similar to {@link #error(String, Object, Object)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arg1          the first argument
     * @param arg2          the second argument
     */
    public void error(Marker marker, String formatMessage, Object arg1, Object arg2) {
        logger.error(marker, formatMessage, arg1, arg2);
    }

    /**
     * * Log a message at the ERROR level according to the specified format
     * and arguments.
     * <p/>
     * <p>This form avoids superfluous string concatenation when the logger
     * is disabled for the ERROR level. However, this variant incurs the hidden
     * (and relatively small) cost of creating an <code>Object[]</code> before invoking the method,
     * even if this logger is disabled for ERROR. The variants taking
     * {@link #error(String, Object) one} and {@link #error(String, Object, Object) two}
     * arguments exist solely in order to avoid this hidden cost.</p>
     *
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void error(String formatMessage, Object... arguments) {
        logger.error(formatMessage, arguments);
    }

    /**
     * This method is similar to {@link #error(String, Object...)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker        the marker data specific to this log statement
     * @param formatMessage the format string
     * @param arguments     a list of 3 or more arguments
     */
    public void error(Marker marker, String formatMessage, Object... arguments) {
        logger.error(marker, formatMessage, arguments);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * This method is similar to {@link #error(String, Throwable)}
     * method except that the marker data is also taken into
     * consideration.
     *
     * @param marker    the marker data specific to this log statement
     * @param message   the message accompanying the exception
     * @param throwable the exception (throwable) to log
     */
    public void error(Marker marker, String message, Throwable throwable) {
        logger.error(marker, message, throwable);
    }

}
