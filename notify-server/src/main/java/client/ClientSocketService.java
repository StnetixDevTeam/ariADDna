package client;

import com.lexsus.ariaddna.server.SharedQueue;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lexsus on 30.03.2017.
 */
public class ClientSocketService implements ClientSocketListener<String>{

    public static final int DURATION = 5;
    private String dest;
    WebSocketClient client = new WebSocketClient();
    private SharedQueue<String> queue;


    public ClientSocketService(String dest, SharedQueue<String> queue) {
        this.dest = dest;
        this.queue = queue;
    }


    @Override
    public void onMessage(String message) {
        try {
            queue.put(message);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {

        AutoConnectSocket socket = new AutoConnectSocket(this);
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

    private void connect(AutoConnectSocket socket){
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
            socket.awaitClose(DURATION, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){

    }
    @Override
    public void onClose(int statusCode, String reason) {
        if (client.isRunning())
            connect(new AutoConnectSocket(this));
    }
}
