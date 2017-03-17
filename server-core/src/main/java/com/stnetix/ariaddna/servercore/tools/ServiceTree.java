package com.stnetix.ariaddna.servercore.tools;


import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by alexkotov on 27.02.17.
 */
public class ServiceTree {
    private HashSet<ServiceNode> nodes;
    private ArrayList<ServiceNode> startQueue;

    public ServiceTree() {
        nodes = new HashSet<>();
    }

    public void addService(IService service, IService parent) throws ServiceNotFoundException {
        ServiceNode node = new ServiceNode(service, parent);
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void startServer() throws AriaddnaException {
        startQueue = createStartQueue();
        for (ServiceNode node: startQueue) {
            if(!node.getService().isRun()) {
                Thread thread = new Thread(node.getService());
                thread.start();
            }
        }
    }

    public void monitoring() {
        for (ServiceNode node: startQueue) {
            //TODO: write monitoring service
        }
    }

    private ArrayList<ServiceNode> createStartQueue() {
        startQueue = new ArrayList<>();
        //TODO: write start queue
        return startQueue;
    }

    private class ServiceNode {
        private IService service;
        private HashSet<ServiceNode> childes;


        ServiceNode(IService service, IService parent) throws ServiceNotFoundException {
            if (service == null) throw new ServiceNotFoundException("Service must be not null");
            this.service = service;
            if (parent != null) {
                ServiceNode parentServiceNode = null;
                for (ServiceNode node : nodes) {
                    if (node.getService().equals(parent)) parentServiceNode = node;
                }
                if (parentServiceNode != null) parentServiceNode.getChildes().add(this);
            }
        }

        public IService getService() {
            return service;
        }

        public HashSet<ServiceNode> getChildes() {
            if (childes == null) childes = new HashSet<>();
            return childes;
        }

        public boolean hasChilds() {
            return childes.size() > 0;
        }
    }
}
