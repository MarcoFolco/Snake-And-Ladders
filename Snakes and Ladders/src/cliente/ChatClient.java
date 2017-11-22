package cliente;

import java.net.*;
import java.util.List;
import java.io.*;

public class ChatClient {
	static boolean done = false;
	private static final int PORT = 5322;
	private static final byte[] addr = {(byte) 172, 16, 1, 97};
	private Socket server;
	private String[] tablero;
	private String[] imagenes;
	String carpeta;
	String mensaje;
	private Integer[] jugadores;
	private String[] peones;
	PrintWriter out;
	JFJuego interfaz;
	Integer turno=1;
	//Objeto player;
	//Jugador aux;
	public static Integer n=10;
	public void mostrar()
	{
		Integer l1 = 99, l2 = 90, l3 = 80, l4 = 90;

		for (int i = 0; i < 5; i++) {
			for (int j = l1; j >= l2; j--) {
				System.out.print(tablero[j] + "\t");
			}
			System.out.println();
			for (int j = l3; j < l4; j++) {
				System.out.print(tablero[j] + "\t");
			}
			System.out.println();
			l1 -= 20;
			l2 -= 20;
			l3 -= 20;
			l4 -= 20;
		}
	}
	public void jugar() {
		out.println("jugar");
	}
	public String getMapa()
	{
		return this.carpeta;
	}
	public String[] getImgs()
	{
		return this.imagenes;
	}
	public String[] getPeones()
	{
		return this.peones;
	}
	public void enviarExit()
	{
		out.println("exit");
		this.interfaz.setVisible(false);
		done=true;
	}
	public void enviarServer(String line)
	{
		if(turno==1)
		{
			out.println(line);
		}
	}
	public ChatClient(JFJuego interfaz) {
		try {
			tablero=new String[101];
			jugadores=new Integer[3];
			imagenes=new String[101];
			peones=new String[4];
			this.interfaz=interfaz;
			//j=new Juego();
			//server = new Socket(InetAddress.getByAddress(addr), PORT);
			server = new Socket(InetAddress.getLocalHost(), PORT);
			System.out.println("Escriba 'exit' para salir.");
			Thread escritura = new Thread() {
				public void run() {
					try {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(System.in));
						 out = new PrintWriter(
								server.getOutputStream(), true);
						// boolean done = false;
						while (!done) {
							String line = in.readLine();
							out.println(line);
							if (line == null || line.trim().equals("exit"))
							{
								done = true;
								interfaz.setVisible(false);
						
							}
						}
					} catch (IOException ioe) {
						System.out.println("Error al escribir en el servidor "
								+ ioe);
					}
				}
			};
			Thread lectura = new Thread() {
				public void run() {
					try {
						BufferedReader svrStream = new BufferedReader(
								new InputStreamReader(server.getInputStream()));
						String svrResponse; // boolean done = false;
						do {
							svrResponse = svrStream.readLine();
							
							String[] lineContenido = svrResponse.split(" ");
							if (lineContenido.length != 0
									&& (lineContenido[0].equals("Has") || svrResponse == null)) {
								done = true;
								System.out
										.println("Has salido del juego.");
								interfaz.setVisible(false);
							}
							if (lineContenido.length != 0
									&& (lineContenido[1].equals("exit") && done == false)) {
								System.out.println(lineContenido[0]
										+ "Ha finalizado la comunicaci�n.");
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("estado")&&done==false)){ //Modificado para que detecte el metodo de inicio de juego en mensajeria
								tablero[Integer.parseInt(lineContenido[1])]=lineContenido[2];
								
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("fin")&&done==false))
							{
								interfaz.finalizar(lineContenido[1]);
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("estadoJugador")&&done==false))
							{
								interfaz.setLabelJugadores(Integer.parseInt(lineContenido[1])-12, Integer.parseInt(lineContenido[2]));
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("dado")&&done==false))
							{
								interfaz.setLabelDado(Integer.parseInt(lineContenido[1]));
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("jugador")&&done==false))
							{
								peones[Integer.parseInt(lineContenido[1])]=lineContenido[2];
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("mensaje")&&done==false))
							{
								mensaje=lineContenido[1];
								System.out.println(mensaje);
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("turno")&&done==false)){
								turno=Integer.parseInt(lineContenido[1]);
								interfaz.setLabelTurno(Integer.parseInt(lineContenido[1]));
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("mapa")&&done==false)){
								carpeta=lineContenido[1];
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("ico")&&done==false)){
								imagenes[Integer.parseInt(lineContenido[1])]=lineContenido[2]; 
							}
							if(lineContenido.length!=0&&(lineContenido[0].equals("inter")&&done==false)){
								interfaz.generarGrafico();
							}
							if (!lineContenido[1].equals("exit") && !lineContenido[0].equals("inicio:") && !lineContenido[0].equals("estado") && !lineContenido[0].equals("fin") && !lineContenido[0].equals("estadoJugador") && !lineContenido[0].equals("dado") && !lineContenido[0].equals("jugador") && !lineContenido[0].equals("turno") && !lineContenido[0].equals("mapa") && !lineContenido[0].equals("ico") && !lineContenido[0].equals("inter")
									&& done == false
									&& lineContenido.length != 0)
								System.out.println(svrResponse);
						} while (!done);
					} catch (IOException ioe) {
						System.out.println("Error al escuchar del servidor "
								+ ioe);
					}
				}
			};
			escritura.start();
			lectura.start();
		} catch (IOException ioe) {
			System.out.println("Error al crear el socket " + ioe);
		}
	}
}