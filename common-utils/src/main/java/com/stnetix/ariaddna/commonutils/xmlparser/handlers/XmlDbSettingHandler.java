package com.stnetix.ariaddna.commonutils.xmlparser.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by alexkotov on 30.05.17.
 */
public class XmlDbSettingHandler extends DefaultHandler {

    private String url;
    private String login;
    private String pass;
    private String driverClass;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("StarageServiceDB")){
            url = attributes.getValue("URL");
            driverClass = attributes.getValue("DriverClass");
            login = attributes.getValue("login");
            pass = attributes.getValue("pass");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getDriverClass() {
        return driverClass;
    }
}
