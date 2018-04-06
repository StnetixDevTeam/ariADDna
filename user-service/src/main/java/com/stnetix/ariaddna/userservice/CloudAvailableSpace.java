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

package com.stnetix.ariaddna.userservice;

import java.util.Map;

public class CloudAvailableSpace {
    private Map<String, Long> maxAvailableCloudSpace;
    private Map<String, Long> availableCloudSpace;

    public Map<String, Long> getMaxAvailableCloudSpace() {
        return maxAvailableCloudSpace;
    }

    public void setMaxAvailableCloudSpace(
            Map<String, Long> maxAvailableCloudSpace) {
        this.maxAvailableCloudSpace = maxAvailableCloudSpace;
    }

    public Map<String, Long> getAvailableCloudSpace() {
        return availableCloudSpace;
    }

    public void setAvailableCloudSpace(Map<String, Long> availableCloudSpace) {
        this.availableCloudSpace = availableCloudSpace;
    }

    public void updateMaxAvailableCloudSpace(
            String cloudUuid, Long maxAvailableCloudSpace) {
        this.maxAvailableCloudSpace.put(cloudUuid, maxAvailableCloudSpace);
    }

    public void updateAvailableCloudSpace(String cloudUuid, Long availableCloudSpace) {
        this.availableCloudSpace.put(cloudUuid, availableCloudSpace);
    }
}
