package com.stnetix.ariaddna.commonutils.xmlparser.exception;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 * Created by alexkotov on 30.05.17.
 */
public class XmlParserException extends AriaddnaException {
    public XmlParserException(Throwable cause) {
        super(cause);
    }

    public XmlParserException(String traceMessage) {
        super(traceMessage);
    }

    public XmlParserException(String traceMessage, String errorMessage) {
        super(traceMessage, errorMessage);
    }

    public XmlParserException(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    public XmlParserException(String traceMessage, String errorMessage, Throwable cause) {
        super(traceMessage, errorMessage, cause);
    }
}
