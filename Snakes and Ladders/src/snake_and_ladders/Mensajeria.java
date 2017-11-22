package snake_and_ladders;

import java.net.Socket;
import java.util.ArrayList;

import java.io.*;

class Mensajeria {
	ArrayList<ClientUser> clientes = new ArrayList<ClientUser>();
	ArrayList<String> archivo = new ArrayList<String>();
	FileWriter fw;
	PrintWriter pw;
	Juego tablero;

	
	public void addUss(Socket SocketCli) {
		ClientUser cu = new ClientUser(SocketCli, this);
		if (clientes.size() < 2) {
			clientes.add(cu);
			cu.start();
			if (clientes.size() == 2) {
				tablero.informarPrimeraVez();
			} //else
				//cu.enviarChat("mensaje Sincronizando...");
		}
		else
		{
			cu.start();
			cu.enviarChat("mensaje Sala:_LLena");
			
		}
	}

	public void anadirJugador(ClientUser cuu) {
		tablero.jugadores.put(cuu.j.id, cuu.j);

	}

	public Mensajeria() {
		fw = null;
		pw = null;
		this.tablero = new Juego(this);
	}

	public void llamarJuego(ClientUser cu) {
		tablero.jugar(cu);
	}

	private void leerArchivo() {

	}

	public void informarPos(String line) {
		for (ClientUser client : clientes) {
			client.enviarChat(line);
		}
	}

	public void informar(ClientUser clientUser, String line, String line2) {
		for (ClientUser client : clientes) {
			if (client == clientUser)
				client.enviarChat(line);
			else
				client.enviarChat(line2);
		}
	}
	public void informarDado(ClientUser clientUser, String line) {
		for (ClientUser client : clientes) {
			if (client == clientUser)
				client.enviarChat(line);
		}
	}
	public void iniciarTurno()
	{
		int cont=1;
		for (ClientUser client : clientes) {
			if (cont==1)
			{
				client.enviarChat("turno "+cont);
				cont--;
			}else
			{
				client.enviarChat("turno "+cont);
			}
		}
	}
	
	public void cambiarTurno(ClientUser cliU)
	{
		for (ClientUser client : clientes)
		{
			if(client==cliU)
			{
				client.enviarChat("turno 0");
			}else
			{
				client.enviarChat("turno 1");
			}
		}
	}
	public void broadcasting(ClientUser clientUser, String line) {
		// System.out.println("llego: "+line);
		if (line.equals("exit")) {
			clientUser.enviarChat("Has salido del juego");
			// clientUser.enviarChat("exit exit");
			for (ClientUser client : clientes) {
				if (client != clientUser) {
					client.enviarChat("El jugador " + clientUser.getId() + " " + line);
				}
			}
		} else {
			for (ClientUser client : clientes) {
				if (client != clientUser) {
					client.enviarChat("(" + clientUser.getId() + ") " + line);
				} else {
					try {
						fw=new FileWriter("C:/Users/marco/eclipse-workspace/Snakes and Ladders/log.txt");
						//fw = new FileWriter("C:/Users/tep2/workspace/Snakes and Ladders/log.txt");
						pw = new PrintWriter(fw);
						pw.println(clientUser.getId() + "- " + line);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (fw != null)
								fw.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		}
	}
}
