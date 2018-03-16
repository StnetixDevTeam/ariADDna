package com.stnetix.ariaddna.localstoragemanager.localservice;

import java.io.File;

/**
 * Created by LugovoyAV on 16.02.2018.
 */
public interface LocalService {
    File getLocalFileByUuid(String fileUuid);
}
