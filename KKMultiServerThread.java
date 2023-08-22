package use;

import java.net.*;
import java.io.*;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;

    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
    
    public void run() {
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            int verifica = 0;
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while (true) {
                // Configura o tempo de espera para 5 segundos (5000 milissegundos)
                socket.setSoTimeout(8000);
                
                String inputLine = null;
                try {
                    inputLine = in.readLine();
                } catch (SocketTimeoutException timeoutException) {
                	if(verifica < 2 ) {
                		out.println("Tem como você responder? estou tentando fazer a porra de uma piada!!");
                		verifica++;
                	}else {
                		out.println("Parece que voce não quer ouvir uma piada :(");
                		out.println("Vamos deixar outro entrar para ouvir então.");
                		socket.close();
                	}
                    continue;
                }
                
                if (inputLine == null) {
                    // Cliente fechou a conexão
                    break;
                }
                
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                
                if (outputLine.equals("Tchau.")) {
                    socket.close();
                    break;
                }
            }
            
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
