package snake_and_ladders;

public class SerpEsc extends Objeto{

	Integer destinoPos;
	Boolean tipo;
	
	public String getTipo()
	{
		if(tipo==true)
		{
			return TipoObjeto.SERPIENTE.getTipo();
		}else
		{
			return TipoObjeto.ESCALERA.getTipo();
		}
	}
	
	public SerpEsc(Integer pos,Integer destinoPos,Boolean tipo,String img)
	{
		super(pos,img);
		this.destinoPos=destinoPos;
		this.tipo=tipo;
	}

	@Override
	public Integer getDest() {
		return this.destinoPos;
	}
}
