package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer07_topics_email {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
    private static final String ROUTING_EMAIL = "inform.#.email.#";

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

            channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_TOPICS_INFORM,ROUTING_EMAIL);

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
            channel.basicConsume(QUEUE_INFORM_EMAIL,true,defaultConsumer);
//            channel.basicPublish("",QUEUE,null,msg.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }
}
