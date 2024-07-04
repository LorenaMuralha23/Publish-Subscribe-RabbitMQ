package com.mycompany.emitlogs;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogs {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try ( Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            
            String message = "[SERVER SAYS] And after wall, you're my wonder wall! - Oasis, 1995";
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

            System.out.println("[SERVER STATUS] Message Sended!");
        }
    }

}
