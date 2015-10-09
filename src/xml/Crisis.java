package xml;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Crisis {
	
	private String nombre;
	private String tipo;
	private String fecha;
	private String lugar;
	
	private List<Familia> familias;
	
	public Crisis(String nombre, String tipo, String fecha, String lugar) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.fecha = fecha;
		this.lugar = lugar;
	}

	public String getNombre() {
                XMLNombreCrisis spe1 = new XMLNombreCrisis();
		nombre=spe1.runExample();
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
            
                XMLTipoCrisis spe1 = new XMLTipoCrisis();
		tipo=spe1.runExample();
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}
        
        public String fechaAleatoria(){
                Calendar unaFecha;
                Random aleatorio;
                aleatorio = new Random();

                unaFecha = Calendar.getInstance();
                unaFecha.set (aleatorio.nextInt(2)-2014, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //System.out.println("La fecha vale " + sdf.format(unaFecha.getTime()));
                fecha=sdf.format(unaFecha.getTime()).toString();
                return fecha;
    }

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
                XMLLugarNacimiento spe1 = new XMLLugarNacimiento();
		lugar=spe1.runExample();
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

}
