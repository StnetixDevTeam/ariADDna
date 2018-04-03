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

package com.stnetix.ariaddna.commonutils.dto.vufs;

/**
 * Represent type of file on local file system.
 * */

public enum FileType {

    /**
     * Text file.
     * */
    FILE_TXT {
        @Override
        public String toString() {
            return "FILE_TXT";
        }
    },

    /**
     * Binary file.
     * */
    FILE_BIN {
        @Override
        public String toString() {
            return "FILE_BIN";
        }
    },

    /**
     * Cryptographic file
     * */
    FILE_CRYPT {
        @Override
        public String toString() {
            return "FILE_CRYPT";
        }
    },

    /**
     * Directory
     * */
    DIR {
        @Override
        public String toString() {
            return "DIR";
        }
    }
}
