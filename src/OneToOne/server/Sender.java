package OneToOne.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    Socket socket;
    DataOutputStream output;
    String name;

    public Sender(Socket socket) {
        this.socket = socket;
        try {
            output = new DataOutputStream(socket.getOutputStream());
            name = "["+socket.getInetAddress()+":"+socket.getPort()+"]";
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(output!=null) {
            try {
                output.writeUTF(name+scanner.nextLine());
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    } // run()
}
