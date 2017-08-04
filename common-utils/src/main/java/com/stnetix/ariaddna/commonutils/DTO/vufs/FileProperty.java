package com.stnetix.ariaddna.commonutils.DTO.vufs;

import java.io.Serializable;

/**
 * Describe file property. Used in class {@link VirtualFile} to describe basic platform
 * file settings (Owner, Creation and edit date an time, and related access permissions.
 * See more about file attributes in (<a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/BasicFileAttributeView.html">BasicFileAttributeView</a>,
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/FileOwnerAttributeView.html">FileOwnerAttributeView</a> * )
 */
public class FileProperty implements Serializable{
    private Long id;
    private String propertyName;
    private String propertyValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
