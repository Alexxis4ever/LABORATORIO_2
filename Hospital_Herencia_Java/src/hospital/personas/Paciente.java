package hospital.personas;

import java.util.ArrayList;

public class Paciente extends Persona {
	
	private String numHistoria;
	private String sexo;
	private String grupoSanguineo;
	private ArrayList<String> listaMedicamentos;
	
	public String getNumHistoria() {
		return numHistoria;
	}
	public void setNumHistoria(String numHistoria) {
		this.numHistoria = numHistoria;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}
	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}
	public ArrayList<String> getListaMedicamentos() {
		return listaMedicamentos;
	}
	public void setListaMedicamentos(ArrayList<String> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}
		
}

