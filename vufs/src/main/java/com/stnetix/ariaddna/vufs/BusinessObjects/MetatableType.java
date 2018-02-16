package com.stnetix.ariaddna.vufs.BusinessObjects;

/**
 * Flag of type metatable
 */
public enum MetatableType {

    MASTER {
        @Override
        public String toString() {
            return "Master";
        }
    },
    SNAPSHOT {
        @Override
        public String toString() {
            return "Snapshot";
        }
    }
}
