package maps;

import model.data_structures.*;
import model.vo.*;

import java.io.FileWriter;
import java.io.PrintWriter;

public class MapDrawer
{
	public static void loadMap(Graph<Integer, InfoVertex, Double> graph, int index)
	{
		final String functions = "./mapsAPI/functions.js";
		try
		{
			PrintWriter log = new PrintWriter(new FileWriter(functions, false));
			log.println("function initMap(){"
					+ "var location ="
					+ "{lat : 41.8316578,"
					+ "lng : -87.6374727};");
			
			log.println("var map = new google.maps.Map(document.getElementById('map'),"
					+ "{"
					+ "zoom : 15,"
					+ "center: location,"
					+ "styles: [" + 
					"                {" + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#242f3e\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#746855\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"elementType\": \"labels.text.stroke\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#242f3e\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"administrative.land_parcel\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"visibility\": \"off\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"administrative.locality\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#d59563\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"administrative.neighborhood\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"visibility\": \"off\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"poi\"," + 
					"                    \"elementType\": \"labels.text\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"visibility\": \"off\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"poi\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#d59563\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"poi.park\"," + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#263c3f\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"poi.park\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#6b9a76\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road\"," + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#38414e\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road\"," + 
					"                    \"elementType\": \"geometry.stroke\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#212a37\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road\"," + 
					"                    \"elementType\": \"labels\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"visibility\": \"off\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#9ca5b3\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road.highway\"," + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#746855\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road.highway\"," + 
					"                    \"elementType\": \"geometry.stroke\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#1f2835\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"road.highway\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#f3d19c\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"transit\"," + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#2f3948\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"transit.station\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#d59563\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"water\"," + 
					"                    \"elementType\": \"geometry\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#17263c\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"water\"," + 
					"                    \"elementType\": \"labels.text\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"visibility\": \"off\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"water\"," + 
					"                    \"elementType\": \"labels.text.fill\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#515c6d\"" + 
					"                        }" + 
					"                    ]" + 
					"                }," + 
					"                {" + 
					"                    \"featureType\": \"water\"," + 
					"                    \"elementType\": \"labels.text.stroke\"," + 
					"                    \"stylers\": [" + 
					"                        {" + 
					"                            \"color\": \"#17263c\"" + 
					"                        }" + 
					"                    ]" + 
					"                }" + 
					"            ]"
					+ "});");
			
			int i = 0;
			log.print("var rectangle = null;");
			log.println("var circle = null;");
			
			for(Integer k : graph.getAdj())
			{
				InfoVertex info = graph.getInfoVertex(k);
				if(i < index)
				{
					if(!info.isDrawn())
					{
						log.println("circle = new google.maps.Circle("
								+ "{center: "
								+ "{ lat: " + info.getLatitud() + ", "
								+ "lng: " + info.getLongitud() + "},"
								+ "map: map,"
								+ "radius: 15,"
								+ "strokeColor: 'red',"
								+ "fillColor: 'red'});");
//						System.out.print("Dibujado, ");
//						System.out.println("Contador: " + i + " Id: " + k + " latitud: " + graph.getInfoVertex(k).getLatitud() + " longitud: " + graph.getInfoVertex(k).getLongitud());
						
						for(Integer j : graph.getVertex(k).getAdj())
						{
							if(graph.getInfoArc(k, j) > 0.005)
							{
								log.println("var destinations" + j + " = [];");
								log.println("destinations" + j + ".push(new google.maps.LatLng(" + info.getLatitud() + ", " + info.getLongitud() + ") );");
								InfoVertex info2 = graph.getInfoVertex(j);
								log.println("destinations" + j + ".push(new google.maps.LatLng(" + info2.getLatitud() + ", " + info2.getLongitud() + ") );");
								log.println("var polylineOptions" + j + " = {path: destinations" + j + "}");
								log.println("var polyline" + j + " = new google.maps.Polyline(polylineOptions" + j + ");"
										+ "polyline" + j + ".setMap(map);");
							}
							if(graph.getInfoArc(k, j) < 0.005)
							{
								graph.getInfoVertex(j).drawIt();
//								System.out.print("No dibujado, ");
//								System.out.println("Contador: " + i + " Id: " + j + " latitud: " + graph.getInfoVertex(j).getLatitud() + " longitud: " + graph.getInfoVertex(j).getLongitud());
							}
						}
					}
					else if(!info.isDrawn())
					{
						log.println("rectangle = new google.maps.Rectangle(" + 
								"{ "+ 
								"map : map," + 
								"bounds: new google.maps.LatLngBounds(" + 
								"new google.maps.LatLng(" + (info.getLatitud() + 0.00006)+", " + (info.getLongitud() - 0.0001) + "), " + 
								"new google.maps.LatLng(" + (info.getLatitud() - 0.00006893) + ", " + (info.getLongitud() + 0.00011743) + ")" + 
								"), " + 
								"fillColor: 'green', " + 
								"strokeColor: 'green' " + 
								"}" + 
								");");
						
						for(Integer j : graph.getVertex(k).getAdj())
						{
							log.println("var destinations" + j + " = [];");
							log.println("destinations" + j + ".push(new google.maps.LatLng(" + info.getLatitud() + ", " + info.getLongitud() + ") );");
							InfoVertex info2 = graph.getInfoVertex(j);
							log.println("destinations" + j + ".push(new google.maps.LatLng(" + info2.getLatitud() + ", " + info2.getLongitud() + ") );");
							log.println("var polylineOptions" + j + " = {path: destinations" + j + "}");
							log.println("var polyline" + j + " = new google.maps.Polyline(polylineOptions" + j + ");"
									+ "polyline" + j + ".setMap(map);");
//								System.out.print("No dibujado, ");
//								System.out.println("Contador: " + i + " Id: " + j + " latitud: " + graph.getInfoVertex(j).getLatitud() + " longitud: " + graph.getInfoVertex(j).getLongitud());
						}
					}
					i++;
				}
				else
				{
					break;
				}
			}
			log.println("}");
			log.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
