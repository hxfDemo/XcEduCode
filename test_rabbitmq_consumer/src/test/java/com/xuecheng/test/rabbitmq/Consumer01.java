package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer01 {
    private static final String QUEUE = "helloword";

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
            channel.queueDeclare(QUEUE, true, false, false, null);
            DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    envelope.getDeliveryTag();
                    envelope.getExchange();
                    envelope.getRoutingKey();
                    String msg = new String(body, "utf-8");
                    System.out.println(msg);
                }
            };


            channel.basicConsume(QUEUE,true,defaultConsumer);
//            System.out.println("send to mq:"+msg);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

    }
}
