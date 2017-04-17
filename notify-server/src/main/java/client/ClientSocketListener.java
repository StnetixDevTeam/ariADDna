package client;

import org.eclipse.jetty.websocket.api.Session;

import javax.websocket.SessionException;

/**
 * Created by Lexsus on 30.03.2017.
 */
public interface ClientSocketListener<E> {
    public void onClose(int statusCode, E reason);
    public void onMessage(E message);
}
