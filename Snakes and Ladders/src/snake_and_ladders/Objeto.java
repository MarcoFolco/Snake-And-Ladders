package snake_and_ladders;

public abstract class Objeto {

	private Integer pos;
	
	private String img;
	
	public abstract String getTipo();
	
	public abstract Integer getDest();
	
	public Objeto(Integer pos,String img)
	{
		this.pos=pos;
		this.img=img;
	}
	public Integer getPos()
	{
		return this.pos;
	}
	public String getImg()
	{
		return this.img;
	}
	public void setPos(Integer pos)
	{
		this.pos=pos;
	}
}
