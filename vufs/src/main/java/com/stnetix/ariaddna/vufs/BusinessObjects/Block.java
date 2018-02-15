package com.stnetix.ariaddna.vufs.BusinessObjects;

import de.greenrobot.common.hash.Murmur3A;

/**
 * Block it is elementary unity in ariADDna storage.
 */
public class Block {
    private String mVersion;
    private String mBlockUuid;
    private Long mNumber;
    private String mFileUuid;
    private byte[] mPayload;
    private Long mTimestamp;
    private Long mSize;

    public Block(String version, Long number, String fileUuid, byte[] payload, Long timestamp, Long size) {
        this.mVersion = version;
        this.mNumber = number;
        this.mFileUuid = fileUuid;
        this.mPayload = payload;
        this.mTimestamp = timestamp;
        this.mSize = size;
        generateBlockUuid(payload, fileUuid);
    }

    private void generateBlockUuid(byte[] payload, String fileUUid){
        Murmur3A murmur = new Murmur3A();
        murmur.update(payload);
        murmur.update(fileUUid.getBytes());
        mBlockUuid = String.valueOf(murmur.getValue());
    }

    public String getVersion() {
        return mVersion;
    }

    public String getBlockUuid() {
        return mBlockUuid;
    }

    public Long getNumber() {
        return mNumber;
    }

    public String getFileUuid() {
        return mFileUuid;
    }

    public byte[] getPayload() {
        return mPayload;
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public Long getSize() {
        return mSize;
    }
}
