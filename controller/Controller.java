package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Pelicula;
import model.Socio;
import model.Videoclub;

public class Controller {
	
	public Boolean haVistoAlgoParecido(String idPeli, String idSocio)
	{
		return Videoclub.getInstance().getHavistoAlgoParecido(idPeli, idSocio);
	}
	
	public Socio buscaSocio(String clave)
	{
		return Videoclub.getInstance().getSocio(clave);
	}
	
	public void buscaPendientesDeudasSocio(String tarjeta)
	{
		//Uso el campo codigo de cliente de la vista obtenido en la búsqueda previa	
		Videoclub.getInstance().buscaPendientesDeudasSocio(tarjeta);

	}

	/**
	 * Ñapa para meter a pelo el codigo de la película en v2.4
	 * @param clave
	 */
	public int getIDPelicula(String clave)
	{
		return Videoclub.getInstance().getIDPelicula("TITULO", clave.toUpperCase());
	}
	public void buscaPelicula(String clave)
	{
		//Depende de la longitud
		//si la longitud es de hasta 4 es un codigo de socio
		//si la longitud es mayor de 4 y menor de 9 y es un numero es un DNI
		//si no es un nombre
		if (clave.length()<=4)
			try {
				Integer.valueOf(clave);
				Videoclub.getInstance().getPelicula("ID", clave);

			} catch (NumberFormatException e) {
				Videoclub.getInstance().getPelicula("TITULO", clave.toUpperCase());
			}
		else
			Videoclub.getInstance().getPelicula("TITULO", clave.toUpperCase());
	}

	public void cuandoHaVistoElSocioLaPeliculaDB(String titulo, String tarjeta) {
		// TODO Auto-generated method stub
		Videoclub.getInstance().cuandoHaVistoElSocioLaPeliculaDB(titulo, tarjeta);
	}
	
	
	/**
	 * Puede dar problemas si la película se llama 300 pero bueno...
	 * @param clave
	 */
	public void busca3SociosPelicula(String clave) {
		Videoclub.getInstance().get3SociosPeliculaPendientesDeudasRetrasos("TITULO",clave);
	}
	
	/**
	 * Actualiza lo que hayan tocado de la película
	 * @param cod
	 * @param tit
	 * @param pases
	 * @param PVP
	 * @param retrasos
	 * @param fecha
	 * @param alquilada
	 */
	public void updatePelicula(String cod, String tit, String pases, String PVP, String retrasos, Date fecha, String alquilada)
	{
		
		Videoclub.getInstance().updatePelicula(cod, tit, pases, PVP, retrasos, fecha, alquilada);
		
	}
	
	/**
	 * Actualiza lo que hayan tocado en el socio
	 * @param id
	 * @param dni
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @param numalqui
	 * @param notas
	 * @param avisar
	 * @param email
	 */
	public void updateSocio(String id, String dni, String nombre,
			String direccion, String telefono, String numalqui, String notas, Boolean avisar, String email)
	{
		
		Videoclub.getInstance().updateSocio(id, dni, nombre, direccion, telefono, numalqui, notas, avisar, email);
		
	}

	/**
	 * Devolver la película
	 * @param codPel
	 */
	public void devuelvePeliculaSocio(String codPel) 
	{
		Videoclub.getInstance().devuelvePeliculaSocio(codPel);
		
	}

	/**
	 * Alquilar la película
	 * @param tarjeta
	 * @param titulo
	 * @param codPel
	 * @param numPases
	 * @param b 
	 */
	public void alquilaPeliculaSocio(String tarjeta, String titulo, String codPel, String numPases,  Boolean isPromoViernes) 
	{
		Videoclub.getInstance().alquilaPeliculaSocio(tarjeta, titulo, codPel, numPases, isPromoViernes);
		
	}

	
	public void altaSocio() {
		Videoclub.getInstance().altaSocio();
		
	}
	
	public void altaPelicula() 
	{
		Videoclub.getInstance().altaPelicula();
		
	}

	/**
	 * Método para el listado de socios
	 * @return
	 */
	public ArrayList<Socio> getSocios() 
	{
		return Videoclub.getInstance().getaSocios();
		
	}

	/**
	 * Método para el listados de películas
	 * @return
	 */
	public ArrayList<Pelicula> getPeliculas() 
	{
		return Videoclub.getInstance().getaPeliculas();
	}

	/**
	 * Actualiza los deudores y pendientes y devuelve las tuplas de deudores
	 * @return
	 */
	public ArrayList<String[]> getDeudores(Boolean quick)
	{
		Videoclub.getInstance().getDeudoresADevolverHoyRetrasos(quick);
		return Videoclub.getInstance().getListaRetrasos();
	}

	/**
	 * Actualiza los deudores y pendientes y devuelve las tuplas de pendientes
	 * @return
	 */
	public ArrayList<String[]> getAlquileres(Boolean quick) {
//		Long start_time;
//		start_time = System.nanoTime();
		Videoclub.getInstance().getDeudoresADevolverHoyRetrasos(quick);
//		Long diff_time = System.nanoTime() - start_time;
//		System.out.println("	E L A P S E D getAlquileres-> " + (double)diff_time / 1000000000.0);
		return Videoclub.getInstance().getListaBuenaGente();
	}

	/**
	 * Cuando cierra la aplicación guarda el fichero con 
	 * el número de alquileres que llevamos
	 */
	public void cerrandoAplicacion()
	{
		Videoclub.getInstance().cerrandoAplicacion();
	}

}
