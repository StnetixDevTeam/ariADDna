package client;

import com.lexsus.ariaddna.server.MessageProcessor;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class ClientMessageProcessor<E> implements MessageProcessor<E>{
    @Override
    public void process(E message) {

        System.out.println("[ClientMessageProcessor]: get message:"+message);
    }
}
