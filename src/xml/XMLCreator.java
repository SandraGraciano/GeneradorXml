package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;



public class XMLCreator {

	List<Crisis> myData;
	Document dom;

	public XMLCreator(int numCrisis, int numFamilias) {
		//crea una nueva lista
            int k=0;
            while (k<numCrisis) {                
               
                myData = new ArrayList<Crisis>();
		//la inicia
		loadData();
		//obtiene un DOM object
		createDocument();
                

                System.out.println("Empieza.... ");
                System.out.println("");
                
		createDOMTree(numFamilias);
		printToFile(numCrisis);
                
		System.out.println("Archivo generado.");
                System.out.println("");
                k=k+1;
            }

	}

//        public void parametrosEntrada(int numcrisis, int numfamilias){
//                int cri=0;
//                int fam=0;
//                while (cri<numcrisis) { 
//                    while (fam<numcrisis) {  
//                        //crea la instancia
//                        XMLCreator xce = new XMLCreator();
//                        //ejecuta el proceso de creacion
//                        xce.runCreaXML();  
//                    }
//                
//            }
//        }
        
	public void runCreaXML(){
		System.out.println("....Finalizó Proceso.... ");
                System.out.println("");
		//createDOMTree();
		//printToFile();
	}

	/**
	 * Agrega una lista de libros
	 * 
	 */
	private void loadData(){
		Crisis crisis = new Crisis("Crisis Frontera Venezuela", "Desplazamiento Forzado","2015-09-01","Cucuta");
		
		Afectado afectado1 = new Afectado("Luis", "Lopez", "Padre", "1950-09-09", "Bogota", "Caracas");
		Afectado afectado2 = new Afectado("Carla", "Perez", "Madre", "1955-12-05", "Cali", "Caracas");
		List<Afectado> afectados1 = new ArrayList<Afectado>();
		afectados1.add(afectado1);
		afectados1.add(afectado2);
		
		Afectado afectado3 = new Afectado("Andres", "Builes", "Padre", "1975-11-23", "Monteria", "Zamora");
		Afectado afectado4 = new Afectado("Sergio", "Builes", "Hijo", "1997-12-02", "Zamora", "Zamora");
		List<Afectado> afectados2 = new ArrayList<Afectado>();
		afectados2.add(afectado3);
		afectados2.add(afectado4);
		
		Familia familia1 = new Familia("1", afectados1);
		Familia familia2 = new Familia("2", afectados2);
		
		List<Familia> familias = new ArrayList<Familia>();
		familias.add(familia1);
		familias.add(familia2);
		
		crisis.setFamilias(familias);
		myData.add(crisis);
	}

