package snake_and_ladders;

public class Nulo extends Objeto{

	public String getTipo()
	{
		return TipoObjeto.NULO.getTipo();
	}
	public Nulo(Integer pos,String img)
	{
		super(pos,img);
	}
	@Override
	public Integer getDest() {
		return null;
	}
}
