package com.stnetix.ariaddna.externalcloudapi.test;

import com.google.gson.JsonObject;
import com.stnetix.ariaddna.externalcloudapi.cloudinterface.iAbstractCloud;
import com.stnetix.ariaddna.externalcloudapi.implementation.YandexDisk;
import org.junit.*;

import java.io.File;

public class IAbstractCloudTest {
    private static iAbstractCloud cloud;

    private static File dir;
    private static File subDir;

    @BeforeClass
    public static void setUp(){
        cloud = new YandexDisk();
        dir = new File("This_is_a_test_folder");
        subDir = new File(dir, "sub directory");

        if(!dir.exists() & !subDir.exists()){
            dir.mkdir();
            subDir.mkdir();
            System.out.println("Directories " + dir.getName() + " "
                    + dir.getName() + "/" + subDir.getName() + " created");
        }
    }

    @AfterClass
    public static void clean(){
        if(dir.exists() & subDir.exists()){
            subDir.delete();
            dir.delete();
            System.out.println("Directories were deleted");
        }
    }


    @Test
    public void testCreateDirectory() throws Exception {
        cloud.createDirectory(dir);
        cloud.createDirectory(subDir);
    }

    @Test
    public void testDeleteDirectory() throws Exception {
        cloud.deleteResource(dir);
        cloud.deleteResource(subDir);
    }

}
