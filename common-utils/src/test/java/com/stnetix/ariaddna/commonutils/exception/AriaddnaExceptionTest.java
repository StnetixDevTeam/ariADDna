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

package com.stnetix.ariaddna.commonutils.exception;

import org.junit.Test;

/**
 * Created by alexkotov on 01.02.17.
 */
public class AriaddnaExceptionTest {
    @Test
    public void getErrorMessageManyLevels() throws Exception {
        Throwable t1 = new AriaddnaException("1 уровень");
        Throwable t2 = new AriaddnaException("2 уровень", t1);
        Throwable t3 = new AriaddnaException("3 уровень", t2);
        Throwable t4 = new AriaddnaException("4 уровень", t3);
        Throwable t5 = new AriaddnaException("5 уровень", t4);
        Throwable t6 = new AriaddnaException("6 уровень", t5);
        Throwable t7 = new AriaddnaException("7 уровень", t6);
        Throwable t8 = new AriaddnaException("8 уровень", t7);
        Throwable t9 = new AriaddnaException("9 уровень", t8);
        try {
            throw t9;
        } catch (AriaddnaException e) {
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    @Test
    public void getErrorMessageTwoLevel() {
        Throwable t1 = new AriaddnaException("1 уровень");
        Throwable t2 = new AriaddnaException("2 уровень", t1);
        try {
            throw t2;
        } catch (AriaddnaException e) {
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Test
    public void getErrorMessageOneLevel() {
        Throwable t1 = new AriaddnaException("1 уровень");
        try {
            throw t1;
        } catch (AriaddnaException e) {
            System.out.println(e.getErrorMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}