/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.externalcloudapi.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.stnetix.ariaddna.externalcloudapi.cloudinterface.IAbstractCloud;
import com.stnetix.ariaddna.externalcloudapi.implementation.YandexDisk;

@Ignore
public class IAbstractCloudTest {
    private static IAbstractCloud cloud;
    private static JsonObject result;
    private static URL imgUrl1;
    private static URL imgUrl2;
    private static URL bookUrl;
    private static File root;
    private static File docs;
    private static File books;
    private static File pics;
    private static List<File> files = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws Exception {
        imgUrl1 = new URL(
                "http://st.gde-fon.com/wallpapers_original/wallpapers/300884_(www.Gde-Fon.com).jpg");
        imgUrl2 = new URL("http://studymedia.in/wp-content/uploads/2016/04/mega_machines.jpg");
        bookUrl = new URL(
                "http://adm-lib.ru/books/2/Horstmann_-_Biblioteka_professionala_Java_2_Tom_2.pdf");

        root = new File("Local Storage");
        docs = new File(root, "MyDocs");
        books = new File(docs, "My Books");
        pics = new File(docs, "Картинки");

        if (!root.exists() && !docs.exists()
                && !books.exists() && !pics.exists()) {
            root.mkdir();
            docs.mkdir();
            books.mkdir();
            pics.mkdir();
            System.out.println("Directories "
                    + "\n\t" + root.getName()
                    + "\n\t" + root.getName() + "/" + docs.getName()
                    + "\n\t" + root.getName() + "/" + docs.getName() + "/" + books.getName()
                    + "\n\t" + root.getName() + "/" + docs.getName() + "/" + books.getName() + "/"
                    + pics.getName()
                    + " created");
        }

        files.add(new File(pics, "image.jpg"));
        files.add(new File(pics, "image2.jpg"));
        files.add(new File(books, "book.djvu"));

        System.out.println("Downloading files...");
        downloadFile(imgUrl1, files.get(0));
        // downloadFile(imgUrl2, files.get(1));
        // downloadFile(bookUrl, files.get(2));
        System.out.println("Done");
    }

    @AfterClass
    public static void clean() {
        for (File file : files) {
            file.delete();
        }
        System.out.println("Files were deleted");

        if (root.exists() && docs.exists()
                && books.exists() && pics.exists()) {
            pics.delete();
            books.delete();
            docs.delete();
            root.delete();
            System.out.println("Directories were deleted");
        }
    }

    static void downloadFile(URL href, File path) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .url(href)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println(response.code() + ": " + response.message());
            }
            if (path.exists()) {
                path.delete();
            }
            path.createNewFile();
            InputStream is = response.body().byteStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream fos = new FileOutputStream(path);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            int cur;
            while ((cur = bis.read()) != -1) {
                bos.write(cur);
            }
            bos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void commonYandexDiskTest() {
        result = new JsonObject();
        cloud = new YandexDisk();

        result = cloud.createDirectory(pics);
        Assert.assertEquals(201, result.get("Status Code").getAsInt());
        result = cloud.createDirectory(books);
        Assert.assertEquals(201, result.get("Status Code").getAsInt());

        result = cloud.uploadFile(files.get(0));
        Assert.assertEquals(200, result.get("Status Code").getAsInt());

        result = cloud.copyFile(files.get(0), new File(root, files.get(0).getName()));
        Assert.assertEquals(201, result.get("Status Code").getAsInt());

        result = cloud.deleteResource(root);
        Assert.assertEquals(202, result.get("Status Code").getAsInt());
    }
}
