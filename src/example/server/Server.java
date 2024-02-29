package example.server;

import java.net.*;

public class Server {
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(8082);
            System.out.println("서버가 준비되었습니다");

            socket = serverSocket.accept();
            System.out.println("[서버의 채팅창]");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main
} // class
