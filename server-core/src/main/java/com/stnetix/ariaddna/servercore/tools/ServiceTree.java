package com.stnetix.ariaddna.servercore.tools;


import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;
import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by alexkotov on 27.02.17.
 */
public class ServiceTree {
    private HashSet<ServiceNode> nodes;
    private ArrayList<ServiceNode> startQueue;

    private static AriaddnaLogger logger = AriaddnaLogger.getLogger(ServiceTree.class);

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
        for (ServiceNode node : startQueue) {
            if (!node.getService().isRun()) {
                Thread thread = new Thread(node.getService());
                thread.start();
                logger.info("Service "+node.getService().getClass().getTypeName() + " started by server-core service.");
            }
        }
    }

    public void monitoring() throws AriaddnaException {
        boolean isStoped = false;
        while (!isStoped) {
            for (ServiceNode node : startQueue) {
                if (!node.getService().isRun()) {
                    logger.info("Service "+node.getService().getClass().getTypeName() + " stopped by itself.");
                    stopServer();
                    isStoped=true;
                    break;
                }
            }
        }
    }

    private void stopServer() throws AriaddnaException {
        for (int i = startQueue.size()-1; i>=0; i--) {
            IService service = startQueue.get(i).getService();
            if(service.isRun()) {
                logger.info("Service "+service.getClass().getTypeName() + " stopped by monitoring service.");
                startQueue.get(i).getService().stop();
            }
        }
    }

    private ArrayList<ServiceNode> createStartQueue() {
        startQueue = new ArrayList<>();
        ArrayList<ServiceNode> tmpList = new ArrayList<>(nodes);
        while (tmpList.size() > 0) {
            for (ServiceNode node : tmpList) {
                if (node.hasChilds()) {
                    for (ServiceNode childNode : node.getChildes()) {
                        if (childNode.hasChilds()) {
                            continue;
                        } else {
                            if (!startQueue.contains(childNode)) {
                                startQueue.add(childNode);
                                node.getChildes().remove(childNode);
                                break;
                            }
                        }
                    }
                    break;
                } else {
                    if (!startQueue.contains(node)) {
                        startQueue.add(node);
                        tmpList.remove(node);
                        break;
                    }
                }
            }
        }

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
            return childes==null;
        }
    }
}
