package model.logic;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import model.logic.Representacion;




public class XMLParserSAX {

    public static void main(String[] args) {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        Handler handler = new Handler();
        saxParser.parse(new File("./data/Central-WashingtonDC-OpenStreetMap.xml"), handler);
        //Get Employees list
        List<Representacion> repList = handler.getRepList();
        //print employee information
        if(repList != null) {
        for(Representacion rep : repList)
            System.out.println(rep);}
    } catch (ParserConfigurationException | SAXException | IOException e) {
        e.printStackTrace();
    }
    }

}
