package iit.administrationweb.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadedEchoServer extends Thread {

	private Socket socket;
	public static final String webFolder = "/home/dali/serveur";
	PrintWriter printWriter;

	public ThreadedEchoServer(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {

		super.run();
		try {
			/*lecture de l'input du serveur (venant du client)*/
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			
			String input = reader.readLine();
			String[] intupDecoup=input.split(" ");
			input=intupDecoup[1];
			File f =new File(webFolder+input);
			printWriter = new PrintWriter(socket.getOutputStream());
			if(f.exists()){
				printWriter.print("HTTP/1.0 200 OK");
				printWriter.println("Content-Type : text/html");
				printWriter.println();
				
				String chaine="";
				
				//lecture du fichier texte	
				try{
					InputStream ips=new FileInputStream(f); 
					InputStreamReader ipsr=new InputStreamReader(ips);
					BufferedReader br=new BufferedReader(ipsr);
					String ligne;
					while ((ligne=br.readLine())!=null){
						System.out.println(ligne);
						chaine+=ligne+"\n";
					}
					br.close(); 
				}		
				catch (Exception e){
					System.out.println(e.toString());
				}
				
				printWriter.println(chaine);
				
			}
			
			
			printWriter.flush();
			socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
