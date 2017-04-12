package client;

/**
 * Created by Vladislav Gasanov on 28.03.2017.
 */
import java.util.concurrent.*;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Basic Echo Client Socket
 */
@WebSocket(maxTextMessageSize = 64 * 1024)
public class SimpleEchoSocket
{
    private final CountDownLatch closeLatch;
    private ClientSocketListener listener;
    private final BlockingQueue<String> queue;
    @SuppressWarnings("unused")
    private Session session;

//    public SimpleEchoSocket()
//    {
//        this.closeLatch = new CountDownLatch(1);
//    }

    public SimpleEchoSocket(ClientSocketListener listener,BlockingQueue<String> queue)
    {
        //this();
        this.closeLatch = new CountDownLatch(1);
        this.listener = listener;
        this.queue = queue;
    }


    public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException
    {
        return this.closeLatch.await(duration,unit);
    }


    public  void awaitClose() throws InterruptedException
    {
        this.closeLatch.await();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {

        System.out.printf("Connection closed: %d - %s%n",statusCode,reason);
        //this.session.getPolicy().get
        this.session = null;
        this.closeLatch.countDown(); // trigger latch
        listener.onClose(statusCode,reason);
    }

    @OnWebSocketConnect
    public void onConnect(Session session)
    {
        System.out.printf("Got connect: %s%n",session);
        this.session = session;
//        try
//        {
//            Future<Void> fut;
//            fut = session.getRemote().sendStringByFuture("Hello");
//            fut.get(2,TimeUnit.SECONDS); // wait for send to complete.
//
//            fut = session.getRemote().sendStringByFuture("Thanks for the conversation.");
//            fut.get(2,TimeUnit.SECONDS); // wait for send to complete.
//
//            //session.close(StatusCode.NORMAL,"I'm done");
//        }
//        catch (Throwable t)
//        {
//            t.printStackTrace();
//        }
    }

    @OnWebSocketMessage
    public void onMessage(String msg) throws InterruptedException {
        //System.out.printf("Got msg: %s%n",msg);
        queue.put(msg);
    }
}
