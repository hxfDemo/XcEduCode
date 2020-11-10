package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {
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
       String msg="hello word 黑马 程序员";
            channel.basicPublish("",QUEUE,null,msg.getBytes());
            System.out.println("send to mq:"+msg);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (channel!=null){
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }
        }

    }
}
