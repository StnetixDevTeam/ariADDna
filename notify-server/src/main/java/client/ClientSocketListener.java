package client;

/**
 * Created by Lexsus on 30.03.2017.
 */
public interface ClientSocketListener {
    public void onClose(int statusCode, String reason);
}
