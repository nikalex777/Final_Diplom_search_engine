package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Main {
    static final int PORT = 8989;

    public static void main(String[] args) {
        try {
            BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
            try (ServerSocket socket = new ServerSocket(PORT)) {
                while (true) {
                    try (Socket sockets = socket.accept();
                         BufferedReader in = new BufferedReader(new InputStreamReader(sockets.getInputStream()));
                         PrintWriter out = new PrintWriter(sockets.getOutputStream(), true)) {
                        out.println("Соединение установлено, порт" + " " + PORT + " Введите слово для поиска:");
                        String word = in.readLine();
                        List<PageEntry> searchResult = engine.search(word);
                        out.println(listToJson(searchResult));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public static String listToJson(List<PageEntry> list) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(list);
    }
}