	/**
	 * Crea un arbol nuevo de XML (esto aca es puro Xerces, el parseador de
	 * XML para Java
	 */
	private void createDocument() {

		//instancia de factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//instancia del builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//instancia del DOM
		dom = db.newDocument();

		}catch(ParserConfigurationException pce) {
			System.out.println("Error instanciando DocumentBuilder " + pce);
			System.exit(1);
		}

	}

	/**
	 * Crea la estructura XML
	 */
	private void createDOMTree(int numFamilias){

		//crea el root element <crisis>
		Element rootEle = dom.createElement("crisis");
		dom.appendChild(rootEle);

		Iterator<Crisis> it  = myData.iterator();
		
		while(it.hasNext()) {
			
			Crisis c = (Crisis)it.next();
			//Por cada objeto Crisis saca los atributos
			//arma el arbol
			Element nombreEle = dom.createElement("nombre");
			Text nombreText = dom.createTextNode(c.getNombre());
			nombreEle.appendChild(nombreText);
			rootEle.appendChild(nombreEle);
			
			Element tipoEle = dom.createElement("tipo");
			Text tipoText = dom.createTextNode(c.getTipo());
			tipoEle.appendChild(tipoText);
			rootEle.appendChild(tipoEle);
			
			Element fechaEle = dom.createElement("fecha");
			Text fechaText = dom.createTextNode(c.fechaAleatoria());
			fechaEle.appendChild(fechaText);
			rootEle.appendChild(fechaEle);
			
			Element lugarEle = dom.createElement("lugar");
			Text lugarText = dom.createTextNode(c.getLugar());
                        //Text lugarText = dom.createTextNode("Caqueta");
			lugarEle.appendChild(lugarText);
			rootEle.appendChild(lugarEle);
			
			Element afectadosEle = dom.createElement("afectados");
			rootEle.appendChild(afectadosEle);
                        
			int u=0;
                        List<Familia> familias = c.getFamilias();
			List<Afectado> afectados = null;
                        
                        double numfam=0;
                        if ((numFamilias % 2) == 0) {
                            numfam=numFamilias/2;
                        }else if((numFamilias % 2) != 0){
                            numfam=(numFamilias/2);
                            numfam=java.lang.Math.floor(numfam);
                            //numfam=Math.round(numfam);
                            numfam=numfam+1;
                        }
                        
                      while (u<(numfam)) { 
      
			if(familias != null && familias.size()>0){
				
				for(Familia familia: familias){
					//elemento familia
					Element familiaEle = dom.createElement("familia");
					afectadosEle.appendChild(familiaEle);
					
					afectados = familia.getAfectados();
					
					if(afectados!=null && afectados.size()>0){
                                                int i=1;
						for(Afectado afectado : afectados){
							
							//elemento afectado
							Element afectadoEle = dom.createElement("afectado");
							familiaEle.appendChild(afectadoEle);
							
							Element nombresEle = dom.createElement("nombres");
							Text nombresText = dom.createTextNode(afectado.getNombres());
							nombresEle.appendChild(nombresText);
							afectadoEle.appendChild(nombresEle);
							
							Element apellidosEle = dom.createElement("apellidos");
							Text apellidosText = dom.createTextNode(afectado.getApellidos());
							apellidosEle.appendChild(apellidosText);
							afectadoEle.appendChild(apellidosEle);

                                                        if (i==1) {
                                                        Element rolEle = dom.createElement("rol");
                                                        Text rolText = dom.createTextNode("Cabeza de hogar");
							//Text rolText = dom.createTextNode(afectado.getRol());
							rolEle.appendChild(rolText);
							afectadoEle.appendChild(rolEle);
                                                        
                                                        
                                                        } else if (i==2 || i==3){
                                                        Element rolEle = dom.createElement("rol");
                                                        Text rolText = dom.createTextNode("Hijo");
							//Text rolText = dom.createTextNode(afectado.getRol());
							rolEle.appendChild(rolText);
							afectadoEle.appendChild(rolEle);
                                                            
                                                        }else if(i==4){
                                                        Element rolEle = dom.createElement("rol");
                                                        Text rolText = dom.createTextNode("Tio");
							//Text rolText = dom.createTextNode(afectado.getRol());
							rolEle.appendChild(rolText);
							afectadoEle.appendChild(rolEle);
                                                        
                                                        }
                                                        
                                                        i=i+1;
							
							Element fechaNacEle = dom.createElement("fecha-nacimiento");
							Text fechaNacText = dom.createTextNode(afectado.fechaAleatoria1());
							fechaNacEle.appendChild(fechaNacText);
							afectadoEle.appendChild(fechaNacEle);
							
							Element lugarNacEle = dom.createElement("lugar-nacimiento");
							Text lugarNacText = dom.createTextNode(afectado.getLugarNacimiento());
							lugarNacEle.appendChild(lugarNacText);
							afectadoEle.appendChild(lugarNacEle);
							
							Element lugarProcEle = dom.createElement("lugar-procedencia");
							Text lugarProcText = dom.createTextNode(afectado.getLugarProcedencia());
							lugarProcEle.appendChild(lugarProcText);
							afectadoEle.appendChild(lugarProcEle);
						}
					}
					
				}
			}
                            
                            u=u+1;
                        
                    }

			
			

		}

	}

	/**
	 * Usando clases de Xerces imprime el XML en un archivo
     */
	private void printToFile(int numCris){

		try
		{
			//print
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			//para imprimir en consola usar este serializador
			//XMLSerializer serializer = new XMLSerializer(System.out, format);

                Random aleatorio;
                aleatorio = new Random();
                int numRandon = (int) Math.round(Math.random() * 99999999 ) ;
                
			//para generar un archivo de salida con fileoutputstream en lugar de system.out
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File("Crisis"+numRandon+".xml")), format);
                        //new FileOutputStream(new File("C:/Users/698315/Desktop/FinalPractica/CreaXMLCrisisSebas/CrisisGeneradas/Crisis"+numRandon+".xml")), format);

			serializer.serialize(dom);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}
        
        public String fechaAleatoria(){
                Calendar unaFecha;
                String fecha ="";
                Random aleatorio;
                aleatorio = new Random();

                unaFecha = Calendar.getInstance();
                unaFecha.set (aleatorio.nextInt(10)+2015, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("La fecha vale " + sdf.format(unaFecha.getTime()));
                fecha=sdf.format(unaFecha.getTime()).toString();
                return fecha;
    }


	public static void main(String args[]) {

		//crea la instancia
		XMLCreator xce = new XMLCreator(25,10);//número de crisis y número de familias
		//ejecuta el proceso de creacion
                //xce.parametrosEntrada(2, 4);
		xce.runCreaXML();
	}
}
