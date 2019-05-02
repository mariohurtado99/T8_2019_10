package model.vo;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class VOPuntos 
{
	
	///////////////////////////////////////////
	//Attributes
	///////////////////////////////////////////
	
	private int id;
	
	private double latitude;
	
	private double longitude;
	
	private double distanceFromPoint;
	
	private int puntos;
	
	///////////////////////////////////////////
	//Constructors
	///////////////////////////////////////////
	
	public VOPuntos(String pId, String pLatitude, String pLongitude)
	{
		try
		{
			int vId = 0;
			double vLatitude = 0;
			double vLongitude = 0;	
			vId = Integer.parseInt(pId);
			vLatitude = Double.parseDouble(pLatitude);
			vLongitude = Double.parseDouble(pLongitude);
		
			id = vId;
			latitude = vLatitude;
			longitude = vLongitude;
			distanceFromPoint = 0;
			puntos = 0;
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException i)
		{
//			System.out.println("--------------------EL ERROR ESTA AQUI: " + puntos);
			i.printStackTrace();
		}
		catch(DateTimeException d)
		{
			d.getStackTrace();
		}
	}
	
	///////////////////////////////////////////
	//Methods
	///////////////////////////////////////////
	
	public int getId()
	{
		return id;
	}
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public int getPuntos()
	{
		return puntos;
	}
	
	public void agregarUnPunto()
	{
		puntos++;
	}
	
	public void cambiarPuntos(int newPuntos)
	{
		puntos = newPuntos;
	}
	
	public void setDistanceFrom(double dist)
	{
		distanceFromPoint = dist;
	}
	
	public double getDistanceFrom()
	{
		return distanceFromPoint;
	}
}
