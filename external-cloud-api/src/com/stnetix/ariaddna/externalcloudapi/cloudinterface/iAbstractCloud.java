package com.stnetix.ariaddna.externalcloudapi.cloudinterface;

/**
 * This is an abstract interface containing general methods for
 * manipulation with resources at any external cloud storage.
 * Every certain cloud storage realization must implement this interface.
 */
public interface iAbstractCloud {
    /**
     * An uploadResource method implementation takes as a parameter a path to a resource
     * which is stored at user's local HDD/SSD and sends it to an external cloud storage.
     * Notice, that resource may be a file or directory with files.
     *
     * @param path A path to a resource at user's local storage.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */

    //TODO look for possibility of a cloud provider to return successfully sent byte amount.
    //TODO Function should take as a parameter a ref to a file chunk: Type?
    String uploadResource(String path);

    /**
     * Provides the ability to upload a resource from one cloud disk storage to another.
     * A method should somehow request a URL for downloading and pass it as parameter.
     * Also a URL may point to any file that is available at the Internet.
     * @param path A path where the uploaded resource should be stored.
     * @param url A URL to an external resource.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String uploadExternalResource(String path, String url);

    /**
     * A downloadBinFile method implementation is responsible for downloading a file-chunk from
     * a cloud storage to user's local storage.
     * @param path
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String downloadResource(String path);

    /**
     * Provides a copying procedure. Thus as a result one should have to similar files.
     * @param from Stands for the path to a file that would be copied.
     * @param to This is a new path for the copied file.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String copyFile(String from, String to);

    /**
     * This method implementation moves the file from one path to another.
     * @param from Stands for the path to a file that would be copied.
     * @param to This is a new path where the file will be moved.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String moveFile(String from, String to);

    /**
     * A createDirectory implementation method allows one to create a new folder or directory
     * at a cloud storage.
     * @param path A path where a new directory will be created relatively a root of a cloud storage.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String createDirectory(String path);

    /**
     * This method implementation should delete a directory or a file.
     * @param path A path to a file which would be removed.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String deleteResource(String path);

    /**
     * A method implementation should provide the possibility to receive
     * different information about a resource.
     * E.g. creation time, file type, size and so on.
     * Notice, that "resource" both means a usual file and a directory.
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String getResourceMetadata(String path);


    /**
     * An implementation should allow one to get as much informaation
     * about user's cloud disk storage as possible.
     * Depending on a cloud storage provider the response may vary.
     * At least the returned value should contain:
     * - total space available
     * - used space
     * = free space
     *
     * @return String representation of response with usefull
     * information which may be converted into JSON Object.
     */
    String getCloudDiskMetadata();
}