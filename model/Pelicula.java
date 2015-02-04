package model;

import java.util.Date;

public class Pelicula {
	
	/**Identificador de socio - autoincremental*/
	private int id;
	
	/**Título de la película*/
	private String nombre;
	
	/**Notas de la película*/
	private String notas;
	
	/**Alquilada*/
	private Boolean alquilada;
	
	/**Alquileres*/
	private int alquileres;
	
	/**Retrasos*/
	private int retrasos;
	
	/**Fecha de alta*/
	private Date fechaAlta;
	
	/**PVP*/
	private int PVP;

	public int getPVP() {
		return PVP;
	}

	public void setPVP(int pVP) {
		PVP = pVP;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Boolean getAlquilada() {
		return alquilada;
	}

	public void setAlquilada(Boolean alquilada) {
		this.alquilada = alquilada;
	}

	public int getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(int alquileres) {
		this.alquileres = alquileres;
	}

	public int getRetrasos() {
		return retrasos;
	}

	public void setRetrasos(int retrasos) {
		this.retrasos = retrasos;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Pelicula(int id, String nombre, Boolean alquilada, int alquileres,
			int retrasos, Date fechaAlta, int PVP, String notas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alquilada = alquilada;
		this.alquileres = alquileres;
		this.retrasos = retrasos;
		this.fechaAlta = fechaAlta;
		this.PVP = PVP;
		this.notas = notas;
	}
	
	public String toString()
	{
		String res = new String((this.id+" | ")+
		(this.nombre+" | ")+
		(this.notas +" | ")+
		(this.alquilada+" | ")+
		(this.alquileres+" | ")+
		(this.retrasos+" | ")+
		(this.fechaAlta +" | ")+
		(this.PVP+" | ")+ System.getProperty("line.separator"));
		//(this.aAlquiladas.toString())+ System.getProperty("line.separator"));
		
		return res;
	}

}
