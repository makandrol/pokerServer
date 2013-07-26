import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey
 * Date: 26.06.13
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public class SocketThread implements Runnable {

    private JTextArea messages;
    private Socket playerSocket;
    private String playerName;
    private Scanner in;
    private PrintWriter out;
    private boolean exit = true;
    private String inMessage;

    public SocketThread(Socket playerSocket, JTextArea messages) {
        this.playerSocket = playerSocket;
        this.messages = messages;
    }

    @Override
    public void run() {
        try {
            in = new Scanner(playerSocket.getInputStream());
            out = new PrintWriter(playerSocket.getOutputStream());

            out.println("What is your name?");
            out.flush();

            boolean isName = true;
            while (isName) {
                playerName = in.nextLine();
                isName = false;
            }
            if (playerName.equals("exit"))
                return;

            //with first commit
            out = new PrintWriter(playerSocket.getOutputStream());
            out.println("Server: hello, " + playerName);
            out.flush();

            messages.append(playerName + " connect...\n");

            out = new PrintWriter(playerSocket.getOutputStream());

            while (exit) {
                inMessage = in.nextLine();
                System.out.println(inMessage);
                String copy = "";
                inMessage = inMessage.trim();
                boolean whisper = false;
                boolean send = false;

                if (inMessage.equals("exit")) {
                    exit = false;
                }
            }

        } catch (IOException ex) {
            try {
                playerSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

