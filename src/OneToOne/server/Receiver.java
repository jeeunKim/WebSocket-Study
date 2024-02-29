package OneToOne.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
    Socket socket;
    DataInputStream input;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            input = new DataInputStream(socket.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while(input!=null) {
            try {
                System.out.println(input.readUTF());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    } // run
}
