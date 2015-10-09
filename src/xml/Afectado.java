package xml;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Random;

public class Afectado {
	
	private String nombres;
	private String apellidos;
	private String rol;
	private String fechaNacimiento;
	private String lugarNacimiento;
	private String lugarProcedencia;

	public Afectado(String nombres, String apellidos, String rol,
			String fechaNacimiento, String lugarNacimiento,
			String lugarProcedencia) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rol = rol;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
		this.lugarProcedencia = lugarProcedencia;
	}

	public String getNombres() {
            
            	XMLReader spe = new XMLReader();
		nombres=spe.runExample();
		return nombres;
	}
        

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
                XMLReader1 spe1 = new XMLReader1();
		apellidos=spe1.runExample();
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRol() {
            
            //Para el ejemplo se crearan tres roles
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
        public String fechaAleatoria1(){
                Calendar unaFecha;
                Random aleatorio;
                aleatorio = new Random();

                unaFecha = Calendar.getInstance();
                unaFecha.set (aleatorio.nextInt(70)-2014, aleatorio.nextInt(12)+1, aleatorio.nextInt(30)+1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //System.out.println("La fecha vale " + sdf.format(unaFecha.getTime()));
                fechaNacimiento=sdf.format(unaFecha.getTime()).toString();
                return fechaNacimiento;
    }

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
                XMLLugarNacimiento spe1 = new XMLLugarNacimiento();
		lugarNacimiento=spe1.runExample();
		
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getLugarProcedencia() {
                XMLLugarNacimiento spe1 = new XMLLugarNacimiento();
		lugarProcedencia=spe1.runExample();
		return lugarProcedencia;
	}

	public void setLugarProcedencia(String lugarProcedencia) {
		this.lugarProcedencia = lugarProcedencia;
	}

}
