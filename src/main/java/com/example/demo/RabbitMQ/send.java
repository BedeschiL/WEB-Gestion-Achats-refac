/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class send {
    private static String QUEUE_NAME = null;
    private static String Message = null;
    public send(String queue,String msg) {
        QUEUE_NAME=queue;
        Message=msg;
    }

    public static void send()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            channel.basicPublish("", QUEUE_NAME, null, Message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + Message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
