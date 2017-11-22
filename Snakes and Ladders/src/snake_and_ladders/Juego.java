package snake_and_ladders;

import java.util.*;
import java.io.*;

public class Juego {

	Map<Integer,Objeto> tablero;
	
	Map<Long,Jugador> jugadores; //id->jugador
	
	String[] peones;
	
	Mensajeria m;
	
	String map;
	
	String j1,j2;
	
	public Juego(Mensajeria m) //Genero el tablero y obtengo la clase mensajeria para poder indicar el cambio de tablero
	{
		this.tablero=new HashMap<Integer,Objeto>();
		this.m=m;
		this.jugadores=new HashMap<Long,Jugador>();
		this.peones=new String[4];
		//this.valorDado=0;
		generarTablero();
	}
	public Juego()
	{
		this.tablero=new HashMap<Integer,Objeto>();
		this.m=null;
		this.jugadores=new HashMap<Long,Jugador>();
		peones=new String[4];
		//this.valorDado=0;
		generarTablero();
	}
	private Integer tirarDados()
	{
		Random rnd=new Random();
		return (int) (rnd.nextDouble()*6+1);
	}
	public void generarTablero()
	{
		FileReader fr=null;
		BufferedReader br=null;
		File mapa=new File("C:/Users/marco/eclipse-workspace/Snakes and Ladders/mapas/Mapa1.map");
		//File mapa=new File("C:/Users/tep2/workspace/Snakes and Ladders/mapas/Mapa1.map");
		System.out.println("Generando tablero");
		try
		{
			fr=new FileReader(mapa);
			br=new BufferedReader(fr);
			String leido;
			String[] linea;
			while((leido=br.readLine())!=null)
			{
				linea=leido.split("-");
				//System.out.println(linea[0]);
				//System.out.println(linea[1]);
				if(linea[0].equals("mapa"))
				{
					map=linea[1];
				}
				if(linea[0].equals("jugador")) 
				{
					peones[Integer.parseInt(linea[1])]=linea[2];
				}
				if(linea[1].equals("N"))
				{
					Nulo n=new Nulo(Integer.parseInt(linea[0]),linea[2]);
					tablero.put(Integer.parseInt(linea[0]), n);
				}else
				if(linea[1].equals("E"))
				{
					SerpEsc e=new SerpEsc(Integer.parseInt(linea[0]),Integer.parseInt(linea[2]),false,linea[3]);
					tablero.put(Integer.parseInt(linea[0]), e);
				}
				if(linea[1].equals("S"))
				{
					SerpEsc s=new SerpEsc(Integer.parseInt(linea[0]),Integer.parseInt(linea[2]),true,linea[3]);
					tablero.put(Integer.parseInt(linea[0]), s);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			/*for(Objeto j:tablero.values())
			{
				System.out.println(j.getTipo());
			}*/
			try
			{
				if(fr!=null)
					fr.close();
			}
			catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	private Integer posicionar(Integer pos)
	{
		if(tablero.get(pos).getTipo().equals(TipoObjeto.NULO.getTipo()))
			return pos;
		return posicionar(tablero.get(pos).getDest());
	}
	
	public void jugar(ClientUser Client)
	{
		Jugador jActual = jugadores.get(Client.getId());
		Integer valorDado = tirarDados();
		Integer posicionOrig=jActual.getPos();
		Integer posicion=posicionOrig+valorDado; //Sumo a la posicion lo que salio en el dado
		
		if(posicion>100)
		{
			posicion=posicion-(posicion-100);
		}
		jActual.setPos(posicionar(posicion));
		System.out.println("posicion: "+jActual.getPos());
		String line=null; //CODIFICAR
		if(jActual.getPos()==100)
		{
			m.informar(Client, "fin ganaste", "fin perdiste");
		}
		else
		{
			line="estadoJugador "+jActual.getId()+" "+jActual.getPos();
			m.informarPos(line);
			line="estado "+ posicionOrig +" "+tablero.get(posicionOrig).getTipo();
			m.informarPos(line);
			line="dado "+valorDado;
			m.informarDado(Client, line);
			m.cambiarTurno(Client);
		}
	}
	public void informarPrimeraVez()
	{
		int cont=0;
		Collection<Objeto> s = tablero.values();
		Collection<Jugador> ju = jugadores.values();
		for (int i = 1; i < peones.length; i++) {
			m.informarPos("jugador "+i+" "+peones[i]);
		}
		for (Objeto o : s) {
			m.informarPos("estado "+o.getPos()+" "+o.getTipo());
			m.informarPos("ico "+o.getPos()+" "+o.getImg());
		}
		m.informarPos("mapa "+map);
		m.iniciarTurno();
		m.informarPos("inter faz");
		for (Jugador j : ju) {
			m.informarPos("estadoJugador "+j.getId()+" "+j.getPos());
		}
		
	}
}
