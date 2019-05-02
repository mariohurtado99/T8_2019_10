package view;

import java.util.Scanner;

import com.teamdev.jxmaps.MapViewOptions;

import controller.Controller;
import maps.Mapping;
import model.data_structures.Graph;
import model.vo.InfoVertex;

public class View 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		boolean finished = false;
		
		while(!finished)
		{
			printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 1:
					long memoryBeforeCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					long startTime = System.currentTimeMillis();
					
					Controller.cargarInfo();
					long endTime = System.currentTimeMillis();
					long duracion = endTime - startTime;
					
					long memoryAfterCase1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					System.out.println("Tiempo en cargar: " + duracion + " milisegundos \nMemoria utilizada:  "+ ((memoryAfterCase1 - memoryBeforeCase1)/1000000.0) + " MB");
					break;
					
				case 2:
					
					loadMap(graph());
					System.out.println("Mapa Cargado.");
					break;
					
				case 3:
					Controller.generarJSON();
					break;
					
				case 4:
					finished = true;
					
					System.out.println("Adios!");
					sc.close();
					break;
			}
		}
	}
	
	public static void loadMap(Graph<Integer, InfoVertex, Double> graph)
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("Key de Google");
		Mapping map = new Mapping(options);
		map.map("Mapa", graph);
	}
	
	public static Graph<Integer, InfoVertex, Double> graph()
	{
		return Controller.getGraph();
	}
	
	private static void printMenu()
	{
		System.out.println("---------ISIS 1206 - Estructuras de Datos----------");
		System.out.println("-------------------- Taller 8 - 2019-1 ----------------------");
		System.out.println("1. Cargar informacion (Grafo JSON y estaciones)");
		System.out.println("2. Cargar Mapa.");
		System.out.println("3. Generar JSON");
		System.out.println("4. Salir");
	}
}
