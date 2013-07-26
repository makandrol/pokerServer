import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends JFrame {

    Server() {

        setTitle("Ецци Server");
        setSize(300, 300);

        final JTextArea messages = new JTextArea();
        add(messages);

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JButton newGame = new JButton("Start new game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(newGame, BorderLayout.SOUTH);

        try {
            System.setOut(new PrintStream(System.out, true, "cp866"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ServerSocket serverSocket = new ServerSocket(9222);
            messages.append("Server starting...\n");
            setVisible(true);
            while (true){
                Socket socket = serverSocket.accept();
                messages.append(socket.getInetAddress().toString() + "\n");
                SocketThread socketThread = new SocketThread(socket, messages);
                Thread t = new Thread(socketThread);
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Server();
    }


}
