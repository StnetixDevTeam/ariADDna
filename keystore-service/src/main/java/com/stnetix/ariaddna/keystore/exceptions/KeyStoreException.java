package com.stnetix.ariaddna.keystore.exceptions;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 * Created by alexkotov on 19.04.17.
 */
public class KeyStoreException extends AriaddnaException {
    public KeyStoreException(Throwable cause) {
        super(cause);
    }

    public KeyStoreException(String traceMessage) {
        super(traceMessage);
    }

    public KeyStoreException(String traceMessage, String errorMessage) {
        super(traceMessage, errorMessage);
    }

    public KeyStoreException(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    public KeyStoreException(String traceMessage, String errorMessage, Throwable cause) {
        super(traceMessage, errorMessage, cause);
    }
}
