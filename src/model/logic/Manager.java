package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import api.IManager;
import model.data_structures.Graph;
import model.haversine.Haversine;
import model.vo.InfoVertex;
import model.vo.VOPuntos;
import model.data_structures.Node;;
public class Manager implements IManager
{
	///////////////////////////////////
	// Constantes
	///////////////////////////////////

	/**
	 * Ruta del archivo de los nodos y sus coordenadas.
	 */
	public static final String NODOS_WASHINGTON = "./data/Central-WashingtonDC-OpenStreetMap.xml";

	/**
	 * Ruta del archivo JSON de donde se va a cargar el grafo
	 */
	public static final String DATA = "./data/data.json";

	///////////////////////////////////
	// Atributos
	///////////////////////////////////

	private Graph<Integer, InfoVertex, Double> graph;

	///////////////////////////////////
	// Metodos
	///////////////////////////////////

	public Manager()
	{
		graph = new Graph<Integer, InfoVertex, Double>();
	}

	public Graph<Integer, InfoVertex, Double> grafo()
	{
		return graph;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void cargarInfo()
	{
		JSONParser parser = new JSONParser();
		try
		{
			Object mainObj = parser.parse(new FileReader(DATA));
			JSONObject jsonObject = (JSONObject) mainObj;
			JSONArray vertices = (JSONArray) jsonObject.get("vs");
			Iterator<JSONObject> verticesIterator = (Iterator<JSONObject>) vertices.iterator();
			while(verticesIterator.hasNext())
			{
				JSONObject v1 = (JSONObject) verticesIterator.next();
				VOPuntos puntos = null;
				boolean es = (boolean) v1.get("esEst");
				if(es)
				{
					JSONObject est = (JSONObject) v1.get("est");
					String id = ((Long) est.get("id")).toString();
					String lat = ((Double) est.get("lat")).toString();
					String longitud = ((Double) est.get("long")).toString();
					puntos = new VOPuntos(id, lat, longitud);
				}

				Integer id = new Integer(((Long) v1.get("id")).intValue());

				double lat = (Double) v1.get("lat");
				double lon = (Double) v1.get("long");

				InfoVertex info = new InfoVertex(id, lat, lon, puntos);
				graph.addVertex(id, info);
			}

			//Vertices inicializados
			//Ahora a inicializar los arcos
			JSONArray arcs = (JSONArray) jsonObject.get("arcs");
			Iterator<JSONObject> arcosIterator = (Iterator<JSONObject>) arcs.iterator();
			while(arcosIterator.hasNext())
			{
				JSONObject a1 = (JSONObject) arcosIterator.next();
				Integer idPrincipal = ((Long) a1.get("id")).intValue();
				JSONArray adjs = (JSONArray) a1.get("adj");
				Iterator<JSONObject> adjsIterator = (Iterator<JSONObject>) adjs.iterator();
				while(adjsIterator.hasNext())
				{
					JSONObject obj = adjsIterator.next();
					graph.addEdge(idPrincipal, ((Long)obj.get("id")).intValue(), Double.parseDouble((String) obj.get("dis")));
				}
			}
		}
		catch(ParseException e)
		{
			System.out.println("parser");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String reporteVerticesInterseccion()
	{
		return null;
	}

	@Override
	public String reporteVerticesEstacion()
	{
		return null;
	}

	public void cargarInfoSinJSON()
	{
		graph = new Graph<Integer, InfoVertex, Double>();
		try
		{
			BufferedReader lector = new BufferedReader(new FileReader(NODOS_WASHINGTON));
			String linea = lector.readLine();
			while(linea != null)
			{
				Integer idInter = 0;
				Double longInter = 0.0;
				Double latInter = 0.0;
				String[] array = linea.split(",");
				VOPuntos p = new VOPuntos("", "", "");
				if(!array[0].equals(""))
				{
					idInter = Integer.parseInt(array[0]);
					longInter = Double.parseDouble(array[1]);
					latInter = Double.parseDouble(array[2]);

				}
				InfoVertex info = new InfoVertex(idInter, latInter, longInter, p);
				graph.addVertex(info.getId(), info);
				linea = lector.readLine();
			}
			System.out.println("Numero de vertices: " + graph.V());

			//Ya todos los vertices fueron agregados
			//Ahora se cargan los arcos de archivo
			//			System.out.println("Número de arcos entre vertices interseccion: " + cont);

			//Ya se cargaron los arcos

			lector.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void loadInfo()
	{
		graph = new Graph<Integer, InfoVertex, Double>();
		try
		{
			BufferedReader lector = new BufferedReader(new FileReader(NODOS_WASHINGTON));
			String linea = lector.readLine();
			VOPuntos p = new VOPuntos("", "", "");
			while(linea != null)
			{
				Integer idInter = 0;
				Double longInter = 0.0;
				Double latInter = 0.0;
				String[] array = linea.split(",");
				if(!array[0].equals(""))
				{
					idInter = Integer.parseInt(array[0]);
					longInter = Double.parseDouble(array[1]);
					latInter = Double.parseDouble(array[2]);
				}
				InfoVertex info = new InfoVertex((idInter), latInter, longInter, p);
				graph.addVertex(info.getId(), info);
				linea = lector.readLine();
			}
			lector.close();
			System.out.println(graph.V());

			System.out.println(graph.V());

			for(Integer k : graph.getAdj())
			{
				InfoVertex nuevo = graph.getInfoVertex(k);
				edges(nuevo, graph);

			}
			System.out.println(graph.E());
			lector.close();
			System.out.println(graph.E());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void cargarEstacion(BufferedReader lector)
	{
		try
		{
			int cont = 0;
			String linea = lector.readLine();
			linea = lector.readLine();

			while(linea != null)
			{
				String[] partes = linea.split(",");

				VOPuntos puntos2 = new VOPuntos("", "", "");
				InfoVertex info = new InfoVertex(puntos2.getId(), puntos2.getLatitude(), puntos2.getLongitude(), puntos2);
				graph.addVertex(puntos2.getId(), info);

				int id = 0;
				double distance = Double.MAX_VALUE;
				for(Integer k: graph.getAdj())
				{
					Node<Integer, InfoVertex, Double> node = graph.getAdj().get(k);
					double pDistance = Haversine.distance(node.value().getLatitud(), node.value().getLongitud(), puntos2.getLatitude(), puntos2.getLongitude());
					id = k;
					distance = pDistance;
				}
			}

			graph.addEdge(id, puntos2.getId(), distance);
			cont++;

			linea = lector.readLine();
		
		System.out.println("Numero de arcos: " + cont);
	}
		catch (Exception e) {
			e.getMessage();}
		}



	public void nodes(BufferedReader lector)
	{
		try
		{
			//			int cont = 0;
			String linea = lector.readLine();
			linea = lector.readLine();

			while(linea != null)
			{
				String[] partes = linea.split(",");

				VOPuntos puntos3 = new VOPuntos("", "", "");
				InfoVertex info = new InfoVertex(puntos3.getId()+321375, puntos3.getLatitude(), puntos3.getLongitude(), puntos3);
				graph.addVertex(info.getId(), info);
				//				cont++;

				linea = lector.readLine();
			}
			lector.close();
			//			System.out.println("Numero de arcos: " + cont);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void edges(InfoVertex station, Graph<Integer, InfoVertex, Double> graph)
	{
		int id = 0;
		double distance = Double.MAX_VALUE;
		for(Integer k: graph.getAdj())
		{
			Node<Integer, InfoVertex, Double> node = graph.getAdj().get(k);

			double pDistance = Haversine.distance(node.value().getLatitud(), node.value().getLongitud(), station.getLatitud(), station.getLongitud());

			id = k;
			distance = pDistance;

		}
	}

	graph.addEdge(id, puntos3.getId(), distance);
	//		System.out.println("Arco hacia: " + puntos3.getId() + " desde: " + id);



@SuppressWarnings("unchecked")
public void generarJSON()
{
	Object[] vertexs = graph.getAdj().getValues();
	JSONObject JSONgraph = new JSONObject();
	JSONArray vertices = new JSONArray();
	JSONArray arcos = new JSONArray();
	for(int i = 0; i < vertexs.length; i++)
	{
		Node<Integer, InfoVertex, Double> node = (Node<Integer, InfoVertex, Double>) vertexs[i];
		if(node != null)
		{
			JSONObject v1 = new JSONObject();
			v1.put("id", node.value().getId());
			v1.put("lat", node.value().getLatitud());
			v1.put("long", node.value().getLongitud());
			JSONObject obj = new JSONObject();
			obj.put("id", node.value().getId());
			obj.put("lat", node.value().getLatitud());
			obj.put("long", node.value().getLongitud());
			v1.put("est", obj);

			vertices.add(v1);
		}
	}

	for(Integer k: graph.getAdj())
	{
		JSONObject v2 = new JSONObject();
		v2.put("id", k);
		JSONArray arr = new JSONArray();
		for(Integer i: graph.getVertex(k).getAdj())
		{
			JSONObject adjx = new JSONObject();
			adjx.put("id", i);
			String distance = Double.toString(graph.getVertex(k).getEdge(i));
			adjx.put("dis", distance);
			arr.add(adjx);
		}
		v2.put("adj", arr);
		arcos.add(v2);
	}

	JSONgraph.put("vs", vertices);
	JSONgraph.put("arcs", arcos);

	try
	{
		FileWriter file = new FileWriter(DATA, false);

		//Corrector
		String linea = JSONgraph.toJSONString();
		file.write(linea);
		file.flush();
		file.close();
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
}

public Graph<Integer, InfoVertex, Double> getGraph()
{
	return graph;
}

public static void main(String[] args)
{
	Manager man = new Manager();
	man.cargarInfoSinJSON();
	man.loadInfo();
	man.generarJSON();
}
}
