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

public class Server {

    ServerSocket serv = null;
    Socket sock = null;
    private Scanner in;
    private Scanner console;
    PrintWriter pw;

    public Server(){
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            in = new Scanner(sock.getInputStream());
            console  = new Scanner(System.in);
            pw = new PrintWriter(sock.getOutputStream());

        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.hasNext()) {
                            String w = in.nextLine();
                            System.out.println("Client: " + w);
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
                            pw.println(w);
                            pw.flush();
                            System.out.println("Server: " + w);
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

    static void closeServ(ServerSocket serv){
        try {
            serv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}