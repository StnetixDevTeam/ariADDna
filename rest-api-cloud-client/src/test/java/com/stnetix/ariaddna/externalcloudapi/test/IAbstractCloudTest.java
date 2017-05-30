package com.stnetix.ariaddna.externalcloudapi.test;

import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import com.stnetix.ariaddna.externalcloudapi.implementation.YandexDisk;
import org.junit.*;

import java.io.File;

public class IAbstractCloudTest {
    private static iAbstractCloud cloud;

    private static File level_1;
    private static File level_2;
    private static File level_3;
    private static File level_4;

    @BeforeClass
    public static void setUp(){
        cloud = new YandexDisk();
        level_1 = new File("Level 1");
        level_2 = new File(level_1, "Level_2");
        level_3 = new File(level_2, "Level-3");
        level_4 = new File(level_3, "Level 4");

        if(!level_1.exists() & !level_2.exists()){
            level_1.mkdir();
            level_2.mkdir();
            level_3.mkdir();
            level_4.mkdir();
            System.out.println("Directories " + level_1.getName() + " "
                    + level_1.getName() + "/" + level_2.getName() + " created");
        }
    }

    @AfterClass
    public static void clean(){
        if(level_1.exists() & level_2.exists()){
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

    @Test
    public void testCreateDirectory() throws Exception {
//        cloud.createDirectory(level_1);
//        cloud.createDirectory(level_2);
        cloud.createDirectory(level_4);
    }

    @Test
    public void testDeleteDirectory() throws Exception {
        cloud.deleteResource(level_1);
    }

    @Test
    public void testDownloadFile() throws Exception {
        File path = new File("Мишки.jpg");
        cloud.downloadFile(path);
    }

    @Test
    public void testUploadFile() throws Exception {
        File path = new File("Медвежата.jpg");
        cloud.uploadFile(path);
    }
}
