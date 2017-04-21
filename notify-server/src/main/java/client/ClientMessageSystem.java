package client;

import com.lexsus.ariaddna.server.IPushConsume;
import com.lexsus.ariaddna.server.MessageProcessor;
import com.lexsus.ariaddna.server.SharedQueue;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;

/**
 * Created by LugovoyAV on 31.03.2017.
 */
public class ClientMessageSystem<E> {
    IPushConsume<E> consumer;
    @Autowired
    ClientSocketService clientService;

    public ClientMessageSystem(IPushConsume<E> consumer) {
        this.consumer = consumer;
    }

    private boolean   isStopped;
    public void runMessagesSystem() {
        isStopped = false;
        try {
            clientService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (!isStopped) {
                consumer.consume();

            }
        }).start();
    }

}
