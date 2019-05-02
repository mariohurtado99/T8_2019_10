package api;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;

@SuppressWarnings("serial")
public class HelloWorld extends MapView 
{
	private Map map;
	
	private JFrame frame;
	
	public HelloWorld(String pName)
	{
		frame = new JFrame(pName);
		
//		setOnMapReadyHandler(new MapReadyHandler() 
//		{
//			
//			@Override
//			public void onMapReady(MapStatus status) 
//			{
//				if(status == MapStatus.MAP_STATUS_OK)
//				{
//					map = getMap();
//					
//					MapOptions mapOptions = new MapOptions();
//					
//					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
//					mapOptions.setMapTypeControlOptions(controlOptions);
//					
//					map.setOptions(mapOptions);
//					map.setCenter(new LatLng(41.8316578, -87.6374727));
//					map.setZoom(11.0);
//					
//					Marker mark = new Marker(map);
//					mark.setPosition(map.getCenter());
//					Rectangle rect = new Rectangle(map);
//					
//					rect.setBounds(new LatLngBounds(map.getCenter(), map.getCenter()));
//					
//					Circle circle = new Circle(map);
//					
//					circle.setCenter(map.getCenter());
//					circle.setRadius(500);
//					
//					CircleOptions co = new CircleOptions();
//					co.setFillColor("#FF0000");
//					co.setFillOpacity(0.35);
//					
//					circle.setOptions(co);
//				}
//			}
//		});
	}
	
	public void show()
	{
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
					map.setZoom(11.0);
					
					Marker mark = new Marker(map);
					mark.setPosition(map.getCenter());
					Rectangle rect = new Rectangle(map);
					
					rect.setBounds(new LatLngBounds(map.getCenter(), map.getCenter()));
					
					Circle circle = new Circle(map);
					
					circle.setCenter(map.getCenter());
					circle.setRadius(500);
					
					CircleOptions co = new CircleOptions();
					co.setFillColor("#FF0000");
					co.setFillOpacity(0.35);
					
					circle.setOptions(co);
				}
			}
		});
		frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		HelloWorld mapa = new HelloWorld("Nuevo mapa");
		mapa.show();
	}
}

