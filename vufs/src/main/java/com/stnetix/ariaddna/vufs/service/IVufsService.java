package com.stnetix.ariaddna.vufs.service;

import com.stnetix.ariaddna.vufs.BusinessObjects.Metafile;

import java.util.Set;

/**
 * Created by vasap87 on 20.02.18.
 */
public interface IVufsService {

    Metafile createEmptyMetafile();

    Metafile getMetafileByUuid(String fileUuid);

    Metafile addBlockByUuidToMetafile(String blockUuid, Metafile metafile);

    Metafile removeBlockByUuidFromMetafile(String blockUuid, Metafile metafile);

    boolean addMetafileToMetatable(Metafile metafile);

    boolean removeMetafileFromMetatable(Metafile metafile);

    Set<String> getAllocationByBlockUuid(String blockUuid);

    void setAllocationForBlockByUuid(String blockByUuid);
}
