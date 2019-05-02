package model.vo;

public class InfoVertex
{
	private double latitud;
	private double longitud;
	private boolean draw;
	private int id;
	
	public InfoVertex(int pId, double pLatitud, double pLongitud, VOPuntos pEstacion)
	{
		id = pId;
		latitud = pLatitud;
		longitud = pLongitud;
		draw = false;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int pId)
	{
		id = pId;
	}
	
	public boolean isDrawn()
	{
		return draw;
	}
	
	public void drawIt()
	{
		draw = true;
	}
	
	public double getLatitud()
	{
		return latitud;
	}
	
	public double getLongitud()
	{
		return longitud;
	}
}
