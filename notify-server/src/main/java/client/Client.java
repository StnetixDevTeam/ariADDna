package client;

/**
 * Created by Vladislav Gasanov on 28.03.2017.
 */
import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 * Example of a simple Echo Client.
 */
public class Client{

    public static void main(String[] args)
    {
        String destUri = "ws://127.0.0.1:8080/echo";
        if (args.length > 0)
        {
            destUri = args[0];
        }
        ClientPushNotification client = new ClientPushNotification(destUri);
        try {
            client.start();
            new ClientMessageSystem().runMessagesSystem(client.getQueue());

        } catch (Exception e) {
            e.printStackTrace();
        }

//        WebSocketClient client = new WebSocketClient();
//        SimpleEchoSocket socket = new SimpleEchoSocket(destUri);
//        try
//        {
//            client.start();
//
//            URI echoUri = new URI(destUri);
//            ClientUpgradeRequest request = new ClientUpgradeRequest();
//            client.connect(socket,echoUri,request);
//            System.out.printf("Connecting to : %s%n",echoUri);
//
//            // wait for closed socket connection.
//            socket.awaitClose(5,TimeUnit.SECONDS);
//            //socket.awaitClose();
//
//
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//           //     client.stop();
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
    }

}
