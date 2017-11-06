package com.ctyk.mobile.template.module.rabbitmq;

import com.ctyk.mobile.template.model.request.message.MessageModel;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wei.yang on 2017/11/6
 */
@Component
public class MessageConsumer implements MessageListener, Closeable {

    private static final Logger logger = Logger.getLogger(MessageConsumer.class);

    private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();

    private ExecutorService executors = new ThreadPoolExecutor(10,
            20,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024),
            threadFactory);

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void onMessage(Message message) {
        executors.submit(new Handler(message));
    }

    @Override
    public void close() throws IOException {
        executors.shutdown();
    }

    private class Handler implements Runnable {

        private Message message;

        Handler(Message message) {
            this.message = message;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(message.getBody()));
                MessageModel messageModel = (MessageModel) inputStream.readObject();
                logger.info(String.format("msg:%s\t%s\t", count.incrementAndGet(), Thread.currentThread().getName()) +
                        messageModel);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
