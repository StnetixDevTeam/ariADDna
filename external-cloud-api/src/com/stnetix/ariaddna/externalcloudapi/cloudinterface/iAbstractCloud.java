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
    int uploadBinFile();

    /**
     * A downloadBinFile method implementation is responsible for downloading a file-chunk from
     * a cloud storage to user's local storage.
     */
    //TODO what should be a parameter and a return value?
    void downloadBinFile();

    /**
     * A createDirectory implementation method allows one to create a new folder or directory
     * at a cloud storage.
     * @return A method implementation should signal whether a directory was successfully created (true) or not (false).
     */
    //FIXME param?
    boolean createDirectory();

    /**
     * This method implementation is similar with createDirectory but removes a certain directory.
     * @return A method implementation should return true if a directory was remove and false otherwise.
     */
    //FIXME param?
    boolean removeDirectory();

    /**
     * A method implementation should provide the possibility to receive different information about file.
     * E.g. creation time, file type, size and so on.
     */
    //FIXME return and param values
    void getFileMetaData();

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
     * This method implementation should tell one how much of a cloud storage space is already used.
     * @return Used space in bytes.
     */
    int getUsedSpace();
}