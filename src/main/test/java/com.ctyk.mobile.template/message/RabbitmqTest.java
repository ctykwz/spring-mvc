package com.ctyk.mobile.template.message;

import com.ctyk.mobile.template.model.request.message.MessageModel;
import com.ctyk.mobile.template.module.rabbitmq.MessagePushWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wei.yang on 2017/11/6
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:spring/spring-root.xml"
})
public class RabbitmqTest {

    @Autowired
    private MessagePushWorker pushWorker;

    @Test
    public void send() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            MessageModel messageModel = new MessageModel();
            messageModel.setContent(String.format("msg body:%s\t", i));
            messageModel.setTitle(String.format("msg title:%s\t", i));
            pushWorker.send(messageModel);
        }
        Thread.sleep(1000);
    }
}
