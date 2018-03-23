package com.stnetix.ariaddna.commonutils.xmlparser.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by LugovoyAV on 20.02.2018.
 */
public class BlockManipulationSettingHandler extends DefaultHandler {
    private int blockSize;

    public int getBlockSize() {
        return blockSize;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("BlockManipulationService")){
            blockSize = Integer.parseInt(attributes.getValue("BlockSize"));

        }
    }
}
