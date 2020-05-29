package frsf.isi.died.guia08.problema02.modelo;

import java.time.LocalDateTime;

public class Jugador 
{
	public enum Posicion { ARQUERO, DEFENSOR, VOLANTE, DELANTERO}; 
	
	private String nombre;
	private Integer dni;
	private LocalDateTime fechaNacimiento;
	private Posicion posicion;
	private double altura;
	private double peso;
	private boolean pateaDerecha;
	private boolean pateaIzquierda;
	
	public Jugador(String nombre, Integer dni, LocalDateTime fechaNacimiento, Posicion posicion, double altura,
			double peso, boolean pateaDerecha, boolean pateaIzquierda) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.posicion = posicion;
		this.altura = altura;
		this.peso = peso;
		this.pateaDerecha = pateaDerecha;
		this.pateaIzquierda = pateaIzquierda;
	}
	
	public Posicion getPosicion()
	{
		return this.posicion;
	}
	
}
