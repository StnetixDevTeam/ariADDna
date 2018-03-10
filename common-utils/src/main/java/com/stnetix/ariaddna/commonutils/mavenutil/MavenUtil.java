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

package com.stnetix.ariaddna.commonutils.mavenutil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

/**
 * Helps to get current version
 */
public class MavenUtil {

    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(MavenUtil.class);

    private MavenUtil() {
    }

    public static String getCurrentVersion() {
        Model model = null;
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            if ((new File("pom.xml")).exists()) {
                model = reader.read(new FileReader("pom.xml"));
            } else {
                model = reader.read(new InputStreamReader(MavenUtil.class.getResourceAsStream(
                        "/META-INF/maven/com.stnetix.ariaddna/common-utils/pom.xml")));
            }

        } catch (IOException e) {
            LOGGER.error("Can't read Pom.xml file. Exception message is: ", e.getMessage());
        } catch (XmlPullParserException e) {
            LOGGER.error("Can't parse Pom.xml file. Exception message is: ", e.getMessage());
        }
        return model != null ? model.getParent().getVersion() : "0.0.0";
    }
}
