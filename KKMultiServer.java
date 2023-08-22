package use;

import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 7777;
        int maxConnections = 3; 
        int currentConnections = 0;
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                if (currentConnections < maxConnections) {
                    Socket clientSocket = serverSocket.accept();
                    new KKMultiServerThread(clientSocket).start();
                    currentConnections++;
                } else {
                    // Caso tenha atingido o limite, recuse a conexão
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Número máximo de conexões atingido. Tente novamente mais tarde.");
                    out.close();
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("Nao foi possivel entrar na porta: " + portNumber);
            System.exit(-1);
        }
    }
}
