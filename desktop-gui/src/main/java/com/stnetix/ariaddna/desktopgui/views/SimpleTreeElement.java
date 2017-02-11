package com.stnetix.ariaddna.desktopgui.views;

/**
 * Simple model for create a files tree view
 * TODO: replace with VUFS item
 * @author slonikmak
 */
public class SimpleTreeElement {
    private String name;
    private int id;

    public SimpleTreeElement(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SimpleTreeElement{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
