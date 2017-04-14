package com.lexsus.ariaddna.server;

import client.Client;
import org.eclipse.jetty.websocket.api.Session;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by LugovoyAV on 04.04.2017.
 */
@Service
public class UserServiceImpl implements UserService{
    
    private ConcurrentHashMap<Session,ClientInfo> queueUsers = new ConcurrentHashMap<>();

    private ConcurrentHashMap<ClientInfo,BlockingQueue<String>> clientMap = new ConcurrentHashMap<>();
    private HashMap<ClientInfo,WorkerThread> workerHashMap = new HashMap<>();


    @Override
    //TODO individual message
    public void sendMessage(ClientInfo clientInfo, String text) throws IOException {
//        for (Session session :
//                queueUsers) {

//            session.getRemote().sendString("TextMessage");
//        }
    }

    @Override
    public void sendMessageAll(String text) throws IOException {
        Set<Map.Entry<ClientInfo,BlockingQueue<String>>> set = clientMap.entrySet();
        System.out.println("sendMessageAll text ="+ text);
        if (set==null)
        {
            System.out.println("set == null ");
        }


        //Iterator<Map.Entry<ClientInfo,BlockingQueue<String>>> iter = set.iterator();
        for (Map.Entry<ClientInfo,BlockingQueue<String>> entry:set) {
           try {
                BlockingQueue<String> q = entry.getValue();
                if (q==null) {

                    System.out.println("queue==null");
                }
                else
                {
                    if (text==null)
                    {
                        System.out.println("text==null");
                    }
                    System.out.println("service message put = "+text);
                    q.put(text);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public void removeClient(Session session) {
        ClientInfo client = queueUsers.get(session);
        if (client!=null) {
            WorkerThread worker = workerHashMap.get(session);
            if (worker != null)
                worker.setStopped(true);
        }
        System.out.println("remove Client");
        clientMap.remove(client);
        queueUsers.remove(session);
    }

    private ClientInfo GetClientInfoBySession(Session session) {
        //TODO add sevice for getting client iformation
        ClientInfo client = new ClientInfo();
        clientMap.put(client,new LinkedBlockingQueue<>());
        return client;
    }

    public void addClient(Session session)
    {
        ClientInfo clientInfo = GetClientInfoBySession(session);
        queueUsers.put(session, clientInfo);
        ExecutorService executorService = Executors.newCachedThreadPool();
        WorkerThread worker = new WorkerThread(clientMap.get(clientInfo),session);
        workerHashMap.put(clientInfo,worker);
        executorService.execute(worker);

    }

        
}
