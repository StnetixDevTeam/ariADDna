package com.stnetix.ariaddna.localstoragemanager.localStoreIndexingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Class for represent basic file attribute
 */
public class FileAttributes {

    private Date creationTime;
    private Date modifyTime;
    private Date lastAccessTime;
    private boolean isRegularFile;
    private boolean isDirectory;
    private boolean isSymbolicLinc;
    private long size;

    /**
     * default constructor
     */
    public FileAttributes() {

    }

    /**
     * constructor with Path
     *
     * @param file file to get attributes
     * @throws IOException
     */
    public FileAttributes(Path file) throws IOException {
        BasicFileAttributes basicFileAttributes = Files.readAttributes(file, BasicFileAttributes.class);
        setCreationTime(new Date(basicFileAttributes.creationTime().toMillis()));
        setModifyTime(new Date(basicFileAttributes.lastModifiedTime().toMillis()));
        setLastAccessTime(new Date(basicFileAttributes.lastAccessTime().toMillis()));
        setDirectory(basicFileAttributes.isDirectory());
        setRegularFile(basicFileAttributes.isRegularFile());
        setSymbolicLinc(basicFileAttributes.isSymbolicLink());
        setSize(basicFileAttributes.size());
    }

    /**
     * constructor with fields
     *
     * @param creationTime
     * @param modifyTime
     * @param lastAccessTime
     * @param isRegularFile
     * @param isDirectory
     * @param isSymbolicLinc
     * @param size
     */
    public FileAttributes(Date creationTime, Date modifyTime, Date lastAccessTime, boolean isRegularFile, boolean isDirectory, boolean isSymbolicLinc, long size) {
        this.creationTime = creationTime;
        this.modifyTime = modifyTime;
        this.lastAccessTime = lastAccessTime;
        this.isRegularFile = isRegularFile;
        this.isDirectory = isDirectory;
        this.isSymbolicLinc = isSymbolicLinc;
        this.size = size;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccesTime) {
        this.lastAccessTime = lastAccesTime;
    }

    public boolean isRegularFile() {
        return isRegularFile;
    }

    public void setRegularFile(boolean regularFile) {
        isRegularFile = regularFile;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isSymbolicLinc() {
        return isSymbolicLinc;
    }

    public void setSymbolicLinc(boolean symbolicLinc) {
        isSymbolicLinc = symbolicLinc;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileAttributes{" +
                "creationTime=" + creationTime +
                ", modifyTime=" + modifyTime +
                ", lastAccessTime=" + lastAccessTime +
                ", isRegularFile=" + isRegularFile +
                ", isDirectory=" + isDirectory +
                ", isSymbolicLinc=" + isSymbolicLinc +
                ", size=" + size +
                '}';
    }
}
