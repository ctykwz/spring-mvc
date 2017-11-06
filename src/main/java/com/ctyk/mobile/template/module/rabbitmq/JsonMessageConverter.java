package com.ctyk.mobile.template.module.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.UnsupportedEncodingException;

/**
 * @author wei.yang on 2017/11/6
 */
public class JsonMessageConverter extends AbstractMessageConverter {

    private static final Logger logger = Logger.getLogger(JsonMessageConverter.class);

    private static ClassMapper classMapper = new DefaultClassMapper();

    private static final String DEFAULT_CHART_SET = "UTF-8";

    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        byte[] body;
        String msg = JSONObject.toJSONString(object);
        try {
            body = msg.getBytes(DEFAULT_CHART_SET);

        } catch (UnsupportedEncodingException e) {
            body = new byte[0];
            logger.info("msg converter failed.");
        }
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        messageProperties.setContentEncoding(DEFAULT_CHART_SET);
        messageProperties.setContentLength(body.length);
        classMapper.fromClass(object.getClass(), messageProperties);
        return new Message(body, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        MessageProperties properties = message.getMessageProperties();
        String contentType = properties.getContentType();
        String chartSet = properties.getContentEncoding();
        Object msg = null;
        if (MessageProperties.CONTENT_TYPE_JSON.equals(contentType)) {
            if (Strings.isNullOrEmpty(chartSet)) {
                chartSet = DEFAULT_CHART_SET;
            }
            Class<?> aClass = classMapper.toClass(properties);
            byte[] body = message.getBody();
            logger.info("msg len:\t" + body.length);
            try {
                msg = JSONObject.parseObject(new String(body, chartSet), aClass);
                logger.info("msg \t" + msg);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
