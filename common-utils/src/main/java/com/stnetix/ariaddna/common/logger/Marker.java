package com.stnetix.ariaddna.common.logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * This class is copy of org.slf4j.helpers.BasicMarker with public constructor.
 */
public class Marker implements org.slf4j.Marker {
    private static final long serialVersionUID = 1432688279243058482L;

    private final String name;
    private List<org.slf4j.Marker> referenceList;

    public Marker(String name) {
        if (name == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized void add(org.slf4j.Marker reference) {
        if (reference == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        }

        // no point in adding the reference multiple times
        if (this.contains(reference)) {
            return;

        } else if (reference.contains(this)) { // avoid recursion
            // a potential reference should not its future "parent" as a reference
            return;
        } else {
            // let's add the reference
            if (referenceList == null) {
                referenceList = new Vector<org.slf4j.Marker>();
            }
            referenceList.add(reference);
        }

    }

    public synchronized boolean hasReferences() {
        return ((referenceList != null) && (referenceList.size() > 0));
    }

    public boolean hasChildren() {
        return hasReferences();
    }

    public synchronized Iterator<org.slf4j.Marker> iterator() {
        if (referenceList != null) {
            return referenceList.iterator();
        } else {
            List<org.slf4j.Marker> emptyList = Collections.emptyList();
            return emptyList.iterator();
        }
    }

    public synchronized boolean remove(org.slf4j.Marker referenceToRemove) {
        if (referenceList == null) {
            return false;
        }

        int size = referenceList.size();
        for (int i = 0; i < size; i++) {
            org.slf4j.Marker m = referenceList.get(i);
            if (referenceToRemove.equals(m)) {
                referenceList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(org.slf4j.Marker other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }

        if (this.equals(other)) {
            return true;
        }

        if (hasReferences()) {
            for (org.slf4j.Marker ref : referenceList) {
                if (ref.contains(other)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method is mainly used with Expression Evaluators.
     */
    public boolean contains(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }

        if (this.name.equals(name)) {
            return true;
        }

        if (hasReferences()) {
            for (org.slf4j.Marker ref : referenceList) {
                if (ref.contains(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String OPEN = "[ ";
    private static String CLOSE = " ]";
    private static String SEP = ", ";

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof org.slf4j.Marker))
            return false;

        final org.slf4j.Marker other = (org.slf4j.Marker) obj;
        return name.equals(other.getName());
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        if (!this.hasReferences()) {
            return this.getName();
        }
        Iterator<org.slf4j.Marker> it = this.iterator();
        org.slf4j.Marker reference;
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append(' ').append(OPEN);
        while (it.hasNext()) {
            reference = it.next();
            sb.append(reference.getName());
            if (it.hasNext()) {
                sb.append(SEP);
            }
        }
        sb.append(CLOSE);

        return sb.toString();
    }
}