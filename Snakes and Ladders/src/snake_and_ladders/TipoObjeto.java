package snake_and_ladders;

public enum TipoObjeto {
	JUGADOR("ju"),
	SERPIENTE("ser"),
	ESCALERA("esc"),
	NULO("null");
	
	private String nom;
	TipoObjeto(String s)
	{
		this.nom=s;
	}
	
	String getTipo()
	{
		return this.nom;
	}
}
