package maps;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MapTypeId;
import com.teamdev.jxmaps.MapTypeStyle;
import com.teamdev.jxmaps.MapTypeStyleElementType;
import com.teamdev.jxmaps.MapTypeStyleFeatureType;
import com.teamdev.jxmaps.MapTypeStyler;
import com.teamdev.jxmaps.swing.MapView;
import com.teamdev.jxmaps.Polygon;
import com.teamdev.jxmaps.PolygonOptions;
import com.teamdev.jxmaps.StyledMapType;

import model.data_structures.Graph;
import model.vo.InfoVertex;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Mapping extends MapView
{
	private Map map;
	//	public Mapping(MapViewOptions options)
	//	{
	//		super(options);
	//		setOnMapReadyHandler(new MapReadyHandler() {
	//			@Override
	//			public void onMapReady(MapStatus status) {
	//				if (status == MapStatus.MAP_STATUS_OK) {
	//					final Map map = getMap();
	//					map.setZoom(5.0);
	//					GeocoderRequest request = new GeocoderRequest(map);
	//					request.setAddress("Kharkiv, UA");
	//
	//					getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
	//						@Override
	//						public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
	//							if (status == GeocoderStatus.OK) {
	//								map.setCenter(result[0].getGeometry().getLocation());
	//								Marker marker = new Marker(map);
	//								marker.setPosition(result[0].getGeometry().getLocation());
	//
	//								final InfoWindow window = new InfoWindow(map);
	//								window.setContent("Hello, World!");
	//								window.open(map, marker);
	//							}
	//						}
	//					});
	//				}
	//			}
	//		});
	//	}

	public Mapping(MapViewOptions options)
	{
		super(options);
	}

	public void map(String pName, Graph<Integer, InfoVertex, Double> graph)
	{
		JFrame frame = new JFrame(pName);
		setOnMapReadyHandler(new MapReadyHandler()
		{

			@Override
			public void onMapReady(MapStatus status)
			{
				if(status == MapStatus.MAP_STATUS_OK)
				{
					map = getMap();

					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(41.8316578, -87.6374727));
					map.setZoom(15);

					CircleOptions co = new CircleOptions();
					co.setFillColor("#FF0000");
					co.setFillOpacity(0.35);

					PolygonOptions po = new PolygonOptions(map);
					po.setFillColor("#FF0000");
					po.setFillOpacity(2);
					po.setStrokeWeight(1);

					//					LatLng[] path = {map.getCenter(), new LatLng(4.6361097, -74.0976732)};
					//					Polygon polygon = new Polygon(map);
					//					polygon.setPath(path);
					//					polygon.setOptions(po);

					for(Integer k : graph.getAdj())
					{
						InfoVertex info = graph.getInfoVertex(k);
						Circle circle = new Circle(map);
						circle.setCenter(new LatLng(info.getLatitud(), info.getLongitud()));
						circle.setRadius(15);
						circle.setOptions(co);
						for(Integer j : graph.getVertex(k).getAdj())
						{
							InfoVertex info2 = graph.getInfoVertex(j);
							LatLng[] path = {new LatLng(info.getLatitud(), info.getLongitud()),
									new LatLng(info2.getLatitud(), info2.getLongitud())};

							Polygon polygon = new Polygon(map);
							polygon.setPath(path);
							polygon.setOptions(po);
						}

					}
				}
			}
		});

		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void map1(String name)
	{
		JFrame frame = new JFrame(name);
		setOnMapReadyHandler(new MapReadyHandler()
		{

			@Override
			public void onMapReady(MapStatus status)
			{
				if(status == MapStatus.MAP_STATUS_OK)
				{
					map = getMap();

					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					mapOptions.setMapTypeControlOptions(controlOptions);

					map.setOptions(mapOptions);
					map.setCenter(new LatLng(41.8316578, -87.6374727));
					map.setZoom(15);

					StyledMap eme = new StyledMap(map);

					//					StyledMapType darkMap = createStyle(map);

					map.mapTypes().set("dark", eme.getStyle());
					map.setMapTypeId(new MapTypeId("dark"));

					CircleOptions co = new CircleOptions();
					co.setFillColor("#FF0000");
					co.setFillOpacity(0.35);

					PolygonOptions po = new PolygonOptions(map);
					po.setFillColor("#FF0000");
					po.setFillOpacity(0.35);

					LatLng[] path = {map.getCenter(), new LatLng(4.6361097, -74.0976732)};
					Polygon polygon = new Polygon(map);
					polygon.setPath(path);
					polygon.setOptions(po);
				}
			}
		});

		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private StyledMapType createStyle(Map map)
	{
		MapTypeStyle style = new MapTypeStyle();
		style.setElementType(MapTypeStyleElementType.GEOMETRY);
		MapTypeStyler s1 = new MapTypeStyler();
		s1.setColor("#ebe3cd");

		style.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style1 = new MapTypeStyle();
		style1.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#523735");

		style1.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style2 = new MapTypeStyle();
		style2.setElementType(MapTypeStyleElementType.LABELS_TEXT_STROKE);
		s1 = new MapTypeStyler();
		s1.setColor("#f5f1e6");

		style2.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style3 = new MapTypeStyle();
		style3.setElementType(MapTypeStyleElementType.GEOMETRY_STROKE);
		style3.setFeatureType(MapTypeStyleFeatureType.ADMINISTRATIVE);
		s1 = new MapTypeStyler();
		s1.setColor("#c9b2a6");

		style3.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style4 = new MapTypeStyle();
		style4.setElementType(MapTypeStyleElementType.GEOMETRY_STROKE);
		style4.setFeatureType(MapTypeStyleFeatureType.ADMINISTRATIVE_LAND_PARCEL);
		s1 = new MapTypeStyler();
		s1.setColor("#dcd2be");

		style4.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style5 = new MapTypeStyle();
		style5.setFeatureType(MapTypeStyleFeatureType.ADMINISTRATIVE_LAND_PARCEL);
		style5.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#ae9e90");

		style5.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style6 = new MapTypeStyle();
		style6.setFeatureType(MapTypeStyleFeatureType.LANDSCAPE_NATURAL);
		style6.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#dfd2ae");

		style6.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style7 = new MapTypeStyle();
		style7.setFeatureType(MapTypeStyleFeatureType.POI);
		style7.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#dfd2ae");

		style7.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style8 = new MapTypeStyle();
		style8.setFeatureType(MapTypeStyleFeatureType.POI);
		style8.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#93817c");

		style8.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style9 = new MapTypeStyle();
		style9.setFeatureType(MapTypeStyleFeatureType.POI_PARK);
		style9.setElementType(MapTypeStyleElementType.GEOMETRY_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#a5b076");

		style9.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style10 = new MapTypeStyle();
		style10.setFeatureType(MapTypeStyleFeatureType.POI_PARK);
		style10.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#447530");

		style10.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style11 = new MapTypeStyle();
		style11.setFeatureType(MapTypeStyleFeatureType.ROAD);
		style11.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#f5f1e6");

		style11.setStylers(new MapTypeStyler[] {s1});

		//
		MapTypeStyle style12 = new MapTypeStyle();
		style12.setFeatureType(MapTypeStyleFeatureType.ROAD_ARTERIAL);
		style12.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#fdfcf8");

		style12.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style13 = new MapTypeStyle();
		style13.setFeatureType(MapTypeStyleFeatureType.ROAD_HIGHWAY);
		style13.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#f8c967");

		style13.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style14 = new MapTypeStyle();
		style14.setFeatureType(MapTypeStyleFeatureType.ROAD_HIGHWAY);
		style14.setElementType(MapTypeStyleElementType.GEOMETRY_STROKE);
		s1 = new MapTypeStyler();
		s1.setColor("#e9bc62");

		style14.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style15 = new MapTypeStyle();
		style15.setFeatureType(MapTypeStyleFeatureType.ROAD_HIGHWAY_CONTROLLED_ACCESS);
		style15.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#e98d58");

		style15.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style16 = new MapTypeStyle();
		style16.setFeatureType(MapTypeStyleFeatureType.ROAD_HIGHWAY_CONTROLLED_ACCESS);
		style16.setElementType(MapTypeStyleElementType.GEOMETRY_STROKE);
		s1 = new MapTypeStyler();
		s1.setColor("#db8555");

		style16.setStylers(new MapTypeStyler[] {s1});
		//
		MapTypeStyle style17 = new MapTypeStyle();
		style17.setFeatureType(MapTypeStyleFeatureType.ROAD_LOCAL);
		style17.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#806b63");

		style17.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style18 = new MapTypeStyle();
		style18.setFeatureType(MapTypeStyleFeatureType.TRANSIT_LINE);
		style18.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#dfd2ae");

		style18.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style19 = new MapTypeStyle();
		style19.setFeatureType(MapTypeStyleFeatureType.TRANSIT_LINE);
		style19.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#8f7d77");

		style19.setStylers(new MapTypeStyler[] {s1});
		//
		MapTypeStyle style20 = new MapTypeStyle();
		style20.setFeatureType(MapTypeStyleFeatureType.TRANSIT_LINE);
		style20.setElementType(MapTypeStyleElementType.LABELS_TEXT_STROKE);
		s1 = new MapTypeStyler();
		s1.setColor("#ebe3cd");

		style20.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style21 = new MapTypeStyle();
		style21.setFeatureType(MapTypeStyleFeatureType.TRANSIT_STATION);
		style21.setElementType(MapTypeStyleElementType.GEOMETRY);
		s1 = new MapTypeStyler();
		s1.setColor("#dfd2ae");

		style21.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style22 = new MapTypeStyle();
		style22.setFeatureType(MapTypeStyleFeatureType.WATER);
		style22.setElementType(MapTypeStyleElementType.GEOMETRY_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#b9d3c2");

		style22.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle style23 = new MapTypeStyle();
		style23.setFeatureType(MapTypeStyleFeatureType.WATER);
		style23.setElementType(MapTypeStyleElementType.LABELS_TEXT_FILL);
		s1 = new MapTypeStyler();
		s1.setColor("#92998d");

		style23.setStylers(new MapTypeStyler[] {s1});

		MapTypeStyle[] styles = {style,
				style1,
				style2,
				style3,
				style4,
				style5,
				style6,
				style7,
				style8,
				style9,
				style10,
				style11,
				style12,
				style13,
				style14,
				style15,
				style16,
				style17,
				style18,
				style19,
				style20,
				style21,
				style22,
				style23};

		StyledMapType styledMap = new StyledMapType(map, styles);
		styledMap.setName("Dark");

		return styledMap;
	}

	public static void main(String[] args) 
	{
		MapViewOptions options = new MapViewOptions();
		options.importPlaces();
		options.setApiKey("Key de google");
		Mapping pan = new Mapping(options);
		pan.map1("hola");
	}
}
