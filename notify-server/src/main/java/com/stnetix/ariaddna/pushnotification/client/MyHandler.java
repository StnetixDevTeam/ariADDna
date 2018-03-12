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
