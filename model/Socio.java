package model;

import java.util.ArrayList;

public class Socio {
	/**Identificador de socio - autoincremental*/
	private int id;
	
	/**Nombre/s del socio*/
	private String nombre;
	
	/**Apellido/s del socio*/
	private String apellido;
	
	/**Alquileres*/
	private int alquileres;
	
	/**Alquileres*/
	private int alquileresHoy;
	
	/**Numero DNI*/
	private String DNI;
	
	/**Domicilio*/
	private String dom;
	
	/**Teléfono*/
	private String tel;
	
	/**Email*/
	private String email;
	
	/**Observaciones */
	private String observaciones;
	
	/**Avisar*/
	private Boolean avisar;
	
	/**Alquiladas*/
	private ArrayList<Pelicula> aAlquiladas;

	

	public Socio(int id, String nombre, String apellido, int alquileres,
			String dNI, String dom, String tel, String email,
			String observaciones, Boolean avisar, ArrayList<Pelicula> aAlquiladas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.alquileres = alquileres;
		this.DNI = dNI;
		this.dom = dom;
		this.tel = tel;
		this.email = email;
		this.observaciones = observaciones;
		this.avisar = avisar;
		this.aAlquiladas = aAlquiladas;
		this.alquileresHoy = 0;
	}
	
	public String toString()
	{
		String res = new String((this.id+" | ")+
		(this.nombre+" | ")+
		(this.apellido +" | ")+
		(this.alquileres+" | ")+
		(this.DNI+" | ")+
		(this.dom+" | ")+
		(this.tel+" | ")+
		(this.email+" | ")+
		(this.observaciones+" | ")+
		(this.avisar+" | ")+ System.getProperty("line.separator"));
		//(this.aAlquiladas.toString())+ System.getProperty("line.separator"));
		
		return res;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(int alquileres) {
		this.alquileres = alquileres;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean getAvisar() {
		return avisar;
	}

	public void setAvisar(Boolean avisar) {
		this.avisar = avisar;
	}

	public ArrayList<Pelicula> getaAlquiladas() {
		return aAlquiladas;
	}

	public void setaAlquiladas(ArrayList<Pelicula> aAlquiladas) {
		this.aAlquiladas = aAlquiladas;
	}
	
	public int getAlquileresHoy() {
		return alquileresHoy;
	}

	public void setAlquileresHoy(int alquileresHoy) {
		this.alquileresHoy = alquileresHoy;
	}
}
