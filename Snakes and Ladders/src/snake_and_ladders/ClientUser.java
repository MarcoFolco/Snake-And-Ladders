package snake_and_ladders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientUser extends Thread {
	Socket mClientSocket;
	Mensajeria m;
	PrintWriter clientResponse;
	Jugador j;

	public ClientUser(Socket s,Mensajeria m) {
		mClientSocket = s;
		this.m=m;
	}
	public void enviarChat(String s){
		try {
				clientResponse.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void run() {
		try {
			this.j=new Jugador(1,this.getId(),"ficha.png");
			m.anadirJugador(this);
			clientResponse = new PrintWriter(
					mClientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					mClientSocket.getInputStream()));
			System.out.println("Nuevo cliente conectado (" + this.getId() + ")");
			while (true) {
				String line = in.readLine();
				m.broadcasting(this, line);
				if (!line.trim().equals("exit") && line != null) {
					
					System.out.println("(" + this.getId() + ")" +line);
				}			
				if (line.trim().equals("exit") || line == null) {
					System.out.println("Cliente Desconectado (" + this.getId() + ")");
					break;	
				}
				if(line.trim().equals("jugar"))
				{
					System.out.println("Jugando");
					m.llamarJuego(this);
				}
			}
		} catch (IOException ioe) {
			System.out.println("Error del cliente: " + ioe);
		}
	}
}