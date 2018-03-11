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

package com.stnetix.ariaddna.localstoragemanager.event;

import java.nio.file.Path;

/**
 * event for external processing
 */
public class FileSystemWatchEvent {
    /**
     * Event types
     */
    public enum Type {
        CREATE, DELETE, MOVE, RENAME, MODIFY
    }

    private Type type;
    private Path pathFrom;
    private Path pathTo;

    /**
     * Constructor for CREATE, DELETE and MODIFY events
     *
     * @param type     event type
     * @param pathFrom path to changed file
     */
    public FileSystemWatchEvent(Type type, Path pathFrom) {
        this.type = type;
        this.pathFrom = pathFrom;
        this.pathTo = null;
    }

    /**
     * Constructor for MOVE or RENAME events
     *
     * @param type     event type
     * @param pathFrom path to changed file
     * @param pathTo   new name or destination file
     */
    public FileSystemWatchEvent(Type type, Path pathFrom, Path pathTo) {
        this.type = type;
        this.pathFrom = pathFrom;
        this.pathTo = pathTo;
    }

    /**
     * @return type of event
     */
    public Type getType() {
        return type;
    }

    /**
     * @return path to the item where the event occurred
     */
    public Path getPathFrom() {
        return pathFrom;
    }

    /**
     * @return path to the item after renaming
     */
    public Path getPathTo() {
        return pathTo;
    }

    @Override
    public String toString() {
        return "FileSystemWatchEvent{" +
                "type=" + type +
                ", pathFrom=" + pathFrom +
                ", pathTo=" + pathTo +
                '}';
    }
}
