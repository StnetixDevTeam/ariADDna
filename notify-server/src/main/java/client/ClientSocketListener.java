package client;

import org.eclipse.jetty.websocket.api.Session;

import javax.websocket.SessionException;

/**
 * Created by Lexsus on 30.03.2017.
 */
public interface ClientSocketListener {
    public void onClose(int statusCode, String reason);
    public void onMessage(Session session, String message);
}
