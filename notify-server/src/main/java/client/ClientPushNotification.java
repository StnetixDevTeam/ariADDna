package client;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lexsus on 30.03.2017.
 */
public class ClientPushNotification implements ClientSocketListener{

    private String dest;
    WebSocketClient client = new WebSocketClient();
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public BlockingQueue<String> getQueue() {
        return queue;
    }

    public ClientPushNotification(String dest) {
        this.dest = dest;

    }

    public void start() throws Exception {

        SimpleEchoSocket socket = new SimpleEchoSocket(this,queue);
        try
        {
            client.start();

            connect(socket);
            //socket.awaitClose();


        }
        catch (Throwable t)
        {
            t.printStackTrace();
            client.stop();
        }
    }

    private void connect(SimpleEchoSocket socket){
        URI echoUri = null;
        try {
            echoUri = new URI(dest);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        try {
            client.connect(socket,echoUri,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Connecting to : %s%n",echoUri);

        // wait for closed socket connection.
        try {
            socket.awaitClose(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){

    }
    @Override
    public void onClose(int statusCode, String reason) {
        if (client.isRunning())
            connect(new SimpleEchoSocket(this,queue));
    }
}
