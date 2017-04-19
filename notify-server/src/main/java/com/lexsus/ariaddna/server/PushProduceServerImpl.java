package com.lexsus.ariaddna.server;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

/**
 * Created by Lexsus on 01.04.2017.
 */
public class PushProduceServerImpl<E> implements IPushProduce<E> {
  //  @Autowired
    private SharedQueue<E> sharedQueue;
    //    @Autowired
    MessageGenerator<E> generator;
    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(PushProduceServerImpl.class);
    public PushProduceServerImpl(SharedQueue<E> sharedQueue, MessageGenerator<E> generator) {
        this.sharedQueue = sharedQueue;
        this.generator = generator;
    }

    @Override
    public void produce() {
        E mes = generator.generate();
        if (mes!=null)
        {
            try {
                sharedQueue.put(mes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
            LOGGER.debug("generator element==null");
    }
}
