package xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReader extends DefaultHandler{
	
	List<Nombre> misNombres;
	
	private String tempVal;
	//to maintain context
	private Nombre tempNombre;
	
	
	public XMLReader(){
		misNombres = new ArrayList<Nombre>();
	}
	
	public String runExample() {
		parseDocument();
		return printData();
	}

	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			sp.parse("nombres.xml", this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents
	 */
	public String printData(){
		
		//System.out.println("No of nombres '" + misNombres.size() + "'.");
                String nombre="Sara";
		Random aleatorio;
                aleatorio = new Random();
                int numRandon = (int) Math.round(Math.random() * misNombres.size() ) ;
		Iterator<Nombre> it = misNombres.iterator();
                //System.out.println("");
                int i =0;
		while(it.hasNext()) {
			Nombre nom = it.next();
                        i=i+1;
                        if (i==numRandon) {
                            nombre = nom.getNombres().toString();
                            //System.out.println(nom.getNombres()+" - Genero: "+nom.getGenero());
                        }
			//System.out.println(nom.getNombres()+" - Genero: "+nom.getGenero());
		}
                return nombre;
	}
        
        
	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("Nombre")) {
			//create a new instance of employee
			tempNombre = new Nombre();
			tempNombre.setGenero(attributes.getValue("genero"));
		}
	}
	


	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("Nombre")) {
			//add it to the list
			tempNombre.setNombres(tempVal);
			misNombres.add(tempNombre);
			
		}/*else if (qName.equalsIgnoreCase("nombre")) {
			tempNombre.setNombres(tempVal);
		}else if (qName.equalsIgnoreCase("Id")) {
			tempNombre.setId(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("Age")) {
			tempNombre.setAge(Integer.parseInt(tempVal));
		}*/
		
	}
	
	public static void main(String[] args){
		XMLReader spe = new XMLReader();
		spe.runExample();
	}

}
