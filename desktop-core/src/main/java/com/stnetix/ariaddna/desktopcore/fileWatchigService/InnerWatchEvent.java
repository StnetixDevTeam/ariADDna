package com.stnetix.ariaddna.desktopcore.fileWatchigService;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * Event for the internal communication
 */
class InnerWatchEvent {
    Path path;
    WatchEvent<Path> event;

    /**
     * @param path  path of event element
     * @param event
     */
    InnerWatchEvent(Path path, WatchEvent<Path> event) {
        this.path = path;
        this.event = event;
    }

}
