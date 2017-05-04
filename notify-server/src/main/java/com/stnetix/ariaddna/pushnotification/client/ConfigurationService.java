package com.stnetix.ariaddna.pushnotification.client;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by LugovoyAV on 03.05.2017.
 */
public class ConfigurationService {
    ConfigurationService(){
        LoadConfiguration();
    }
    private String nameWebSocket;
    private String pushNotificationServerURL;

    public String getNameWebSocket() {
        return nameWebSocket;
    }

    public String getPushNotificationServerURL() {
        return pushNotificationServerURL;
    }

    public void LoadConfiguration()
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = null;

                saxParser = saxParserFactory.newSAXParser();



            MyHandler handler = new MyHandler();
            saxParser.parse(new File("settings.xml"), handler);
            //Get Employees list
            nameWebSocket = handler.getNameWebSocket();
            pushNotificationServerURL = handler.getPushNotificationServerURL();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

