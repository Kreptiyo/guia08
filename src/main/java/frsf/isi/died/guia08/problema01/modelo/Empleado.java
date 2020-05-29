package frsf.isi.died.guia08.problema01.modelo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Empleado {

	public enum Tipo { CONTRATADO,EFECTIVO}; 
	
	private Integer cuil;
	private String nombre;
	private Tipo tipo;
	private Double costoHora;
	private List<Tarea> tareasAsignadas;
	
	private Function<Tarea, Double> calculoPagoPorTarea;		
	private Predicate<Tarea> puedeAsignarTarea;

	
	public Empleado (Integer cuil, String nombre, Double costoHora, Tipo tipo)
	{
		super();
		this.cuil = cuil;
		this.nombre = nombre;
		this.costoHora = costoHora;
		this.tipo = tipo;
		this.tareasAsignadas = new ArrayList<Tarea>();
	}
	
	public Integer getCuil() {
		return cuil;
	}

	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Double getCostoHora() {
		return costoHora;
	}

	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}

	public List<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	public Function<Tarea, Double> getCalculoPagoPorTarea() {
		return calculoPagoPorTarea;
	}

	public void setCalculoPagoPorTarea(Function<Tarea, Double> calculoPagoPorTarea) {
		this.calculoPagoPorTarea = calculoPagoPorTarea;
	}

	public Predicate<Tarea> getPuedeAsignarTarea() {
		return puedeAsignarTarea;
	}

	public void setPuedeAsignarTarea(Predicate<Tarea> puedeAsignarTarea) {
		this.puedeAsignarTarea = puedeAsignarTarea;
	}

	public Double salario() {
		// cargar todas las tareas no facturadas
		// calcular el costo
		// marcarlas como facturadas.
		Double salarioFinal = 0.0;
		for(Tarea unaTarea: this.tareasAsignadas)
		{
			if(unaTarea.getFacturada() == false)
			{
				switch(this.tipo)
				{
				case CONTRATADO:
					if(duracionTarea(unaTarea) < unaTarea.getDuracionEstimada())
					{
						salarioFinal += this.costoTarea(unaTarea) * 1.30;
					}
					else if((duracionTarea(unaTarea) / 4) > 2)
					{
						salarioFinal += this.costoTarea(unaTarea) * 0.75;
					}
					else
					{
						salarioFinal += this.costoTarea(unaTarea);
					}
					unaTarea.setFacturada(true);
					break;
				case EFECTIVO:
					if(duracionTarea(unaTarea) < unaTarea.getDuracionEstimada())
					{
						salarioFinal += this.costoTarea(unaTarea) * 1.20;
						unaTarea.setFacturada(true);
					}
					else
					{
						salarioFinal += this.costoTarea(unaTarea);
					}
					unaTarea.setFacturada(true);
					break;
				}
				
			}
		}
		
		return salarioFinal;
	}
	
	public Integer duracionTarea(Tarea t)
	{
		Integer duracion;
		duracion = (t.getFechaFin().getDayOfMonth() - t.getFechaFin().getDayOfMonth()) * 4;
		return duracion;
	}
	
	/**
	 * Si la tarea ya fue terminada nos indica cuaal es el monto según el algoritmo de calculoPagoPorTarea
	 * Si la tarea no fue terminada simplemente calcula el costo en base a lo estimado.
	 * @param t
	 * @return
	 */
	public Double costoTarea(Tarea t) 
	{
		return this.costoHora * t.getDuracionEstimada();
	}
	
	
	public Boolean asignarTarea(Tarea t) throws AsignacionFallidaException
	{
		
		Boolean condicion = false;
		
		if(t.getEmpleadoAsignado() != null)
		{
			throw new AsignacionFallidaException("Ya se encuentra un empleado asignado a esta tarea.");
		}
		else if(t.getFechaFin() != null)
		{
			throw new AsignacionFallidaException("Esta tarea ya fue finalizada.");
		}
		else
			{
				switch(this.tipo)
				{
					case CONTRATADO:
						if(this.tareasAsignadasPendientes() < 5)
						{
							this.tareasAsignadas.add(t);
							condicion  = true;
						}
						else
						{
							throw new AsignacionFallidaException("El empleado CONTRATADO ya posee mas de 5 tareas pendientes.");
						}
							break;
					case EFECTIVO:
						if(this.horasDeTrabajo() < 15)
						{
							this.tareasAsignadas.add(t);
							condicion = true;
						}
						else
						{
							throw new AsignacionFallidaException("El empleado EFECTIVO ya posee mas de 15 horas de trabajo.");
						}
				}
		
			}
		
		return condicion;
	}
	
	
	public long tareasAsignadasPendientes()
	{
		return this.tareasAsignadas.stream()
				   .filter(t -> t.getFechaFin() == null)
				   .count();
	}
	
	public Integer horasDeTrabajo()
	{
		return this.tareasAsignadas.stream()
				   .filter(t -> t.getFechaFin() == null)
				   .mapToInt(t -> t.getDuracionEstimada())
				   .sum();
		
	}
	
	public void comenzar(Integer idTarea) throws AsignacionFallidaException{
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de inicio la fecha y hora actual

		for(Tarea unaTarea: this.tareasAsignadas)
		{
			if(unaTarea.getId() == idTarea)
			{
				unaTarea.setFechaInicio(LocalDateTime.now());
			}
			else
			{
				throw new AsignacionFallidaException("La tarea cuyo id ha sido proporcionado no existe.");
			}
		}
	}
	
	public void finalizar(Integer idTarea) throws AsignacionFallidaException
	{
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
		
		for(Tarea unaTarea: this.tareasAsignadas)
		{
			if(unaTarea.getId() == idTarea)
			{
				unaTarea.setFechaFin(LocalDateTime.now());
			}
			else
			{
				throw new AsignacionFallidaException("La tarea cuyo id ha sido proporcionado no existe.");
			}
		}
	}

	public void comenzar(Integer idTarea,String fecha) throws AsignacionFallidaException
	{
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime fechaInicio = LocalDateTime.parse(fecha, formatter);
		
		for(Tarea unaTarea: this.tareasAsignadas)
		{
			if(unaTarea.getId() == idTarea)
			{
				unaTarea.setFechaInicio(fechaInicio);
			}
			else
			{
				throw new AsignacionFallidaException("La tarea cuyo id ha sido proporcionado no existe.");
			}
		}
	}
	
	
	public void finalizar(Integer idTarea,String fecha) throws AsignacionFallidaException
	{
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime fechaFin = LocalDateTime.parse(fecha, formatter);
		
		for(Tarea unaTarea: this.tareasAsignadas)
		{
			if(unaTarea.getId() == idTarea)
			{
				unaTarea.setFechaFin(fechaFin);
			}
			else
			{
				throw new AsignacionFallidaException("La tarea cuyo id ha sido proporcionado no existe.");
			}
		}
	}
}
