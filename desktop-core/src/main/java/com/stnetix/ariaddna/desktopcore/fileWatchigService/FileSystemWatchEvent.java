package com.stnetix.ariaddna.desktopcore.fileWatchigService;

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

    public Type getType() {
        return type;
    }

    public Path getPathFrom() {
        return pathFrom;
    }

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
