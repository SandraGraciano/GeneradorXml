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

public class XMLReader1 extends DefaultHandler{
	
	List<Apellidos> misApellidos;
	
	private String tempVal;
	//to maintain context
	private Apellidos tempApellido;
	
	
	public XMLReader1(){
		misApellidos = new ArrayList<Apellidos>();
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
			sp.parse("apellidos.xml", this);
			
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
		
		//System.out.println("No of apellidos '" + misApellidos.size() + "'.");
                String apellido="Maldonado";
		Random aleatorio;
                aleatorio = new Random();
                int numRandon = (int) Math.round(Math.random() * misApellidos.size() ) ;
		Iterator<Apellidos> it = misApellidos.iterator();
                //System.out.println("");
                int i =0;
		while(it.hasNext()) {
			Apellidos ape = it.next();
                        i=i+1;
                        if (i==numRandon) {
                            apellido = ape.getApellido().toString();
                            //System.out.println(ape.getApellido());
                        }
			//System.out.println(nom.getNombres()+" - Genero: "+nom.getGenero());
		}
                return apellido;
	}
        
        
	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("apellido")) {
			//create a new instance of employee
			tempApellido = new Apellidos();
		}
	}
	


	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("apellido")) {
			//add it to the list
			tempApellido.setApellido1(tempVal);
			misApellidos.add(tempApellido);
			
		}/*else if (qName.equalsIgnoreCase("nombre")) {
			tempNombre.setNombres(tempVal);
		}else if (qName.equalsIgnoreCase("Id")) {
			tempNombre.setId(Integer.parseInt(tempVal));
		}else if (qName.equalsIgnoreCase("Age")) {
			tempNombre.setAge(Integer.parseInt(tempVal));
		}*/
		
	}
	
	public static void main(String[] args){
		XMLReader1 spe = new XMLReader1();
		spe.runExample();
	}

}
