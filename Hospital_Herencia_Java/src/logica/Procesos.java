package logica;

import hospital.CitaMedica;
import hospital.personas.EmpleadoEventual;
import hospital.personas.EmpleadoPlanilla;
import hospital.personas.Medico;
import hospital.personas.Paciente;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Jackson Londoño
 */

public class Procesos {
	
	String dni,nombre,apellido,fechaNacimiento,direccion,ciudad,codEmpleado,numHorasExt, fechaIngreso, area,cargo;
	
	
	ArrayList<Paciente> arregloPacientes;
	ArrayList<EmpleadoPlanilla> arregloEmpleadosPlanilla;
	ArrayList<EmpleadoEventual> arregloEmpleadosEventual;
	ArrayList<Medico> arregloMedicos;
	ArrayList<CitaMedica> arregloCitas = new ArrayList<CitaMedica>();
	
	int cantCitas = 0;
	int numCitas = 0;
	
	
	/**
	 * Por medio de este metodo podemos 
	 * iniciar nuestro sistema.
	 */
	
	public Procesos(){
		
		for (int i = 0; i < 1; i++) {
			
			try {
					numCitas=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de citas medicas por dia"));
					
			} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Error, ingreso texto");
					i--;
			} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Se genero un error");
					i--;
			}
		}
			presentarMenu();
		
	}
	
	
	/**
	 * Por medio de este metodo se presenta el menu con las opciones de ingreso.
	 */
	
	private void presentarMenu() {
		
		int opc = 0;
		String cad="SISTEMA HOSPITALARIO\n\n";
		cad+="1. Registrar Usuarios\n";
		cad+="2. Registrar Cita Medica\n";
		cad+="3. Imprimir Datos\n";
		cad+="4. Salir\n\n";
		cad+="Ingrese una opcion";
		
		do {
			for (int i = 0; i < 1; i++) {
				
				
			/**
			* Por medio de la sentencia try-catch, validamos
			* que el dato ingresado sea de tipo numerico.
			*/
				try {
					opc=Integer.parseInt(JOptionPane.showInputDialog(cad));
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Error, ingreso texto");
					i--;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Se genero un error");
					i--;
				}
				
			}
			switch (opc) {
			case 1:
				cargarMenuRegistro();
				break;
				
			case 2: 
				System.out.println(cantCitas+"<"+numCitas);
				if (cantCitas<numCitas) {
					
				/**
				* Se realiza la validacion de las clases previas, para asi 
				* registrar la cita medica.
				*/
					
				if (validaRegistrosPrevios()) {
					
					registrarCitaMedica();
					
				} else {
					
					JOptionPane.showMessageDialog(null, "No se puede registrar la cita, verifique que los empleados por planilla,\n"+ "medicos y pacientes se encuentren previamente\nregistrados en el sistema","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
					
				}else {
					JOptionPane.showMessageDialog(null, "No se puede registrar la cita, Ha superado el numero de citas por dia","Advertencia",JOptionPane.WARNING_MESSAGE);
				}
				break;
				
			case 3: 
				if (validaRegistrosEmpleados()) {
					
					imprimirDatos();
				}
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestro sistema");
				break;

			default:
				JOptionPane.showMessageDialog(null, "Ingreso una opcion invalida");
				break;
			}
		} while (opc!=4);
		
	}

	
	/**
	* Este metodo es el encargado de imprimir los datos
	* respecto a la opcion que elegida.
	*/	

	private void imprimirDatos() {
		
		
		String menuMsj="Menu Reportes\n\n";
		menuMsj+="1. Imprimir Empleados de Planilla\n";
		menuMsj+="2. Imprimir Empleados Eventuales\n";
		menuMsj+="3. Imprimir Medicos\n";
		menuMsj+="4. Imprimir Pacientes\n";
		menuMsj+="5. Imprimir Citas\n";
		menuMsj+="6. Regresar\n\n";
		menuMsj+="Ingrese una opcion";
		
		
		int cod = 0;
		
		
		do {
			
			
			for (int i = 0; i < 1; i++) {
				
				
				try {
					cod=Integer.parseInt(JOptionPane.showInputDialog(menuMsj));
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Error, ingreso texto");
					i--;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Se genero un error");
					i--;
				}
				
				
			}
			
			
			switch (cod) {
			case 1:
	
				/**
				* Aqui se muestran los registros que se encuentren 
				* dentro de el arrayList de Empleado Planilla.
				*/	
				
				if (arregloEmpleadosPlanilla!=null) {
					
					System.out.println("**********Empleados Planilla**********");
					
					for (int i = 0; i < arregloEmpleadosPlanilla.size(); i++) {
						
						System.out.println("Numero DNI: "+arregloEmpleadosPlanilla.get(i).getDni());

						System.out.println("Nombre: "+arregloEmpleadosPlanilla.get(i).getNombre()+" "+arregloEmpleadosPlanilla.get(i).getApellido());

						System.out.println("Codigo Empleado: "+arregloEmpleadosPlanilla.get(i).getCodEmpleado());

						System.out.println("Cargo: "+arregloEmpleadosPlanilla.get(i).getCargo());

						System.out.println("Salario mensual: "+arregloEmpleadosPlanilla.get(i).getSalarioMensual());
						
						System.out.println();
					}
					System.out.println("*************************************************************************************");
					
				}else {
						JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 2:
				
				
				/**
				* Aqui se muestran los registros que se encuentren dentro 
				* del arrayList de Empleado Eventual.
				*/	
				
				
				if (arregloEmpleadosEventual!=null) {

					System.out.println("******************************Empleados Eventuales**************************************");
					
					for (int i = 0; i < arregloEmpleadosEventual.size(); i++) {

						System.out.println("Numero DNI: "+arregloEmpleadosEventual.get(i).getDni());

						System.out.println("Nombre: "+arregloEmpleadosEventual.get(i).getNombre()+" "+arregloEmpleadosEventual.get(i).getApellido());

						System.out.println("Codigo Empleado: "+arregloEmpleadosEventual.get(i).getCodEmpleado());

						System.out.println("Cargo: "+arregloEmpleadosEventual.get(i).getCargo());

						System.out.println("Honorarios por hora: "+arregloEmpleadosEventual.get(i).getHonorariosHora());

						System.out.println("Fecha termino de contrato: "+arregloEmpleadosEventual.get(i).getFechaTerminoContrato());
						
						System.out.println();
					}
					System.out.println("*************************************************************************************");

				} else {
					JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);

				} break;
				
			case 3:	
			
				/**
				* Aqui se muestran los registros que se encuentren 
				* dentro del arrayList Medicos.
				*/	
				
				
				if (arregloMedicos!=null) {

					System.out.println("**********************************Medicos*********************************************");

					for (int i = 0; i < arregloMedicos.size(); i++) {
						
						System.out.println("Numero DNI: "+arregloMedicos.get(i).getDni());

						System.out.println("Nombre: "+arregloMedicos.get(i).getNombre()+" "+arregloMedicos.get(i).getApellido());
						
						System.out.println("Codigo Empleado: "+arregloMedicos.get(i).getCodEmpleado());

						System.out.println("Cargo: "+arregloMedicos.get(i).getCargo());
						
						System.out.println("Especialidad: "+arregloMedicos.get(i).getEspecialidad());

						System.out.println("Numero de consultorio: "+arregloMedicos.get(i).getNumConsultorio());
						
					    System.out.println();
					    
					}
					System.out.println("*************************************************************************************");

				} else {
					  JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);

				} break;
				
			case 4:		
				
				/**
				* Aqui se muestran los registros que se encuentren dentro 
				* del arrayList Pacientes.
				*/	
				
				if (arregloPacientes!=null) {

					System.out.println("**********************************Pacientes*********************************************");

					for (int i = 0; i < arregloPacientes.size(); i++) {
					
						System.out.println("Numero DNI: "+arregloPacientes.get(i).getDni());
		
						System.out.println("Nombre: "+arregloPacientes.get(i).getNombre()+" "+arregloPacientes.get(i).getApellido());
						
						System.out.println("Numero Historia Clinica: "+arregloPacientes.get(i).getNumHistoria());
		
						System.out.println("Sexo: "+arregloPacientes.get(i).getSexo());
						
						System.out.println("Grupo Sanguineo: "+arregloPacientes.get(i).getGrupoSanguineo());
		
						System.out.println("Lista Medicamentos");
					
						for (int j = 0; j < arregloPacientes.get(i).getListaMedicamentos().size(); j++) {
							System.out.print(arregloPacientes.get(i).getListaMedicamentos().get(j)+" / ");

						}
						System.out.println();
					}
					System.out.println("*************************************************************************************");

				}else{
					JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);

				}
				break;
				
			case 5:
				
				/**
				* Aqui se muestran los registros que se encuentren 
				* dentro del arrayList Citas.
				*/	
				
				
				if (arregloCitas!=null) {

					System.out.println("**********************************Citas*********************************************");

					for (int i = 0; i < arregloCitas.size(); i++) {
						System.out.println("Servicio: "+arregloCitas.get(i).getServicio());

						System.out.println("Paciente: "+arregloCitas.get(i).getPaciente().getNombre()+" "+arregloCitas.get(i).getPaciente().getApellido());
			
						System.out.println("Medico: "+arregloCitas.get(i).getMedico().getNombre()+" "+arregloCitas.get(i).getMedico().getApellido() );
			
						System.out.println("Fecha: "+arregloCitas.get(i).getFecha());
			
						System.out.println("Hora: "+arregloCitas.get(i).getHora());
						System.out.println();
					}

				System.out.println("*************************************************************************************");

				}else{
					JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);

				}
				break;
			case 6: 
				JOptionPane.showMessageDialog(null, "Haz salido del menu registros");
				break;
				
			default: 
				JOptionPane.showMessageDialog(null, "Ingreso una opcion invalida");
				break;
			}
			
		} while (cod!=6);
		
	}
	
	/**
	* @return Aqui Validamos que el usuario que desea ver los 
	* registros sea un empleado eventual o de 
	* planilla retornando un valor verdadero o falso. 
	*/	

	private boolean validaRegistrosEmpleados() {
		
		boolean retorno=false;
		
		if (arregloEmpleadosPlanilla!=null || arregloEmpleadosEventual!=null) {
			if (validarEmpleados()) {
				retorno=true;
			}else{
				JOptionPane.showMessageDialog(null, "No existen empleados con ese documento","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null, "No existen empleados Registrados","Error",JOptionPane.ERROR_MESSAGE);
		}
		return retorno;
	}
	
	
	private boolean validarEmpleadoPlanilla() {
		
		String documentoEmpleado=JOptionPane.showInputDialog("Ingrese el documento del empleado por planilla");
		
		boolean retorna=false;
		
		for (int i = 0; i < arregloEmpleadosPlanilla.size(); i++) {
			if(documentoEmpleado.equals(arregloEmpleadosPlanilla.get(i).getDni())) {
				retorna=true;
			}
		}
		return retorna;
	}
	
	
	/**
	* Aqui validamos que el documento pertenezca a 
	* alguno de los empleados ya sea uno eventual o de planilla.
	*/	
	
	
	private boolean validarEmpleados() {
		
		String documentoEmpleado=JOptionPane.showInputDialog("Ingrese el documento del empleado");

		boolean retorna=false;
				
		
		if (arregloEmpleadosPlanilla!=null) {
			for (int i = 0; i < arregloEmpleadosPlanilla.size(); i++){
				if(documentoEmpleado.equals(arregloEmpleadosPlanilla.get(i).getDni())) {
					retorna=true;
				}
			}
		}
		
		
		if (retorna==false) {
			if (arregloEmpleadosEventual!=null) {
				for (int i = 0; i < arregloEmpleadosEventual.size(); i++) {
					if(documentoEmpleado.equals(arregloEmpleadosEventual.get(i).getDni())) {
						retorna=true;
					}
				}
			}
		}
		return retorna;
	}
	
	/**
	* Aqui validamos que ya existen los registros de medicos, 
	* pacientes y empleados para asi lograr crear una cita medica.
	*/
	
	private boolean validaRegistrosPrevios() {
		
		boolean retorno=false;
		
		if (arregloPacientes!=null && arregloMedicos!=null && arregloEmpleadosPlanilla!=null) {
			retorno=true;
		}
		return retorno;
	}
	
	/**
	* Este metodo tiene la funcion de 
	* registrar Citas medicas.
	*/
	
	private void registrarCitaMedica() {	
		
		/**
		* Aqui se realiza una validacion, donde se verifica 
		* que sea un empleado de planilla, el que este registrando citas.
		*/	
		
		
		if (validarEmpleadoPlanilla()) {
			
			/**
			 * Se crea la instancia de la clase CitaMedica.
			 */
			
			CitaMedica miCita=new CitaMedica();
				
			
		/**
		* Aqui se piden los datos necesarios para 
		* realizar el registro de una cita medica.
		*/
			
			
			miCita.setServicio(JOptionPane.showInputDialog("Ingrese el Servicio"));
			miCita.setPaciente(asignarPaciente());
			miCita.setMedico(asignaMedico());
			miCita.setFecha(JOptionPane.showInputDialog("Ingrese la fecha"));
			miCita.setHora(JOptionPane.showInputDialog("Ingrese la Hora"));

			JOptionPane.showMessageDialog(null, "La cita se ha registrado exitosamente!!!");
			
			arregloCitas.add(miCita);
			cantCitas++;

		}else{
			
			JOptionPane.showMessageDialog(null, "El documento no corresponde a un empleado por planilla","Advertencia",JOptionPane.WARNING_MESSAGE);

		}
		
	}

	
	
	/**
	 * Este metodo fue creado para poder asignar un medico a la cita.
	 */
	
	private Medico asignaMedico() {
		
		boolean repiteCiclo=false;
		Medico miMedico=null;
		
		do {
			String documentoPaciente=JOptionPane.showInputDialog("Ingrese el documento del Medico");

			for (int i = 0; i < arregloMedicos.size(); i++) {
							
				/**
				 * En esta parte validamos que existe el numero de documento 
				 * ingresado para luego asi poder asignar un medico a la cita.
				 */
				
				if(documentoPaciente.equals(arregloMedicos.get(i).getDni())) {
					miMedico=arregloMedicos.get(i);
				}
			}
			if (miMedico!=null) {
				repiteCiclo=false;
			}else{
				repiteCiclo=true;
				JOptionPane.showMessageDialog(null, "El documento no corresponde a un Medico\n" + "Por favor ingrese un documento valido","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		} while (repiteCiclo==true);
		return miMedico;
	}
	
	/**
	 * Este metodo fue creado con el fin de asignar 
	 * un paciente a la cita.
	 */	

	private Paciente asignarPaciente() {
		
		boolean repiteCiclo=false;
		Paciente miPaciente=null;
		
		do {
			String documentoPaciente=JOptionPane.showInputDialog("Ingrese el documento del paciente");

			for (int i = 0; i < arregloPacientes.size(); i++) {
				
				/**
				 * Aqui validamos que existe el numero de documento 
				 * ingresado para luego asignar un paciente a la cita.
				 */	
	
				if(documentoPaciente.equals(arregloPacientes.get(i).getDni())) {
					miPaciente=arregloPacientes.get(i);
				}
			}
			
			if (miPaciente!=null) {
				repiteCiclo=false;
				
			} else { 
				repiteCiclo=true;
				JOptionPane.showMessageDialog(null, "El documento no corresponde a un Paciente\n" + "Por favor ingrese un documento valido","Advertencia",JOptionPane.WARNING_MESSAGE);
			
			}

		} while (repiteCiclo==true);
		
		return miPaciente;
		
	}
	
	
	/**
	 * Este metodo fue creado para 
	 * realizar el registro de usuarios.
	 */
	
	private void cargarMenuRegistro() {
		String cad="REGISTRO DE USUARIOS\nregistro\n\n";
		cad+="1. Registrar Empleado\n";
		cad+="2. Registrar Paciente\n";
		cad+="3. Regresar\n\n";
		cad+="Ingrese una opcion";
		int opc = 0;
		for (int i = 0; i < 1; i++) {
			try {
				opc=Integer.parseInt(JOptionPane.showInputDialog(cad));
				
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,"Error ingreso texto");
				i--;
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Se genero un error");
				i--;
			}
		}

		switch (opc) {
		case 1:
			String tipoEmpleado="Tipo Empleado\n\n";
			tipoEmpleado+="1.Empleado Planilla\n";
			tipoEmpleado+="2.Empleado Eventual\n";
			tipoEmpleado+="3.Medico\n";
			tipoEmpleado+="4.Regresar\n\n";
			tipoEmpleado+="Ingrese el tipo de empleado";
			int tipo = 0;
			for (int i = 0; i < 1; i++) {
				try {
					tipo=Integer.parseInt(JOptionPane.showInputDialog(tipoEmpleado));
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Error ingreso texto");
					i--;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Se genero un error");
					i--;
				}
			}
			
			registrarEmpleado(tipo);
			break;
			
		case 2:
			registrarPaciente();
			break;
			
		case 3:
			JOptionPane.showMessageDialog(null, "Haz salido del registro de usuarios");
			break;
			
		default:
			JOptionPane.showMessageDialog(null, "Ingreso una opcion invalida");
			break;
		}
	}
	
	
	/**
	 * Este metodo fue creado para 
	 * realizar registros de los pacientes.
	 */	
	
	private void registrarPaciente() {
		int cantPaciente = 0;
		if (arregloPacientes==null) {
			for (int i = 0; i < 1; i++) {
				try {
					cantPaciente=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Medicos a registrar"));
					
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Error, ingreso texto");
					i--;
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Se genero un error");
					i--;
				}
			}
			arregloPacientes=new ArrayList<Paciente>();
			for (int j = 0; j < cantPaciente; j++) {
				JOptionPane.showMessageDialog(null, "Registro de datos paciente "+(j+1));
				Paciente miPaciente=new Paciente();
				
				//datos persona
				
				miPaciente.setDni(JOptionPane.showInputDialog("Ingrese el DNI del Paciente "+(j+1)));
				miPaciente.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre del Paciente "+(j+1)));
				miPaciente.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido del Paciente "+(j+1)));
				miPaciente.setFechaNacimiento(JOptionPane.showInputDialog("Ingrese la fecha de Nacimiento del Paciente "+(j+1)));
				miPaciente.setDireccion(JOptionPane.showInputDialog("Ingrese la Direccion del Paciente "+(j+1)));
				miPaciente.setCiudad(JOptionPane.showInputDialog("Ingrese Ciudad del Paciente "+(j+1)));

				//datos paciente

				miPaciente.setNumHistoria(JOptionPane.showInputDialog("Ingrese el Numero de la Historia Clinica del Paciente "+(j+1)));
				miPaciente.setSexo(JOptionPane.showInputDialog("Ingrese el Sexo del Paciente "+(j+1)));
				miPaciente.setGrupoSanguineo(JOptionPane.showInputDialog("Ingrese el Grupo Sanguineo del Paciente "+(j+1)));
				int cantMedic = 0;
				for (int k = 0; k < 1; k++) {
					try {
						cantMedic=Integer.parseInt(JOptionPane.showInputDialog("Cuantos medicamentos a los que es alergico el paciente "+miPaciente.getNombre()+" desea Registrar?"));
						
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,"Error, ingreso texto");
						k--;
					}catch (Exception e) {
						JOptionPane.showMessageDialog(null,"Se genero un error");
						k--;
					}
				}
				ArrayList<String> arregloMedicamentos = new ArrayList<String>();
				String medic = "";
				for (int l = 0; l < cantMedic; l++) {
					medic=JOptionPane.showInputDialog("Ingrese el medicamento "+(l+1));
					arregloMedicamentos.add(medic);
				}

				miPaciente.setListaMedicamentos(arregloMedicamentos);
				arregloPacientes.add(miPaciente);
			}
			JOptionPane.showMessageDialog(null, "El registro de Pacientes se ha realizado con exito");

		}else{
			JOptionPane.showMessageDialog(null, "Ya se han registrado los Pacientes","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Este metodo metodo fue creado para realizar 
	 * registros de empleados, ya sean de planilla, 
	 * eventuales o medicos.
	 */		
		
	private void registrarEmpleado(int tipo) {
		
		switch (tipo) {
			case 1:
				int cantEmpleadoPlanilla = 0;
				if (arregloEmpleadosPlanilla==null) {
					for (int i = 0; i < 1; i++) {
						try {
							cantEmpleadoPlanilla=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de empleados por planilla a registrar"));
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Error, ingreso texto");
							i--;
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Se genero un error");
							i--;
						}
					}
					
					arregloEmpleadosPlanilla=new ArrayList<EmpleadoPlanilla>();

					for (int j = 0; j < cantEmpleadoPlanilla; j++) {
						JOptionPane.showMessageDialog(null, "Registro de datos Empleado Planilla "+(j+1));
						asignarValoresGeneralesEmpleado("Empleado por Planilla "+(j+1));
						EmpleadoPlanilla miEmpleadoPlanilla=new EmpleadoPlanilla();

						//datos persona
						
						miEmpleadoPlanilla.setDni(dni);
						miEmpleadoPlanilla.setNombre(nombre);
						miEmpleadoPlanilla.setApellido(apellido);
						miEmpleadoPlanilla.setFechaNacimiento(fechaNacimiento);
						miEmpleadoPlanilla.setDireccion(direccion);
						miEmpleadoPlanilla.setCiudad(ciudad);
						
						//datos empleado
						
						miEmpleadoPlanilla.setCodEmpleado(codEmpleado);
						miEmpleadoPlanilla.setNumHorasExtras(numHorasExt);
						miEmpleadoPlanilla.setFechaIngreso(fechaIngreso);
						miEmpleadoPlanilla.setArea(area);
						miEmpleadoPlanilla.setCargo(cargo);
						
						//datos empleado planilla
						for (int k = 0; k < 1; k++) {
							try {
								miEmpleadoPlanilla.setSalarioMensual(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario mensual del empleado por Planilla "+(j+1))));
								miEmpleadoPlanilla.setPorcentajeAdicional(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje adicional del empleado por planilla "+(j+1))));
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,"Error, ingreso texto");
								k--;
							}catch (Exception e) {
								JOptionPane.showMessageDialog(null,"Se genero un error");
								k--;
							}
						}
						
						arregloEmpleadosPlanilla.add(miEmpleadoPlanilla);

					}
					JOptionPane.showMessageDialog(null, "El registro de Empleados por Planilla se ha realizado con exito");

				}else{
					JOptionPane.showMessageDialog(null, "Ya se han registrado los empleados por Planilla","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 2:
				int cantEmpleadoEventual = 0;
				if (arregloEmpleadosEventual==null) {
					for (int i = 0; i < 1; i++) {
						try {
							cantEmpleadoEventual=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de empleados eventuales a registrar"));
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Error, ingreso texto");
							i--;
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Se genero un error");
							i--;
						}
					}
					arregloEmpleadosEventual=new ArrayList<EmpleadoEventual>();

					for (int j = 0; j < cantEmpleadoEventual; j++) {
						JOptionPane.showMessageDialog(null, "Registro de datos Empleado Eventual "+(j+1));
						asignarValoresGeneralesEmpleado("Empleado Eventual "+(j+1));
						EmpleadoEventual miEmpleadoEventual=new EmpleadoEventual();

						//datos persona
						
						miEmpleadoEventual.setDni(dni);
						miEmpleadoEventual.setNombre(nombre);
						miEmpleadoEventual.setApellido(apellido);
						miEmpleadoEventual.setFechaNacimiento(fechaNacimiento);
						miEmpleadoEventual.setDireccion(direccion);
						miEmpleadoEventual.setCiudad(ciudad);
						
						//datos empleado
						
						miEmpleadoEventual.setCodEmpleado(codEmpleado);
						miEmpleadoEventual.setNumHorasExtras(numHorasExt);
						miEmpleadoEventual.setFechaIngreso(fechaIngreso);
						miEmpleadoEventual.setArea(area);
						miEmpleadoEventual.setCargo(cargo);
						
						//datos empleado eventual
				
						for (int k = 0; k < 1; k++) {
							try {
								miEmpleadoEventual.setHonorariosHora(Double.parseDouble(JOptionPane.showInputDialog("Ingrese los honorarios hora del Empleado Eventual "+(j+1))));
								
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,"Error, ingreso texto");
								k--;
							}catch (Exception e) {
								JOptionPane.showMessageDialog(null,"Se genero un error");
								k--;
							}
						}
						
						miEmpleadoEventual.setFechaTerminoContrato(JOptionPane.showInputDialog("Ingrese la fecha de terminacion de contrato del Empleado Eventual"+(j+1)));
						arregloEmpleadosEventual.add(miEmpleadoEventual);

					}
					JOptionPane.showMessageDialog(null, "El registro de Empleados Eventuales se ha realizado con exito");

				}else{
					JOptionPane.showMessageDialog(null, "Ya se han registrado los empleados Eventuales","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 3:
				int cantMedicos = 0;
				if (arregloMedicos==null) {
					for (int i = 0; i < 1; i++) {
						try {
							cantMedicos=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Medicos a registrar"));
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,"Error, ingreso texto");
							i--;
						}
					}
					arregloMedicos=new ArrayList<Medico>();
					for (int j = 0; j < cantMedicos; j++) {
						JOptionPane.showMessageDialog(null, "Registro de datos Medico "+(j+1));
						asignarValoresGeneralesEmpleado("Medico "+(j+1));
						Medico miMedico=new Medico();
						
						//datos persona
						
						miMedico.setDni(dni);
						miMedico.setNombre(nombre);
						miMedico.setApellido(apellido);
						miMedico.setFechaNacimiento(fechaNacimiento);
						miMedico.setDireccion(direccion);
						miMedico.setCiudad(ciudad);
						
						//datos empleado
						
						miMedico.setCodEmpleado(codEmpleado);
						miMedico.setNumHorasExtras(numHorasExt);
						miMedico.setFechaIngreso(fechaIngreso);
						miMedico.setArea(area);
						miMedico.setCargo(cargo);
						
						//datos medico
				
						miMedico.setEspecialidad(JOptionPane.showInputDialog("Ingrese la especialidad del Medico "+(j+1)));
						for (int k = 0; k < 1; k++) {
							try {
								miMedico.setNumConsultorio(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de consultorio del Medico "+(j+1))));
								
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null,"Error, ingreso texto");
								k--;
							}catch (Exception e) {
								JOptionPane.showMessageDialog(null,"Se genero un error");
								k--;
							}
						}
						arregloMedicos.add(miMedico);
					}
					JOptionPane.showMessageDialog(null, "El registro de Medicos se ha realizado con exito");

				}else{
					JOptionPane.showMessageDialog(null, "Ya se han registrado los Medicos","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				break;
				
			case 4: 
				JOptionPane.showMessageDialog(null, "Haz salido del registro de empleados");
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Ingreso una opcion invalida");
				break;
		}
	}
	
	
	/**
	 * Este metodo fue creado con el fin de asignar 
	 * y realizar la captura de valores generales en 
	 * las distintas variables
	 */	

	private void asignarValoresGeneralesEmpleado(String tipo) {

		dni=JOptionPane.showInputDialog("Ingrese el DNI del "+tipo);
		nombre=JOptionPane.showInputDialog("Ingrese el nombre del "+tipo);
		apellido=JOptionPane.showInputDialog("Ingrese el apellido del "+tipo);
		fechaNacimiento=JOptionPane.showInputDialog("Ingrese la fecha Nacimiento del "+tipo);
		direccion=JOptionPane.showInputDialog("Ingrese la direccion del "+tipo);
		ciudad=JOptionPane.showInputDialog("Ingrese la ciudad del "+tipo);
		codEmpleado=JOptionPane.showInputDialog("Ingrese el cod Empleado del "+tipo);
		numHorasExt=JOptionPane.showInputDialog("Ingrese el num de Horas Extras del "+tipo);
		fechaIngreso=JOptionPane.showInputDialog("Ingrese la fecha Ingreso del "+tipo);
		area=JOptionPane.showInputDialog("Ingrese el area del "+tipo);
		cargo=JOptionPane.showInputDialog("Ingrese el cargo del "+tipo);
		
	}
}

