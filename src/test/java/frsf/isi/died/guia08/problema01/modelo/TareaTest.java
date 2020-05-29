package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;

import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;

public class TareaTest {

	@Test
	public void asignarEmpleadoTest() throws AsignacionFallidaException {
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		tarea1.asignarEmpleado(contratado);
		
		
		
	}
	
	@Test (expected = AsignacionFallidaException.class)
	public void asignarEmpleadoFallidoTest() throws AsignacionFallidaException
	{
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Empleado efectivo = new Empleado(2039455, "Roman Riquelme", 4.75, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		tarea1.asignarEmpleado(contratado);
		tarea1.asignarEmpleado(efectivo);
	}

}
