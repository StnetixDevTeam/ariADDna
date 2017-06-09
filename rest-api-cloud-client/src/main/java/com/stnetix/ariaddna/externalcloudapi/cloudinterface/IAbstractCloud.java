package com.stnetix.ariaddna.externalcloudapi.cloudinterface;

import com.google.gson.JsonObject;

import java.io.File;
import java.net.URL;

/**
 * This is an abstract interface containing general methods for
 * manipulation with resources at any external cloud storage.
 * One should remember that a "resource" means a file or a directory.
 * Also here are methods for general operations with a cloud.
 * Every certain cloud storage realization must implement this interface.
 */
public interface IAbstractCloud {
    /**
     * An uploadFile method implementation takes as a parameter a path to a file
     * which is stored at user's local HDD/SSD and sends it to an external cloud storage.
     *
     * @param path A path to a file at user's local storage.
     * @return Contains different information about uploaded file.
     */

    JsonObject uploadFile(File path);

    /**
     * Provides the ability to upload a file from one cloud disk storage to another.
     * A URL may point to any file that is available at the Internet.
     * @param path A path where the uploaded file should be stored.
     * @param url A URL to an external resource.
     * @return A representation of response with useful information.
     */
    JsonObject uploadExternalFile(File path, URL url);

    /**
     * A downloadFile method implementation is responsible for downloading a file from
     * a cloud storage to user's local storage.
     * @param path A path to a file which will be downloaded
     * @return A representation of response with useful information.
     */
    JsonObject downloadFile(File path);

    /**
     * Provides a copying procedure. Thus as a result one should have to similar files.
     * @param from Stands for the path to a file that would be copied.
     * @param to This is a new path for the copied file.
     * @return A representation of response with useful information.
     */
    JsonObject copyFile(File from, File to);

    /**
     * This method implementation moves the file from one path to another.
     * @param from Stands for the path to a file that would be copied.
     * @param to This is a new path where the file will be moved.
     * @return A representation of response with useful information.
     */
    JsonObject moveFile(File from, File to);

    /**
     * A createDirectory implementation method allows one to create a new folder or directory
     * at a cloud storage.
     * @param path A path where a new directory will be created.
     * @return Contains all necessary information gathered from a server response.
     */
    JsonObject createDirectory(File path);

    /**
     * This method implementation should delete a directory or a file.
     * Both of them are treated as resource.
     * @param path A path to a file which would be removed.
     * @return A response containing metadata of a deleted resource.
     */
    JsonObject deleteResource(File path);

    /**
     * A method implementation should provide the possibility to receive
     * different information - metadata - about a resource (a file or a directory).
     * E.g. creation time, file type, size and so on.
     * @param path A path to a resource at a cloud storage.
     * @return A response contains a resource's metadata.
     */
    JsonObject getResourceMetadata(File path);

    /**
     * An implementation should allow one to get as much informaation
     * about user's cloud disk storage as possible.
     * Depending on a cloud storage provider the response may vary.
     * At least the returned value should contain:
     * - total space available
     * - used space
     * = free space
     *
     * @return All possible information about an external storage
     */
    JsonObject getCloudStorageMetadata();

    /**
     * Should provide the ability to get OAuth 2.0 token.
     * @return Response should contain an access token.
     */
    JsonObject getCloudStorageAuthToken();

    /**
     * Calling this mehtod one can disable an access token.
     * @return A response representation.
     */
    JsonObject revokeCloudStorageAuthToken();
}