package controller;

import model.data_structures.Graph;
import model.logic.Manager;
import model.vo.InfoVertex;

public class Controller
{
	private static Manager manager = new Manager();
	
	
	public static void cargarSinJSON()
	{
		manager.loadInfo();
	}
	
	public static void generarJSON()
	{
		manager.generarJSON();
	}
	
	public static Graph<Integer, InfoVertex, Double> getGraph()
	{
		return manager.grafo();
	}
	
	public static void cargarInfo()
	{
		manager.cargarInfo();
	}
}
