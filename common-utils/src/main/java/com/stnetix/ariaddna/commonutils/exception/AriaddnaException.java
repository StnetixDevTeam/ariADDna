package com.stnetix.ariaddna.commonutils.exception;

/**
 * This class is the parent of all exceptions in the Ariaddna project.
 */

public class AriaddnaException extends Exception {

    private String errorMessage;

    /**
     * Constructor of new AriaddnaException with the specified cause.
     * @param cause
     * */
    public AriaddnaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor of new AriaddnaException with traceMessage.
     * @param traceMessage
     * */
    public AriaddnaException(String traceMessage) {
        super(traceMessage);
    }

    /**
     * Constructor of new AriaddnaException with traceMessage and errorMessage.
     * @param traceMessage
     * @param errorMessage
     * */
    public AriaddnaException(String traceMessage, String errorMessage){
        super(traceMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor of new AriaddnaException with traceMessage and the specified cause.
     * @param traceMessage
     * @param cause
     * */
    public AriaddnaException(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    /**
     * Constructor of new AriaddnaException with traceMessage, errorMessage and the specified cause.
     * @param traceMessage
     * @param errorMessage
     * @param cause
     * */
    public AriaddnaException(String traceMessage, String errorMessage, Throwable cause) {
        super(traceMessage, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * Method return specific errorMessage
     * @return errorMessage
     * */
    public String getErrorMessage() {
        Throwable tmp  = getCause();
        if(tmp!=null) {
            Throwable t;
            while (true) {
                t = tmp.getCause();
                if (t == null) break;
                tmp = tmp.getCause();
            }
            return tmp.getMessage();
        }
        return getMessage();
    }
}