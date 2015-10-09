package xml;

import java.util.List;

public class Familia {

	private String idFamilia;
	private List<Afectado> afectados;
	
	public Familia(String idFamilia, List<Afectado> afectados) {
		super();
		this.idFamilia = idFamilia;
		this.afectados = afectados;
	}

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public List<Afectado> getAfectados() {
		return afectados;
	}

	public void setAfectados(List<Afectado> afectados) {
		this.afectados = afectados;
	}
	
}
