package frsf.isi.died.guia08.problema01;

import static org.junit.Assert.*;


import org.junit.Test;

import frsf.isi.died.guia08.problema01.modelo.AsignacionFallidaException;

public class AppRRHHTest {

	@Test
	public void agregarEmpleadoContratadoTest() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(230, "Maximiliano Pinto", 3.45);
	}
	
	@Test
	public void agregarEmpleadoEfectivoTest() {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoEfectivo(235, "Juan Carlos Tsuze", 5.45);
	}
	
	@Test
	public void asignarTareaTest() throws AsignacionFallidaException {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(230, "Maximiliano Pinto", 3.45);
		app.asignarTarea(230, 1, "Mantencion del servidor", 4);
	}
	
	@Test (expected = AsignacionFallidaException.class)
	public void asignarTareaFallidoTest() throws AsignacionFallidaException {
		AppRRHH app = new AppRRHH();
		app.agregarEmpleadoContratado(230, "Maximiliano Pinto", 3.45);
		app.asignarTarea(235, 1, "Mantencion del servidor", 4);
	}

}
