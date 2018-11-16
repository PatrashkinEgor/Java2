package Lesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * Java2. Lesson 6. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 16, 2018
 */

public class Client {


    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket sock;
    private Scanner in;
    private Scanner console;
    private PrintWriter out;

    public Client(){

        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            console  = new Scanner(System.in);
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Возникла ошибка при подключении к серверу");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.hasNext()) {
                            String w = in.nextLine();
                            System.out.println("Server: " + w);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (console.hasNext()) {
                            String w = console.nextLine();

                            out.println(w);
                            out.flush();
                            System.out.println("Client: " + w);
                            if (w.equalsIgnoreCase("end of session")) {
                                System.exit(0);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



}

