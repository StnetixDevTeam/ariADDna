package com.stnetix.ariaddna.vufs.BusinessObjects;

import de.greenrobot.common.hash.Murmur3A;

/**
 * Block it is elementary unity in ariADDna storage.
 */
public class Block {
    private String version;
    private String blockUuid;
    private Long number;
    private String fileUuid;
    private byte[] payload;
    private Long timestamp;
    private Long size;

    public Block(String version, Long number, String fileUuid, byte[] payload, Long timestamp, Long size) {
        this.version = version;
        this.number = number;
        this.fileUuid = fileUuid;
        this.payload = payload;
        this.timestamp = timestamp;
        this.size = size;
        generateBlockUuid(payload, fileUuid);
    }

    private void generateBlockUuid(byte[] payload, String fileUuid) {
        Murmur3A murmur = new Murmur3A();
        murmur.update(payload);
        murmur.update(fileUuid.getBytes());
        blockUuid = String.valueOf(murmur.getValue());
    }

    public String getVersion() {
        return version;
    }

    public String getBlockUuid() {
        return blockUuid;
    }

    public Long getNumber() {
        return number;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public byte[] getPayload() {
        return payload;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getSize() {
        return size;
    }
}
