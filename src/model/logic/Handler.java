package model.logic;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import model.logic.Representacion;

public class Handler extends DefaultHandler {

	// List to hold Employees object
		private List<Representacion> repList = null;
		private Representacion representacion = null;
		private StringBuilder data = null;

		// getter method for employee list
		public List<Representacion> getRepList() {
			return repList;
		}

		boolean bId = false;
		boolean bLat = false;
		boolean bLon = false;
	
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

			if (qName.equalsIgnoreCase("node")) {
				// create a new Employee and put it in Map
				String sId = attributes.getValue("id");
				// initialize Employee object and set id attribute
				representacion = new Representacion();
				representacion.setId(Integer.parseInt(sId));
				// initialize list
				if (repList == null)
					repList = new ArrayList<>();
			} else if (qName.equalsIgnoreCase("node")) {
				// set boolean values for fields, will be used in setting Employee variables
				bLat = true;
			} else if (qName.equalsIgnoreCase("node")) {
				bLon = true;
			}
			// create the data container
			data = new StringBuilder();
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (bId) {
				// age element, set Employee age
				representacion.setId(Integer.parseInt(data.toString()));
				bId = false;
			} else if (bLat) {
				representacion.setLat(Double.parseDouble(data.toString()));
				bLat = false;
			} else if (bLon) {
				representacion.setLon(Double.parseDouble(data.toString()));
				bLat = false;
			} 
			
			if (qName.equalsIgnoreCase("node")) {
				// add Employee object to list
				repList.add(representacion);
			}
		}

		@Override
		public void characters(char ch[], int start, int length) throws SAXException {
			data.append(new String(ch, start, length));
		}
	
}
