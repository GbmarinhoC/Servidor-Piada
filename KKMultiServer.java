package use;

import java.net.*;
import java.io.*;

public class KKMultiServer {
    public static void main(String[] args) throws IOException {

    
    	//setando nossa porta
        int portNumber = 7777;
        boolean listening = true;
        //Nao estava rodando pelo terminal porque eu estava dentro do use
        //para isso fique no src e de use.Nome
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
            	// fica nesse while esperando clientes
	            new KKMultiServerThread(serverSocket.accept()).start();
	        }
	    } catch (IOException e) {
            System.err.println("Nao foi possivel entrar na porta: " + portNumber);
            System.exit(-1);
        }
    }
}
