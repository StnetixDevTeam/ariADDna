package client;

import java.util.concurrent.BlockingQueue;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ClientMessageSystem {
    private boolean   isStopped;
    public void runMessagesSystem(BlockingQueue<String> queue) {
        isStopped = false;
        new Thread(() -> {
            while (!isStopped) {
                try {
                    String str = queue.take();
                    System.out.printf("[ClientMessageSystem]Got msg: %s%n",str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
