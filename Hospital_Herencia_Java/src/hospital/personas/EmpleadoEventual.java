package hospital.personas;

public class EmpleadoEventual extends Empleado {
	
	private double honorariosHora;
	private String fechaTerminoContrato;
	
	public double getHonorariosHora() {
		return honorariosHora;
	}
	public void setHonorariosHora(double honorariosHora) {
		this.honorariosHora = honorariosHora;
	}
	
	public String getFechaTerminoContrato() {
		return fechaTerminoContrato;
	}
	public void setFechaTerminoContrato(String fechaTerminoContrato) {
		this.fechaTerminoContrato = fechaTerminoContrato;
	}
	
}
