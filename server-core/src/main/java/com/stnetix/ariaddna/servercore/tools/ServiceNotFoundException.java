package com.stnetix.ariaddna.servercore.tools;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 * Created by alexkotov on 28.02.17.
 */
public class ServiceNotFoundException extends AriaddnaException {
    public ServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ServiceNotFoundException(String traceMessage) {
        super(traceMessage);
    }

    public ServiceNotFoundException(String traceMessage, String errorMessage) {
        super(traceMessage, errorMessage);
    }

    public ServiceNotFoundException(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    public ServiceNotFoundException(String traceMessage, String errorMessage, Throwable cause) {
        super(traceMessage, errorMessage, cause);
    }
}
