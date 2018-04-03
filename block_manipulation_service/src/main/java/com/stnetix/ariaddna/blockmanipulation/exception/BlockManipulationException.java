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

package com.stnetix.ariaddna.blockmanipulation.exception;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 *This exception was throws in BlockManipulation Service.
 * */

public abstract class BlockManipulationException extends AriaddnaException {

    public BlockManipulationException(Throwable cause) {
        super(cause);
    }

    public BlockManipulationException(String traceMessage) {
        super(traceMessage);
    }

    public BlockManipulationException(String traceMessage, String errorMessage) {
        super(traceMessage, errorMessage);
    }

    public BlockManipulationException(String traceMessage, Throwable cause) {
        super(traceMessage, cause);
    }

    public BlockManipulationException(String traceMessage, String errorMessage, Throwable cause) {
        super(traceMessage, errorMessage, cause);
    }
}
