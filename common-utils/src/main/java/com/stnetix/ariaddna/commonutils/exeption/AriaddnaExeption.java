package com.stnetix.ariaddna.commonutils.exeption;

/**
 * This class is the parent of all exceptions in the Ariaddna project.
 */

public class AriaddnaExeption extends Exception {

    private String errorMessage;

    /**
     * Constructor of new AriaddnaExeption with the specified cause.
     * @param cause
     * */
    public AriaddnaExeption(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor of new AriaddnaExeption with traceMessage.
     * @param traceMessage
     * */
    public AriaddnaExeption(String traceMessage) {
        super(traceMessage);
    }

    /**
     * Constructor of new AriaddnaExeption with traceMessage and errorMessage.
     * @param traceMessage
     * @param errorMessage
     * */
    public AriaddnaExeption(String traceMessage, String errorMessage){
        super(traceMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor of new AriaddnaExeption with traceMessage and the specified cause.
     * @param traceMessage
     * @param cause
     * */
    public AriaddnaExeption(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    /**
     * Constructor of new AriaddnaExeption with traceMessage, errorMessage and the specified cause.
     * @param traceMessage
     * @param errorMessage
     * @param cause
     * */
    public AriaddnaExeption(String traceMessage, String errorMessage, Throwable cause) {
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