/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
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

package com.stnetix.ariaddna.vufs.businessobjects;

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

    public Block(String version, Long number, String fileUuid, byte[] payload, Long timestamp,
            Long size) {
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
