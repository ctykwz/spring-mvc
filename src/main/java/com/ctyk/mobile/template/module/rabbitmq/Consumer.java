package com.ctyk.mobile.template.module.rabbitmq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wei.yang on 2017/11/6
 */
@Component
public class Consumer implements Closeable {

    private static final Logger logger = Logger.getLogger(Consumer.class);

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();

    private ExecutorService executors = new ThreadPoolExecutor(10,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024),
            threadFactory);

    private AtomicInteger count = new AtomicInteger(0);

    public void onMessage(Object object) {
        executors.submit(new HandlerWithJson(object));
    }

    @Override
    public void close() throws IOException {
        executors.shutdown();
    }

    private class HandlerWithJson implements Runnable {

        private Object message;

        HandlerWithJson(Object message) {
            this.message = message;
        }

        @Override
        public void run() {
            logger.info(String.format("msg:%s\t", count.incrementAndGet()) + message);
        }
    }
}
