package com.stnetix.ariaddna.servercore.tools;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 21.03.17.
 */
public class ServiceTreeTest {
    @Test
    public void startServer() throws Exception {
        ServiceTree services = new ServiceTree();
        IService main = new TestMainServise();
        IService child = new TestDBServise();
        services.addService(main,null);
        services.addService(child, main);

        services.startServer();
        Thread testException = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    child.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (AriaddnaException e) {
                    e.printStackTrace();
                }
            }
        });
        testException.start();
        services.monitoring();
    }

}