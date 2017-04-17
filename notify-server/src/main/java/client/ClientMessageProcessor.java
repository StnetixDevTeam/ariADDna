package client;

import com.lexsus.ariaddna.server.MessageProcessor;

/**
 * Created by LugovoyAV on 14.04.2017.
 */
public class ClientMessageProcessor implements MessageProcessor<String>{
    @Override
    public void process(String message) {
        System.out.println("[ClientMessageProcessor]: get message:"+message);
    }
}
