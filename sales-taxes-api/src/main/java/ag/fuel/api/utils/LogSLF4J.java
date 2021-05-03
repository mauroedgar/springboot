/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ag.fuel.api.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauro Sousa
 */
public class LogSLF4J {

    public static void logException(Class c, Exception ex) {
        Logger LOGGER = LoggerFactory.getLogger(c);
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        LOGGER.error(ErrorsProperties.EXCEPTION + ex);
        LOGGER.error(ErrorsProperties.EXCEPTIONELEMENTS + sb.toString());
    }

    public static ResponseException getException(Class c, Exception ex) {
        List<String> stackTrace = new ArrayList<>();

        for (StackTraceElement element : ex.getStackTrace()) {
            stackTrace.add(element.toString());
        }

        return new ResponseException(ex.toString(), ex.getMessage(), stackTrace);
    }

    public static void logWarning(Class c, String message) {
        Logger LOGGER = LoggerFactory.getLogger(c);
        LOGGER.warn(ErrorsProperties.WARNING + message);
    }

    public static class ResponseException {
        String exception;
        String message;
        List<String> stacktrace;

        public ResponseException(String exception, String message, List<String> stacktrace) {
            this.exception = exception;
            this.message = message;
            this.stacktrace = stacktrace;
        }

        public String getException() {
            return exception;
        }

        public void setException(String exception) {
            this.exception = exception;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getStacktrace() {
            return stacktrace;
        }

        public void setStacktrace(List<String> stacktrace) {
            this.stacktrace = stacktrace;
        }

    }
}