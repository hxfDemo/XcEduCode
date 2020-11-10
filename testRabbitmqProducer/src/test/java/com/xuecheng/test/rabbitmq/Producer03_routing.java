package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer03_routing {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_ROUTING_INFORM = "exchange_routing_inform";
    private static final String ROUTING_EMAIL = "routing_email";
    private static final String ROUTING_SMS = "routing_sms";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);

            channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, ROUTING_EMAIL);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, "inform");
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, ROUTING_SMS);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, "inform");

            /*            for (int i = 0; i < 5; i++) {
                String message = "send Email message to user" + "第" + i + "次";
                channel.basicPublish(EXCHANGE_TOPICS_INFORM, ROUTING_EMAIL, null, message.getBytes());
            }
            for (int i = 0; i < 5; i++) {
                String message = "send Sms message to user" + "第" + i + "次";
                channel.basicPublish(EXCHANGE_TOPICS_INFORM, ROUTING_SMS, null, message.getBytes());
            }*/for (int i = 0; i < 5; i++) {
                String message = "send Email message to user" + "第" + i + "次";
                channel.basicPublish(EXCHANGE_ROUTING_INFORM, "inform", null, message.getBytes());
            }
            for (int i = 0; i < 5; i++) {
                String message = "send Sms message to user" + "第" + i + "次";
                channel.basicPublish(EXCHANGE_ROUTING_INFORM, "inform", null, message.getBytes());
            }
//            channel.basicPublish("",QUEUE,null,msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
