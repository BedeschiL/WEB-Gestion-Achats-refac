/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeoutException;

public class recv extends Thread {
    private static String QUEUE_NAME = null;

    public recv(String queue) {
        QUEUE_NAME=queue;
    }

    @Override
    public void run()
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            try
            {
                String[] parse = message.split("@");
                Class.forName("oracle.jdbc.OracleDriver");
                String DBurl = "jdbc:oracle:thin:@localhost:1521:xe";
                java.sql.Connection con;
                con = DriverManager.getConnection(DBurl, "SYSDIS", "Gpt");
                String requeteUp = "UPDATE ITEM SET QUANTITE="+parse[3]+", PRIX="+parse[2]+" WHERE ID_ITEM="+parse[0];
                Statement stmt2 = con.createStatement();
                stmt2.executeUpdate(requeteUp);
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }



