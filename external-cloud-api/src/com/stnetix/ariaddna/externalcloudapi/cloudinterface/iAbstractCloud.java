package com.stnetix.ariaddna.externalcloudapi.cloudinterface;

/**
 * This is an abstract interface containing general methods for manipulation with any external cloud storage.
 * Every certain cloud storage realization must implement this interface.
 */
public interface iAbstractCloud {
    /**
     * An uploadBinFile method implementation takes as a parameter a reference to a file-chunk.
     * After that it should initiate a transferring procedure to an external cloud storage.
     * @return The method implementation should return the amount of successfully sent bytes orex
     * zero if the transfer fails.
     */

    //TODO look for possibility of a cloud provider to return successfully sent byte amount.
    //TODO Function should take as a parameter a ref to a file chunk: Type?
    int uploadFile();

    /**
     * Provides the ability to upload a file from one cloud disk storage to another.
     * @param path A path where the uploaded file should be stored.
     * @param url A URL of an external file which may be provided by another cloud.
     *            Also may be any link to any file in the Internet.
     */
    void uploadFileExternally(String path, String url);

    /**
     * A downloadBinFile method implementation is responsible for downloading a file-chunk from
     * a cloud storage to user's local storage.
     */
    //TODO what should be a parameter and a return value?
    void downloadFile();

    /**
     * Provides a copying procedure. Thus as a result one should have to similar files.
     * @param from Stands for the path to a file that would be copied.
     * @param path This is a new path for the copied file.
     */
    void copyFile(String from, String path);

    /**
     * This method implementation moves the file from one path to another.
     * @param from Stands for the path to a file that would be copied.
     * @param path This is a new path where the file will be moved.
     */
    void moveFile(String from, String path);

    /**
     * A createDirectory implementation method allows one to create a new folder or directory
     * at a cloud storage.
     * @param path A path where a new directory will be created relatively a root of a cloud storage.
     * @return Gives back a URL to a created folder.
     */
    String createDirectory(String path);

    /**
     * This method implementation should remove a directory or a file.
     * @param path A path to a file which would be removed.
     * @return A method implementation should return true if a directory was remove and false otherwise.
     */
    //FIXME should it be a response body?
    boolean removeFile(String path);

    /**
     * A method implementation should provide the possibility to receive
     * different information about a file.
     * E.g. creation time, file type, size and so on.
     * Notice, that "file" both means a usual file and a directory.
     * @return JSON object with meta data.
     */
    //FIXME return and param values
    String getFileMetaData();

    /**
     * This method implementation should work similar with getFileMetaData but retrieving information about directory.
     */
    //FIXME return and param values
    void getDirectoryMetaData();

    /**
     * This method implementation should provide the ability to figure out how much available space for a user
     * is at a cloud storage.
     * @return Free space in bytes.
     */
    int getAvailableSpace();

    /**
     * An implementation should allow one to get as much informaation
     * about user's cloud disk storage as possible.
     * Depending on a cloud storage provider the response may vary.
     * At least the returned value should contain:
     * - total space available
     * - used space
     * = free space
     *
     * @return JSON object with a data.
     */
    int getCloudDiskMetadata();
}