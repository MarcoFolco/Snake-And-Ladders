package snake_and_ladders;

public class Jugador extends Objeto{

	long id;
	//ChatClient c=null;

	public long getId() {
		return id;
	}
	public String getTipo()
	{
		return TipoObjeto.JUGADOR.getTipo();
	}
	
	public Jugador(Integer pos,Long id,String img)
	{
		super(pos,img);
		this.id=id;
	}

	@Override
	public Integer getDest() {
		return null;
	}
}
