package com.stnetix.ariaddna.externalcloudapi.test;

import com.google.gson.JsonObject;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.IAbstractCloud;
import com.stnetix.ariaddna.externalcloudapi.implementation.YandexDisk;
import com.stnetix.ariaddna.externalcloudapi.implementation.YandexDiskHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.*;

import java.io.*;
import java.net.URL;

public class IAbstractCloudTest {
    private static IAbstractCloud cloud;
    private static JsonObject result;
    private static URL imgUrl, videoUrl, bookUrl;
    private static File level_1, level_2, level_3, level_4;

    @BeforeClass
    public static void setUp() throws Exception{
        result = new JsonObject();
        cloud = new YandexDisk();

        imgUrl = new URL("https://yadi.sk/i/1x7pp80W3JySKE");
        videoUrl = new URL("https://yadi.sk/i/dqXjGp9C3JySHs");
        bookUrl = new URL("https://yadi.sk/d/EV0xzo0K3JySQB");

        level_1 = new File("Level 1");
        level_2 = new File(level_1, "Level_2");
        level_3 = new File(level_2, "Level-3");
        level_4 = new File(level_3, "Level 4");

        if(!level_1.exists() && !level_2.exists()
                && !level_3.exists() && !level_4.exists()){
            level_1.mkdir();
            level_2.mkdir();
            level_3.mkdir();
            level_4.mkdir();
            System.out.println("Directories "
                    + "\n\t" + level_1.getName()
                    + "\n\t" + level_1.getName() + "/" + level_2.getName()
                    + "\n\t" + level_1.getName() + "/" + level_2.getName() + "/" + level_3.getName()
                    + "\n\t" + level_1.getName() + "/" + level_2.getName() + "/" + level_3.getName() + "/" + level_4.getName()
                    + " created");
        }

    }

    @AfterClass
    public static void clean(){
        if(level_1.exists() && level_2.exists()
                && level_3.exists() && level_4.exists()){
            level_4.delete();
            level_3.delete();
            level_2.delete();
            level_1.delete();
            System.out.println("Directories were deleted");
        }
    }

    @Ignore
    @Test
    public void testGetCloudStorageAuthToken() throws Exception {
        cloud.getCloudStorageAuthToken();
    }

    @Ignore
    @Test
    public void testCreateDirectory() throws Exception {
//        cloud.createDirectory(level_1);
//        cloud.createDirectory(level_2);
        result = cloud.createDirectory(level_4);
        System.out.println(result.toString());
    }

    @Ignore
    @Test
    public void testDeleteDirectory() throws Exception {
        result = cloud.deleteResource(level_1);
        System.out.println(result.toString());
    }

    @Test
    public void testDownloadFile() throws Exception {
        File path = new File("Мишки.jpg");
        result = cloud.downloadFile(path);
    }

    @Test
    public void testUploadFile() throws Exception {
        File path = new File("Медвежата.jpg");
        result = cloud.uploadFile(path);
    }

    @Test
    public void testUploadExtFile() throws Exception{
        URL url = new URL("https://yadi.sk/i/-WxwJWyN3Jm2Hq");
        File path = new File("Питер.jpg");
        result = cloud.uploadExternalFile(path, url);
    }

    @Test
    public void testGetResourceMetadata() throws Exception {
        File path = new File("Мишки.jpg");
        result = cloud.getResourceMetadata(path);
        System.out.println(result.toString());
    }

    @Test
    public void testCopyResource() throws Exception {
        File from = new File("Мишки.jpg");
        File to = new File("Лесные жители.jpg");
        result = cloud.copyFile(from, to);
        System.out.println(result.toString());
    }

}
