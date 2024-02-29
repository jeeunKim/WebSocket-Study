import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultipleChatC extends Frame implements ActionListener {

    TextArea display;
    TextField text;
    Label lword;
    BufferedWriter output;
    BufferedReader input;
    Socket client;
    String clientData = "";

    String serverData = "";

    public MultipleChatC() {
        super("클라이언트");
        display = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
        display.setEditable(false);
        add(display, BorderLayout.CENTER);

        Panel pword = new Panel(new BorderLayout());
        lword = new Label("대화말");
        text = new TextField(30);
        text.addActionListener(this);

        pword.add(lword, BorderLayout.WEST);
        pword.add(text, BorderLayout.EAST);
        add(pword, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    // 클라이언트 종료 시 소켓을 닫음
                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        setSize(300, 150);
        setVisible(true);
    }

    public void runClient(){
        try {
            client = new Socket(InetAddress.getByName("192.168.50.176"), 8096);
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            while (true) {
                serverData = input.readLine();
                if (serverData != null) {
                    display.append("\r\n" + serverData);
                } else {
                    // 서버에서 더 이상 메시지를 받지 않으면 대기 상태로 전환
                    Thread.sleep(1000); // 1초 동안 대기
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close(); // 소켓 닫기
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        clientData = text.getText();
        try {
            // 사용자가 입력한 메시지를 서버로 전송
            output.write(clientData + "\r\n");
            output.flush();
            text.setText(""); // 입력 필드 초기화
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
