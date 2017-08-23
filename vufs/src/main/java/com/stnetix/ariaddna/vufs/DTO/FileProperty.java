package com.stnetix.ariaddna.vufs.DTO;

import java.io.Serializable;
import java.util.UUID;

/**
 * Describe file property. Used in class {@link VirtualFile} to describe basic platform
 * file settings (Owner, Creation and edit date an time, and related access permissions.
 * See more about file attributes in (<a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributeView.html">BasicFileAttributeView</a>,
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/FileOwnerAttributeView.html">FileOwnerAttributeView</a> * )
 */
public class FileProperty implements Serializable{
    private UUID uuid;
    private String propertyName;
    private String propertyValue;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
