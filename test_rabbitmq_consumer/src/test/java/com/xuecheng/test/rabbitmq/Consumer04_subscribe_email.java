package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer04_subscribe_email {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ConnectionFactory factory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
//            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);

            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,"");

            DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    envelope.getDeliveryTag();
                    envelope.getExchange();
                    envelope.getRoutingKey();
                    String msg = new java.lang.String(body, "utf-8");
                    System.out.println(msg);
                }
            };
            channel.basicConsume(QUEUE_INFORM_EMAIL,true,defaultConsumer);
//            channel.basicPublish("",QUEUE,null,msg.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
