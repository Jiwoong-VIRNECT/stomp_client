package com.stomp.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class TestApplication {

    private static String URL = "ws://localhost:8080/spring-mvc-java/chat";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TestApplication.class, args);

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect(URL, sessionHandler);

        byte[] imageInByte;
        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\jiwoo\\Desktop\\test.png"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();

        imageInByte = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(imageInByte));
        byteArrayOutputStream.close();

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }

}
