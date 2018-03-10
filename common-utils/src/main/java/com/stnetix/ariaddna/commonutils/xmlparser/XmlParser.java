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

package com.stnetix.ariaddna.commonutils.xmlparser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;

/**
 * Created by alexkotov on 30.05.17.
 */
public class XmlParser {
    private static AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(XmlParser.class);
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
            LOGGER.error("Problem with parsing file with setting with path:",
                    filesetting.getAbsolutePath(), "\nException is: ", e);
        }
    }

    public DefaultHandler getHandler() {
        return handler;
    }
}
