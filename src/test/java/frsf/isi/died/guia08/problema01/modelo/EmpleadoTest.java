package frsf.isi.died.guia08.problema01.modelo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;


public class EmpleadoTest {

	
	@Test
	public void testSalarioContratado() {
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 3.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		Double esperado1 = 14.625;
		try {
			contratado.asignarTarea(tarea1);
			contratado.comenzar(1);
			contratado.finalizar(1);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			
		}
		assertEquals(esperado1, contratado.salario());
	}
	
	@Test
	public void testSalarioEfectivo() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 2);
		Double esperado1 = 4.80;
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.comenzar(1);
			efectivo.finalizar(1);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			
		}
		assertEquals(esperado1, efectivo.salario());
	}

	@Test
	public void testCostoTarea() {
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		Double esperado1 = 11.25;
		assertEquals(esperado1, contratado.costoTarea(tarea1));

	}

	@Test
	public void testAsignarTareaExitosa() {
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		boolean esperado = false;
		try {
			esperado = contratado.asignarTarea(tarea1);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			
		}
		assertTrue(esperado);
	}
	
	@Test(expected = AsignacionFallidaException.class)
	public void testAsignarTareaContratadoFallida() {
		Empleado contratado = new Empleado(2039255, "Maximiliano Pinto", 3.75, Tipo.CONTRATADO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		Tarea tarea2 = new Tarea(2, "Mantener SO", 3);
		Tarea tarea3 = new Tarea(3, "Mantener SO", 3);
		Tarea tarea4 = new Tarea(4, "Mantener SO", 3);
		Tarea tarea5 = new Tarea(5, "Mantener SO", 3);
		Tarea tarea6 = new Tarea(6, "Mantener SO", 3);
		try {
			contratado.asignarTarea(tarea1);
			contratado.asignarTarea(tarea2);
			contratado.asignarTarea(tarea3);
			contratado.asignarTarea(tarea4);
			contratado.asignarTarea(tarea5);
			contratado.asignarTarea(tarea6);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test(expected = AsignacionFallidaException.class)
	public void testAsignarTareaEfectivoFallida() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		Tarea tarea2 = new Tarea(2, "Mantener SO", 3);
		Tarea tarea3 = new Tarea(3, "Mantener SO", 3);
		Tarea tarea4 = new Tarea(4, "Mantener SO", 3);
		Tarea tarea5 = new Tarea(5, "Mantener SO", 3);
		Tarea tarea6 = new Tarea(6, "Mantener SO", 3);
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.asignarTarea(tarea2);
			efectivo.asignarTarea(tarea3);
			efectivo.asignarTarea(tarea4);
			efectivo.asignarTarea(tarea5);
			efectivo.asignarTarea(tarea6);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			
		}
	}

	@Test
	public void testComenzarInteger() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.comenzar(1);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Assert.assertNotEquals(null, tarea1.getFechaInicio());
	}

	@Test
	public void testFinalizarInteger() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.comenzar(1);
			efectivo.finalizar(1);
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		Assert.assertNotEquals(null, tarea1.getFechaFin());
	}

	@Test
	public void testComenzarIntegerString() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime Inicio = LocalDateTime.parse("06-11-2020 21:30", formatter);
		
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.comenzar(1, "06-11-2020 21:30");
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals(Inicio, tarea1.getFechaInicio());
	}

	@Test
	public void testFinalizarIntegerString() {
		Empleado efectivo = new Empleado(2037255, "Damian Wayne", 2.00, Tipo.EFECTIVO);
		Tarea tarea1 = new Tarea(1, "Mantener SO", 3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime Fin = LocalDateTime.parse("06-11-2020 22:30", formatter);
		
		try {
			efectivo.asignarTarea(tarea1);
			efectivo.comenzar(1, "06-11-2020 21:30");
			efectivo.finalizar(1, "06-11-2020 22:30");
		} catch (AsignacionFallidaException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		assertEquals(Fin, tarea1.getFechaFin());
	}

}
