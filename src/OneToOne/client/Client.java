package OneToOne.client;

import OneToOne.server.Sender;
import OneToOne.server.Receiver;
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        Socket socket = null;
        try {
            String serverIp = "192.168.50.176";
            socket = new Socket(serverIp, 8082);

            System.out.println("서버에 연결되었습니다");
            System.out.println("[클라이언의 채팅창]");

            Sender sender = new Sender(socket);
            Receiver receiver = new Receiver(socket);

            sender.start();
            receiver.start();
        }
        catch(ConnectException ce) {
            ce.printStackTrace();
        }
        catch(IOException ie) {
            ie.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    } // main
}
