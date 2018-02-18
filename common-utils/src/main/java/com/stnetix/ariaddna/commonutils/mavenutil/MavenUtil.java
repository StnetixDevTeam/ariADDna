package com.stnetix.ariaddna.commonutils.mavenutil;


import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helps to get current version
 */
public class MavenUtil {

    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(MavenUtil.class);

    public static String getCurrentVersion() {
        Model model = null;
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            if ((new File("pom.xml")).exists())
                model = reader.read(new FileReader("pom.xml"));
            else
                model = reader.read(new InputStreamReader(MavenUtil.class.getResourceAsStream("/META-INF/maven/com.stnetix.ariaddna/common-utils/pom.xml")));

        } catch (IOException e) {
            LOGGER.error("Can't read Pom.xml file. Exception message is: ", e.getMessage());
        } catch (XmlPullParserException e) {
            LOGGER.error("Can't parse Pom.xml file. Exception message is: ", e.getMessage());
        }
        return model != null ? model.getParent().getVersion() : "0.0.0";
    }
}
