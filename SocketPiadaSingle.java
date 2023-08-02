package use;
import java.net.*;
import java.io.*;
import java.util.Scanner;


public class SocketPiadaSingle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//A seguinte linha cria o ServerSocket, que irá esperar conexões na porta 12345 (caso esta porta já esteja em uso, uma exceção será lançada)
			ServerSocket server = new ServerSocket(1788);
			
			System.out.println("Servidor iniciado na porta 1788");
			//Em seguida criamos um objeto Socket, o qual irá tratar da comunicação com o cliente, assim que um pedido de conexão chegar ao servidor e a conexão for aceita:
			while(true) {
				Socket client = server.accept();
				
				//abre uma conexao com o cliente  para enviar dados
		        PrintWriter saida = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"), true);
		        
		        //manda a mensagem como um objeto
		        String mensagem = "Knock! Knock!";
		        saida.println(mensagem);
		        //limpa a mensagem do writeObject
		        
		      
	            
	          //abre uma conexao com o cliente  para receber dados
	            BufferedReader inputReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
	            
	            String msg;
	            msg = inputReader.readLine();
	            System.out.println(""+ msg);
	            
	            
	            String mensagem2 = "KGB!!!";
	            //Envia os dados novamente
	            saida.println(mensagem2);
	            
	            String msg2;
	            msg = inputReader.readLine();
	            
	            String mensagem3 = "Slap! Nos Fazemos as perguntas!";
	            //Envia os dados novamente
	            saida.println(mensagem3);
	            
	            saida.flush();
		        saida.close();
		        client.close();
				
			}
			
			
		}
		catch(IOException e){
			System.out.printf("ERROR!"  + e.getMessage());
		}
		
	}

}
