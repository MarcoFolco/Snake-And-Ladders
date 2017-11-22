package snake_and_ladders;

import java.net.*;
import java.io.*;

public class ChatServer {
	private static final int PORT = 5322;
	private ServerSocket mServerSocket;

	public ChatServer() {
		System.out.println("Servidor listo");
		Thread daemon = new Thread(new Runnable() {
			public void run() {
				try {
					mServerSocket = new ServerSocket(PORT);
					Mensajeria msj = new Mensajeria();
					while (true) {
						Socket socketDelCliente = mServerSocket.accept();
						msj.addUss(socketDelCliente);
					}
				} catch (IOException e) {
					System.out.println("Error esperando conexiones: " + e);
				}
			}
		});
		daemon.start();
	}

	public static void main(String args[]) {
		ChatServer server = new ChatServer();
	}
}