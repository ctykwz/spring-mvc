package com.ctyk.mobile.template.module.rabbitmq;

import com.ctyk.mobile.template.model.request.message.MessageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wei.yang on 2017/10/23 10:15.
 */
@Component
public class MessagePushWorker {

    private static final String QUEUE_KEY = "wei.queue.test";

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessagePushWorker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 消息发送
     *
     * @param messageModel 消息
     */
    public void send(MessageModel messageModel) {
        rabbitTemplate.convertAndSend(QUEUE_KEY, messageModel);
    }
}
