package com.stnetix.ariaddna.commonutils.xmlparser;

import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by alexkotov on 30.05.17.
 */
public class XmlParser {
    private DefaultHandler handler;

    public XmlParser(File filesetting, DefaultHandler handler) throws XmlParserException {
        this.handler = handler;
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(filesetting, handler);
        } catch (ParserConfigurationException e) {
            throw new XmlParserException("Caused by: ", e);
        } catch (SAXException e) {
            throw new XmlParserException("Caused by: ", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DefaultHandler getHandler() {
        return handler;
    }
}
