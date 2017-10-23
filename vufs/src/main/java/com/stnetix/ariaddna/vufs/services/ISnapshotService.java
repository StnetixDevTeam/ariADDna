package com.stnetix.ariaddna.vufs.services;

import com.stnetix.ariaddna.vufs.BusinessObjects.VirtualFileBO;


/**
 * This interface describes the operations with snapshots. It helps to get new snapshot for new User,
 * get snapshot from server by user uuid, and get difference with
 */
public interface ISnapshotService {

    /**
     * Used when need to get Snapshot of VUFS from server by user.
     * @return null if to this userUuid has got snapshot on server*/
    VirtualFileBO getSnapshotByUserUuid(String userUuid);

    /**
     * This method use, when you need to make new snapshot by path*/
    VirtualFileBO getNewSnapshot(String path);

    /**
     * Method used when you need to get difference with current snapshot*/
    VirtualFileBO getDiffSnapshot(String path);

    /**
     * This Method merge current snapshot with diff*/
    VirtualFileBO mergeSnapshots(VirtualFileBO currentSnap, VirtualFileBO diffSnap);
}
