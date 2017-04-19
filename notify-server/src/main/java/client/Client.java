package client;

/**
 * Created by Vladislav Gasanov on 28.03.2017.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
//        ClientSocketService client = new ClientSocketService(destUri);
//        try {
//            client.start();
//            new ClientMessageSystem().runMessagesSystem();
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigClient.class);
        ClientMessageSystem client = applicationContext.getBean(ClientMessageSystem.class);
        client.runMessagesSystem();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        WebSocketClient client = new WebSocketClient();
//        AutoConnectSocket socket = new AutoConnectSocket(destUri);
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
