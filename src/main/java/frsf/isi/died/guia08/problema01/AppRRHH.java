package frsf.isi.died.guia08.problema01;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import frsf.isi.died.guia08.problema01.modelo.AsignacionFallidaException;
import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;
import frsf.isi.died.guia08.problema01.modelo.Tarea;

public class AppRRHH {

	private List<Empleado> empleados;
	
	
	
	public AppRRHH()
	{
		this.empleados = new ArrayList<Empleado>();
	}
	
	public List retornarEmpleados()
	{
		return this.empleados;
	}
	
	public void agregarEmpleadoContratado(Integer cuil,String nombre,Double costoHora) {
		// crear un empleado
		// agregarlo a la lista
		Empleado emp = new Empleado(cuil, nombre, costoHora, Tipo.CONTRATADO);
		this.empleados.add(emp);
	}
	
	public void agregarEmpleadoEfectivo(Integer cuil,String nombre,Double costoHora) {
		// crear un empleado
		// agregarlo a la lista
		Empleado emp = new Empleado(cuil, nombre, costoHora, Tipo.EFECTIVO);
		this.empleados.add(emp);
	}
	
	public void asignarTarea(Integer cuil,Integer idTarea,String descripcion,Integer duracionEstimada) throws AsignacionFallidaException {
		// crear un empleado
		// con el método buscarEmpleado() de esta clase
		// agregarlo a la lista
		Optional<Empleado> emp = this.buscarEmpleado(e -> e.getCuil().equals(cuil));
		if(emp.isPresent())
		{
			Tarea tarea = new Tarea(idTarea, descripcion,  duracionEstimada);
			tarea.asignarEmpleado(emp.get());
		}
		else
		{
			throw new AsignacionFallidaException("Empleado inexistente.");
		}
		
	}
	
	public void empezarTarea(Integer cuil,Integer idTarea) throws AsignacionFallidaException {
		// busca el empleado por cuil en la lista de empleados
		// con el método buscarEmpleado() actual de esta clase
		// e invoca al método comenzar tarea
		Optional<Empleado> emp = this.buscarEmpleado(e -> e.getCuil().equals(cuil));
		if(emp.isPresent())
		{
			emp.get().comenzar(idTarea);
		}
	}
	
	public void terminarTarea(Integer cuil,Integer idTarea) throws AsignacionFallidaException {
		// crear un empleado
		// agregarlo a la lista
		Optional<Empleado> emp = this.buscarEmpleado(e -> e.getCuil().equals(cuil));
		if(emp.isPresent())
		{
			emp.get().finalizar(idTarea);
		}
	}

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) throws FileNotFoundException, IOException {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
		try(Reader fileReader = new FileReader(nombreArchivo)) {
			try(BufferedReader in = new BufferedReader(fileReader)){
			String linea = null;
			while((linea = in.readLine())!=null) {
			String[] fila = linea.split(";");
			Empleado emp = new Empleado(Integer.valueOf(fila[0]), String.valueOf(fila[1]), Double.valueOf(fila[2]), Tipo.CONTRATADO);
			empleados.add(emp);
			}
			}
			}
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) throws FileNotFoundException, IOException {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
		try(Reader fileReader = new FileReader(nombreArchivo)) {
			try(BufferedReader in = new BufferedReader(fileReader)){
			String linea = null;
			while((linea = in.readLine())!=null) {
			String[] fila = linea.split(";");
			Empleado emp = new Empleado(Integer.valueOf(fila[0]), String.valueOf(fila[1]), Double.valueOf(fila[2]), Tipo.EFECTIVO);
			empleados.add(emp);
			}
			}
			}
	}

	public void cargarTareasCSV(String nombreArchivo) throws FileNotFoundException, IOException, AsignacionFallidaException {
		// leer datos del archivo
		// cada fila del archivo tendrá:
		// cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
		try(Reader fileReader = new FileReader(nombreArchivo)) {
		try(BufferedReader in = new BufferedReader(fileReader)){
		String linea = null;
		while((linea = in.readLine())!=null) {
		String[] fila = linea.split(";");
		Tarea t = new Tarea();
		t.setId(Integer.valueOf(fila[0]));
		t.setDescripcion(fila[1]);
		t.setDuracionEstimada(Integer.valueOf(fila[2]));
		Integer cuilEmpleado = Integer.valueOf(fila[3]);
		Optional<Empleado> emp = this.buscarEmpleado(e -> e.getCuil().equals(cuilEmpleado));
		if(emp.isPresent())
		{
			emp.get().asignarTarea(t);
		}
		else
		{
			throw new AsignacionFallidaException("No se pudo cargar el empleado debido a que no existe.");
		}
		}
		}
		}

	}
	
	private void guardarTareasTerminadasCSV() throws IOException {
		// guarda una lista con los datos de la tarea que fueron terminadas
		// y todavía no fueron facturadas
		// y el nombre y cuil del empleado que la finalizó en formato CSV 
		for(Empleado unEmpleado: this.empleados)
		{
			for(Tarea unaTarea: unEmpleado.getTareasAsignadas())
			{
				if(unaTarea.getFacturada() == false && unaTarea.getFechaFin() != null)
				{
					try(Writer fileWriter= new FileWriter("tareas.csv",true)) {
						try(BufferedWriter out = new BufferedWriter(fileWriter)){
						out.write(unaTarea.asCsv()+ System.getProperty("line.separator"));
						}
						}
				}

			}
		}
	}
	
	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}

	public Double facturar() throws IOException {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}
}
