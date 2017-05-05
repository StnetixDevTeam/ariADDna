package com.stnetix.ariaddna.pushnotification.client;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by LugovoyAV on 04.05.2017.
 */
public class MyHandler extends DefaultHandler {

    private String nameWebSocket;
    private String pushNotificationServerURL;

    public String getNameWebSocket() {
        return nameWebSocket;
    }

    public String getPushNotificationServerURL() {
        return pushNotificationServerURL;
    }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {

            if (qName.equalsIgnoreCase("PushNotificationServer")) {
                pushNotificationServerURL = attributes.getValue("URL");

                nameWebSocket = attributes.getValue("name");

            }
        }
}
