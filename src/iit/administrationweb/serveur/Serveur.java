package iit.administrationweb.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void create() {
		try {
			ServerSocket serverSocket = new ServerSocket(8000);
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					ThreadedEchoServer serveurThread = new ThreadedEchoServer(
							socket);
					serveurThread.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
