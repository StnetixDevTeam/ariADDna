package com.stnetix.ariaddna.commonutils.DTO.vufs;

import java.io.Serializable;

/**
 * Describe file property.
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
