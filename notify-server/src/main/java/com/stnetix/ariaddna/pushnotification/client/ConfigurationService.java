/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.pushnotification.client;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Created by LugovoyAV on 03.05.2017.
 */
public class ConfigurationService {
    private String nameWebSocket;
    private String pushNotificationServerURL;

    ConfigurationService() {
        LoadConfiguration();
    }

    public String getNameWebSocket() {
        return nameWebSocket;
    }

    public String getPushNotificationServerURL() {
        return pushNotificationServerURL;
    }

    public void LoadConfiguration() {
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

