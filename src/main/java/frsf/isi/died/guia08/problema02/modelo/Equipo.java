package frsf.isi.died.guia08.problema02.modelo;

import java.util.List;

import frsf.isi.died.guia08.problema02.modelo.Jugador.Posicion;

public class Equipo {

	private String nombre;
	private List<Jugador> plantel;
	private List<Partido> partidosJugados;
	
	public Integer puntos() {
		return 0;
	}

	public Integer partidosGanados() {
		return 0;
	}
	
	public void agregarJugador(Jugador j)
	{
		switch(j.getPosicion())
		{
		case ARQUERO:
			if(this.cantidadArqueros())
			{
				plantel.add(j);
			}
			break;
		case DEFENSOR:
			if(this.cantidadDefensores())
			{
				plantel.add(j);
			}
			break;
		case VOLANTE:
			if(this.cantidadVolantes())
			{
				plantel.add(j);
			}
			break;
		case DELANTERO:
			if(this.cantidadDelanteros())
			{
				plantel.add(j);
			}
			break;
		}
		
	}
	
	public boolean cantidadArqueros()
	{
		boolean resultado = false;
		Integer cantidad = 0;
		for(Jugador unJugador: this.plantel)
		{
			if(unJugador.getPosicion() == Posicion.ARQUERO)
			{
				cantidad++;
			}
		}
		if(cantidad <= 3)
		{
			resultado = true;
		}
		
		return resultado;
	}
	
	public boolean cantidadDefensores()
	{
		boolean resultado = false;
		Integer cantidad = 0;
		for(Jugador unJugador: this.plantel)
		{
			if(unJugador.getPosicion() == Posicion.DEFENSOR)
			{
				cantidad++;
			}
		}
		if(cantidad <= 9)
		{
			resultado = true;
		}
		
		return resultado;
	}
	
	public boolean cantidadVolantes()
	{
		boolean resultado = false;
		Integer cantidad = 0;
		for(Jugador unJugador: this.plantel)
		{
			if(unJugador.getPosicion() == Posicion.VOLANTE)
			{
				cantidad++;
			}
		}
		if(cantidad <= 9)
		{
			resultado = true;
		}
		
		return resultado;
	}
	
	public boolean cantidadDelanteros()
	{
		boolean resultado = false;
		Integer cantidad = 0;
		for(Jugador unJugador: this.plantel)
		{
			if(unJugador.getPosicion() == Posicion.DELANTERO)
			{
				cantidad++;
			}
		}
		if(cantidad <= 6)
		{
			resultado = true;
		}
		
		return resultado;
	}

}
