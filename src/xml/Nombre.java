package xml;

public class Nombre {
	
	private String genero;
	private String nombres;
	
	public Nombre() {
		// TODO Auto-generated constructor stub
	}
	
	public Nombre(String genero, String nombres) {
		super();
		this.genero = genero;
		this.nombres = nombres;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

}
