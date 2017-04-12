package com.lexsus.ariaddna.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by LugovoyAV on 05.04.2017.
 */
@Service
@Qualifier("main")
public class TestAuto implements ITestAuto{
    @Override
    public void test() {

    }
}
