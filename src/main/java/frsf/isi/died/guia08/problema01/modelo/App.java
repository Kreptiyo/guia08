package frsf.isi.died.guia08.problema01.modelo;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import frsf.isi.died.guia08.problema01.AppRRHH;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;

public class App {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		AppRRHH app = new AppRRHH();
		
		app.cargarEmpleadosContratadosCSV("C:\\Users\\Kreptile\\eclipse-workspace\\guia08-master\\jaja.csv");
	
		
		
		

	}

}
