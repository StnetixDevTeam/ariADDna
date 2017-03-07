package com.stnetix.ariaddna.servercore.tools;


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

    public ServiceTree() {
        nodes = new HashSet<>();
    }

    public void addService(IService service, IService parent) throws ServiceNotFoundException {
        ServiceNode node = new ServiceNode(service, parent);
        if (!nodes.contains(node)) {
            nodes.add(node);
        }
    }

    public void startServer() {
        List<ServiceNode> startQueue = createStartQueue();
    }

    private ArrayList<ServiceNode> createStartQueue() {
        ArrayList<ServiceNode> startQueue = new ArrayList<>();
        
        return startQueue;
    }

    private class ServiceNode {
        private IService service;
        private HashSet<ServiceNode> childes;
        private boolean isRun;

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
