package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static final int PORT = 8989;
    static final String HOST = "localhost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String questionFromServer = in.readLine();
            System.out.println(questionFromServer);
            String answerToServer = scanner.nextLine();
            out.println(answerToServer);
            String jsonFromServer = in.readLine();
            System.out.println(prettyJson(jsonFromServer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String prettyJson(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        return gson.toJson(jsonElement);
    }
}