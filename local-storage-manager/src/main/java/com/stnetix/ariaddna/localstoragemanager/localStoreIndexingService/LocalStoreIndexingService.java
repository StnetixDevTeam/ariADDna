package com.stnetix.ariaddna.localstoragemanager.localStoreIndexingService;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service for fast search file or folder and his metainfo on localfile storage
 */
public class LocalStoreIndexingService {

    /**
     * default constructor
     */
    public LocalStoreIndexingService() {

    }

    /**
     * Find file or directory by Name
     *
     * @param dir  directory for search
     * @param name file name
     * @return Optional with Path or empty Optional
     * @throws IOException
     */
    public Optional<Path> findFileByName(Path dir, String name) throws IOException {
        return findFiles(dir, name).stream().findFirst();
    }

    /**
     * @param dir   directory for search
     * @param match search pattern (* - eny string, ? - eny one symbol, [X,Y] - X or Y, [0-5] - range, {ABC, XYX} - ABC or XYZ)
     * @return collection of files
     * @throws IOException
     */
    public List<Path> findFiles(Path dir, String match) throws IOException {
        ArrayList<Path> pathArrayList = new ArrayList<>();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + match);
        try (Stream<Path> pathStream = Files.find(dir, Integer.MAX_VALUE, (path, basicFileAttributes) -> matcher.matches(path.getFileName()))) {
            pathArrayList.addAll(pathStream.collect(Collectors.toList()));
        }
        return pathArrayList;
    }

    /**
     * return all files in directory and subdirectories
     *
     * @param dir directory for search
     * @return collection of files
     * @throws IOException
     */
    public List<Path> findAllFiles(Path dir) throws IOException {
        List<Path> filesList = findFiles(dir, "*");
        filesList.remove(dir);
        return filesList;
    }

    /**
     * return files created after the date
     *
     * @param dir  directory for search
     * @param date date
     * @return collection of files
     * @throws IOException
     */
    public List<Path> findFilesByCreateDate(Path dir, Date date) throws IOException {
        try (Stream<Path> paths = Files.find(dir, Integer.MAX_VALUE,
                (path, basicFileAttributes) -> basicFileAttributes.creationTime().toMillis() >= date.getTime())) {
            return paths.collect(Collectors.toList());
        }
    }

    /**
     * get common file attributes
     *
     * @param file file to get attributes
     * @return {@link FileAttributes} file attributes
     * @throws IOException
     */
    public FileAttributes getFileAttributes(Path file) throws IOException {
        return new FileAttributes(file);
    }

}
