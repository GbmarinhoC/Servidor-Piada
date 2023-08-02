package use;

import java.net.*;
import java.io.*;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    //Na hora da Criação criamos um method de criação
    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
    
    public void run() {
    	
        try (
        	//Foi criado um PrintWriter para mandar mensagem para o cliente
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        		
        	//Foi criado um jeito de ler oque é enviado para o cliente
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            // criado para administrar as mensagens que sao enviadas e recebidas
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            
            //é retornado oque deve ser escrito na tela do cliente dependendo de que parte do processo ele estiver
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            //Fica fazendo as piadas e perguntas ate que o output retornado do KnockKnock 
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Tchau."))
                	socket.close();
            }
            //vai fechar a conexao do cliente com servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}