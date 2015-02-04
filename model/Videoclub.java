package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;




import javax.swing.table.DefaultTableModel;

import vista.GUI;
import data.H2DB;
import data.Trie;

public class Videoclub {
	
	/**Vista GUI*/
	private GUI vista = null;
	
	/**
	 * filename
	 */
	private static String filename = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
	
	/**BD */
	private H2DB BD = null;

	ArrayList<String[]> listaDeudores = null;

	ArrayList<String[]> listaBuenaGente = null;

	/**Socios*/
	private ArrayList<Socio> aSocios = null;
	
	/**Peliculas*/
	private ArrayList<Pelicula> aPeliculas = null;;
	
	/**Trie Socios*/
	private Trie tSocios = null;
	
	/**Trie Peliculas*/
	private Trie tPeliculas = null;

	private int alquiladasHoy = 0;
	
	private static Videoclub instance = null;
	
	public Trie gettPeliculas() {
		return tPeliculas;
	}
	
	public ArrayList<String[]> getListaRetrasos() {
		return listaDeudores;
	}


	public ArrayList<String[]> getListaBuenaGente() {
		return listaBuenaGente;
	}



	public void settPeliculas(Trie tPeliculas) {
		this.tPeliculas = tPeliculas;
	}

	
	public Trie gettSocios() {
		return tSocios;
	}


	public void settSocios(Trie tSocios) {
		this.tSocios = tSocios;
	}


	public ArrayList<Pelicula> getaPeliculas() {
		return this.BD.getPeliculasDB();
	}


	public void setaPeliculas(ArrayList<Pelicula> aPeliculas) {
		
		this.aPeliculas = aPeliculas;
	}

	public ArrayList<Socio> getaSocios() {
		return this.BD.getSociosDB();
	}


	public void setaSocios(ArrayList<Socio> aSocios) {
		this.aSocios = aSocios;
	}

	public H2DB getBD() {
		return BD;
	}

	public static Videoclub getInstance() {
		if(instance == null) {
			instance = new Videoclub();
		}
		return instance;
	}

	public Videoclub() {
		// Acceder a la BD y cargar datos
		this.BD = new H2DB();
		
		//Se hace 3 veces (aquí y al actualizar pelis y socios), es importante para 
		//tener los socios y las pelis
		//actualizadas con lo que mete el cliente por pantalla
		actualizaTiers();
		
		//Instanciar la vista
		this.vista = new GUI();
		vista.getFrmVideoClubCapitn().setVisible(true);
		
		//Rellenar las estructuras Tier de la vista (socios y peliculas)
		vista.settSocios(tSocios);
		vista.settPeliculas(tPeliculas);
		
		this.listaDeudores = new ArrayList<String[]>();
		this.listaBuenaGente = new ArrayList<String[]>();


		//Si es un nuevo día empieza en cero, si no coge lo que haya en el fichero
		cargaAlquiladasDelDia();

	}

	private void actualizaTiers()
	{
		//Recoger socios
		this.setaSocios(this.BD.getSociosDB());
		//Recoger peliculas
		this.setaPeliculas(this.BD.getPeliculasDB());
		
		this.tSocios = new Trie();	
		for (int i=0; i<this.aSocios.size();i++)
		{
			this.tSocios.insert(String.valueOf(this.aSocios.get(i).getId()).toUpperCase());
			this.tSocios.insert(String.valueOf(this.aSocios.get(i).getNombre()).toUpperCase());
			this.tSocios.insert(String.valueOf(this.aSocios.get(i).getDNI()).toUpperCase());
			this.tSocios.insert(String.valueOf(this.aSocios.get(i).getTel()).toUpperCase());
		}
		System.out.println("Tier de socios: "+this.aSocios.size());
		
		this.tPeliculas = new Trie();	
		for (int i=0; i<this.aPeliculas.size();i++)
		{
			this.tPeliculas.insert(String.valueOf(this.aPeliculas.get(i).getId()).toUpperCase());
			this.tPeliculas.insert(String.valueOf(this.aPeliculas.get(i).getNombre()).toUpperCase());
		}
		System.out.println("Tier de peliculas: "+this.aPeliculas.size());
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		
		Videoclub.getInstance();
		//Actualizamos los retrasados y a devolver hoy

		Videoclub.getInstance().vista.listaAlquileres(false);
		Videoclub.getInstance().vista.listaDeudores(false);
		
		
		
//		//TESTING AREA
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c = Calendar.getInstance();
//		try {
//			c.setTime(sdf.parse("2014-09-29"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		Date fechaDevolucion = null;
//		try {
//			fechaDevolucion = sdf.parse(sdf.format(c.getTime()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(getSundaysBetweenTwoDates( fechaDevolucion, Calendar.getInstance().getTime() ));

	}


	public Socio getSocio(String valor)
	{
		System.out.println("public void getSocio(String valor) valor["+valor+"]");
		Socio s = this.getBD().getSocioDB(valor);
		this.vista.updateVistaSocio(s);
		
		return s;

	}
	

	/**
	 * Hay que ampliar bastante éste método, quieren que saque retrasos
	 * @param columna
	 * @param valor
	 */
	public void getPelicula(String columna, String valor) {
		
		//compruebaPendientes(this.vista.getModeloTablaAlquileres(), idSocio);
		
		this.vista.updateVistaPelicula(this.getBD().getPeliculaDB(columna, valor));
		
	}
	
	/**
	 * De lo último que hice, para sacar los 3 últimos socios que han visto la película
	 * que entra (a devolver o a alquilar) y estudiar al último socio (pendientes y retrasos)
	 * @param columna
	 * @param valor
	 */
	public void get3SociosPeliculaPendientesDeudasRetrasos(String columna, String valor) {
			
		ArrayList<String[]> tupla = new ArrayList<String[]>();
		Boolean quick = true;
		
		tupla = this.getBD().get3SociosPeliculaDB(columna, valor);
		
		
		//Si hay al menos un socio que la vio antes
		if (tupla.size()>0)
		{
			//Busca para la película los 3 últimos socios que la han visto y estudia al primero
			this.vista.set3SociosPelicula(tupla);		
			//Long start_time;
			//Record the start time.
			//start_time = System.nanoTime();

			

			//Al loro el -1 que lleva un gran truco, como no tengo la tarjeta, la saco del modelo
			//de la tabla de los últimos 3 socios que han visto la película
			this.compruebaPendientes(Integer.valueOf(tupla.get(0)[1]), quick);
			//Long diff_time = System.nanoTime() - start_time;
			//System.out.println("	E L A P S E D pendientes :( -> " + (double)diff_time / 1000000000.0);
			
			
			
//			start_time = System.nanoTime();
			this.compruebaRetrasos(valor, Integer.valueOf(tupla.get(0)[1]), quick);
//			diff_time = System.nanoTime() - start_time;
//			System.out.println("	E L A P S E D retrasos-> :( " + (double)diff_time / 1000000000.0);
			
			

			//Calculate how long we've been running in nanoseconds.
		
		}

		
	}


	private int daysBetween(Date d1, Date d2){
		return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public static int getSundaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int numSundays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }
	    
	    //Excluimos hoy y el día de hoy
	    startCal.add(Calendar.DAY_OF_MONTH, 1);
	    while (startCal.getTimeInMillis() < endCal.getTimeInMillis())
	    {
	        if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
	            numSundays++;
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    
	   
	    //System.out.println("DOMINGOS IN BETWEEN ---------------------------> "+numSundays);
	    return numSundays;
	}

	private  ArrayList<String[]> compruebaPendientes(int p_idSocio, Boolean quick) 
	{
		ArrayList<String[]> tuplas = new ArrayList<String[]>();
		
		Videoclub.getInstance().vista.listaAlquileres(quick);
//		Long start_time;
//		start_time = System.nanoTime();
				
		DefaultTableModel modeloAux = this.vista.getModeloTablaAlquileres();	
		int numeroDeudores = modeloAux.getRowCount();
		int numCol = modeloAux.getColumnCount();
		
		
		
//		Long diff_time = System.nanoTime() - start_time;
		//System.out.println("	E L A P S E D compruebaPendientes-> " + (double)diff_time / 1000000000.0);
		
//		start_time = System.nanoTime();
		//for (int row = 0; row < this.vista.getModeloTablaAlquileres().getRowCount(); row++) 
		for (int row = 0; row < numeroDeudores; row++)
		{
			//La lista de alquileres (deudores) tiene en la columna 0 la tarjetita del socio
			if (p_idSocio ==  (int) this.vista.getModeloTablaAlquileres().getValueAt(row, 0)) 
			{
				//String[] record  = new String[this.vista.getModeloTablaAlquileres().getColumnCount()];
				String[] record  = new String[numCol];
				//for (int col = 0; col < this.vista.getModeloTablaAlquileres().getColumnCount(); col++) 
				for (int col = 0; col < numCol; col++)
				{
					record[col] = String.valueOf(this.vista.getModeloTablaAlquileres().getValueAt(row, col));
					//System.out.println("[Deudores]"+String.valueOf(this.vista.getModeloTablaAlquileres().getValueAt(row, col)));
				}
				tuplas.add(record);
			}

		}
		
//		diff_time = System.nanoTime() - start_time;
		//System.out.println("	E L A P S E D compruebaPendientes 2 -> " + (double)diff_time / 1000000000.0);
		
		
		if (tuplas.size()>0)
			this.vista.cartelPendientes(p_idSocio, tuplas.size(), true);
		else
			this.vista.cartelPendientes(p_idSocio, tuplas.size(), false);
		
		return tuplas;
	}
	
	private ArrayList<String[]> compruebaRetrasos(String titulo, int p_idSocio, Boolean quick) 
	{
		ArrayList<String[]> tuplas = new ArrayList<String[]>();
		int retrasosDeLaQueTrae = 0;
		
		//Actualizamos la lista de retrasos ya que es de donde vamos a mirar
		Videoclub.getInstance().vista.listaDeudores(quick);
//		Long start_time;
//		start_time = System.nanoTime();
				
		DefaultTableModel modeloAux = this.vista.getModeloTablaRetrasados();	
		int numeroDeudores = modeloAux.getRowCount();
		int numCol = modeloAux.getColumnCount();
		
//		Long diff_time = System.nanoTime() - start_time;
		//System.out.println("	E L A P S E D compruebaRetrasos-> " + (double)diff_time / 1000000000.0);
		
//		start_time = System.nanoTime();
		//Recorro la lista de deudores
		//for (int row = 0; row < this.vista.getModeloTablaRetrasados().getRowCount(); row++) 
		for (int row = 0; row < numeroDeudores; row++)
		{
			//La lista de deudores tiene en la columna 0 la tarjetita del socio
			if (p_idSocio ==  (int) this.vista.getModeloTablaRetrasados().getValueAt(row, 0)) 
			{
				//Intento rescatar el titulo de la peli que trae para ver si tiene deudas y ver los retrasos
				if (titulo!=null && titulo.equals(String.valueOf(this.vista.getModeloTablaRetrasados().getValueAt(row, 3))))
				{
					//Ahora le saco la fecha jur jur para contar retrasos...
					
					retrasosDeLaQueTrae = this.getNumeroRetrasos(this.vista.getModeloTablaRetrasados().getValueAt(row, 4));
				}
				
				//Reservo memoria para guardar las columnas + 1 para colocar ahí los retrasos 
				//Además le digo que recorra uno menos del modelo, porque hay 5 columnas y no 6
				//VAYA TRUCO!!!
				//String[] record  = new String[this.vista.getModeloTablaRetrasados().getColumnCount() + 1];
				String[] record  = new String[numCol + 1];
				
				//for (int col = 0; col < this.vista.getModeloTablaRetrasados().getColumnCount(); col++) 
				for (int col = 0; col < numCol; col++)
					record[col] = String.valueOf(this.vista.getModeloTablaRetrasados().getValueAt(row, col));
				
				//Master en chapuzas, coloco los retrasos a pelo
				record[5] = String.valueOf(this.getNumeroRetrasos(this.vista.getModeloTablaRetrasados().getValueAt(row, 4)));
				tuplas.add(record);
			}

		}
		
//		diff_time = System.nanoTime() - start_time;
		//System.out.println("	E L A P S E D compruebaRetrasos 2 -> " + (double)diff_time / 1000000000.0);
		
		if (tuplas.size()>0)
			this.vista.cartelDeudas(p_idSocio, tuplas.size(), true);
		else
			this.vista.cartelDeudas(p_idSocio, tuplas.size(), false);
		
		if (retrasosDeLaQueTrae > 0)
			this.vista.cartelRetrasos(p_idSocio, titulo, retrasosDeLaQueTrae, true);
		else
			this.vista.cartelRetrasos(p_idSocio, titulo, retrasosDeLaQueTrae, false);
		
		return tuplas;
	}

	/**
	 * Obtiene el número de retrasos que tiene una película, 
	 * se resta uno porque se devuelve al día siguiente 
	 * y ese día no se cuenta como retraso
	 * @param fecha es la fecha en que se alquiló la película
	 * @return retrasos - 1
	 */
	private int getNumeroRetrasos(Object fecha)
	{
		int resultado = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar fechaDelAlquiler 	= Calendar.getInstance();
		Calendar fechaDeHoy 		= Calendar.getInstance();
		try {
			fechaDelAlquiler.setTime(sdf.parse(String.valueOf(fecha)));
			resultado = this.daysBetween( fechaDelAlquiler.getTime(), fechaDeHoy.getTime() );	
		} catch (ParseException e) {
			System.out.println("[EXCEPTION] getNumeroRetrasos");
			e.printStackTrace();
			return - 1;
		}
		
		//version 2.2 (descontamos los domingos entre las fechas dadas)
		resultado = resultado - getSundaysBetweenTwoDates(fechaDelAlquiler.getTime(), fechaDeHoy.getTime());
		
		//System.out.println("[[[[RETRASOS!!!!!]]]]" + String.valueOf((resultado - 1)));
		
		
		return resultado - 1;
	}
	
	public Boolean updatePelicula(String cod, String tit, String pases,
			String PVP, String retrasos, Date fecha, String alquilada)
	{
		//Tratamiento y protección de campos
		int numPases = 0;
		int numPVP = 0;
		int numRetrasos = 0;
		
		if (!pases.isEmpty())
			try {
				numPases = Integer.valueOf(pases);
			} catch (NumberFormatException e) {
				System.out.println("[EXCEPTION]: public void updatePelicula(String cod, String tit, String pases, String PVP, String retrasos, Date fecha, String alquilada");
				System.err.println(e.getMessage());
				return false;
			}
		
		if (!PVP.isEmpty())
			try {
				numPVP = Integer.valueOf(PVP);
			} catch (NumberFormatException e) {
				System.out.println("[EXCEPTION]: public void updatePelicula(String cod, String tit, String pases, String PVP, String retrasos, Date fecha, String alquilada");
				System.err.println(e.getMessage());
				return false;
			}
		
		if (!retrasos.isEmpty())
			try {
				numRetrasos = Integer.valueOf(retrasos);
			} catch (NumberFormatException e) {
				System.out.println("[EXCEPTION]: public void updatePelicula(String cod, String tit, String pases, String PVP, String retrasos, Date fecha, String alquilada");
				System.err.println(e.getMessage());
				return false;
			}
		
		this.getBD().updatePeliculaDB(cod, tit, numPases, numPVP, numRetrasos, fecha, alquilada);
		
		//Actualizamos TIERS y seteamos en la vista para tener las búsquedas frescas
		this.actualizaTiers();
		vista.settPeliculas(tPeliculas);
		
		return true;
	}
	
	public void updateSocio(String id, String dni, String nombre,
			String direccion, String telefono, String numAlqui, String notas, Boolean avisar, String email)
	{
		//Tratamiento y protección de campos
		int numAlquileres = 0;

		try {
			numAlquileres = Integer.valueOf(numAlqui);
		} catch (NumberFormatException e) {
			System.out.println("[EXCEPTION] public void updateSocio( NÚMERO DE ALQUILERES NO ES UN NÚMERO )");
		}
		
		
		this.getBD().updateSocioDB(id, dni, nombre, direccion, telefono, numAlquileres, notas, avisar, email);
		
		//Actualizamos TIERS y seteamos en la vista para tener las búsquedas frescas
		this.actualizaTiers();
		vista.settSocios(tSocios);

	}
	
	public void altaPelicula()
	{
		int id = this.getBD().altaPeliculaDB();
		if (id == -1)
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "La pelicula ha tenido problemas al darse de alta.");
		else
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "Alta correcta, ahora rellena la ficha o déjalo para después.");
		
		this.vista.altaPelicula(String.valueOf(id));
		this.vista.blanqueaPelicula(false);
	}
	
	public void altaSocio()
	{
		int id = this.getBD().altaSocioDB();
		if (id == -1)
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "El socio ha tenido problemas al darse de alta.");
		else
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "Alta correcta, ahora rellena la ficha o déjalo para después.");
		
		this.vista.altaSocio(String.valueOf(id));
		this.vista.blanqueaSocio(false);
	}
	
	public void devuelvePeliculaSocio(String codPel) {
		
		this.getBD().devuelveAlquilaPeliculaDB(Integer.valueOf(codPel), false, 0);
		
		vista.blanqueaSocio(true);
		vista.blanqueaPelicula(true);
		
		//JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "La película "+codPel+" ha sido DEVUELTA satisfactoriamente.");
	}

	/**
	 * Retocado en version 2.4.1 porque no sumaba bien las alquiladas al Socio
	 * @param tarjeta
	 * @param titulo
	 * @param codPel
	 * @param numPases
	 * @param isPromoViernes
	 */
	public void alquilaPeliculaSocio(String tarjeta, String titulo, String codPel, String numPases, Boolean isPromoViernes ) {
		
		
		this.alquiladasHoy++;
		incrementaAlquiladasSocio(tarjeta);
		
		this.getBD().devuelveAlquilaPeliculaDB(Integer.valueOf(codPel), true, Integer.valueOf(numPases)+1);
		this.getBD().devuelveAlquilaPeliculaHistorialDB(tarjeta, titulo, dameHoyPlus(), isPromoViernes);
		
		//Actualizo el contador de la vista		
		vista.updateAlquiladasHoy(this.alquiladasHoy);
		//Mejora para alquilar en cadena v2.4.1
		//vista.blanqueaSocio(true);
		vista.blanqueaPelicula(true);
		
	}
	
	/**
	 * Método retocado en v2.4.1
	 * @param tarjeta
	 */
	private void incrementaAlquiladasSocio(String tarjeta) {
		int idSocio = Integer.parseInt(tarjeta);
		int i = 0;
		while (this.aSocios.get(i).getId() != idSocio)
			i++;
		System.out.println("[incrementaAlquiladasHoySocio] Tarjeta: "+this.aSocios.get(i).getId());
		this.aSocios.get(i).setAlquileres(this.aSocios.get(i).getAlquileres() + 1);	
		System.out.println("[incrementaAlquiladasHoySocio] "+this.aSocios.get(i).getAlquileres());
		this.getBD().updateNumAlquileresSocioDB(tarjeta, this.aSocios.get(i).getAlquileres());
	}


	/**
	 * Creo que como no tiene hora, el dame hoy se ha de comprobar
	 * para el before y after, en lugar de dameHoyPlus que tiene la hora y
	 * puede ocurrir que le de de plazo hasta una hora avanzada del día del retraso
	 * Ej: el tio tiene que devolver el martes, porque alquila el lunes a las 18.39 por ejemplo, 
	 * entonces le dejo todo el martes para devolver. 
	 * Pero de la forma en que lo hago (sumando 2 o 3 días), si considero la hora, 
	 * tendría hasta el miércoles a las 18.39 para devolver, lo cual no es correcto.
	 * @return
	 */
	private static Date dameHoy() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(date);
		//System.out.println(date.toString());
		return date;
	}
	
	private static java.sql.Timestamp dameHoyPlus(){
		Date date = new Date();
		//System.out.println(date.toString());
	    return new java.sql.Timestamp(date.getTime());
	}



	private static Boolean isSabado(String fecha) {
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		String finalDay = null;
		  try {
			Date dt1=format1.parse(fecha);
			DateFormat format2=new SimpleDateFormat("EEEE"); 
			finalDay=format2.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return finalDay.equals("sábado");
	}


	public void cuandoHaVistoElSocioLaPeliculaDB(String titulo, String tarjeta) {
		Date aux = null;
		String clave = "ID";
		try {
			Integer.parseInt(titulo);
		} catch (NumberFormatException e) {
			clave = "TITULO";
		}
		aux = this.getBD().cuandoHaVistoElSocioLaPeliculaDB(clave, titulo, Integer.valueOf(tarjeta));
		
		
		if (aux == null)
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "El socio "+tarjeta+", no ha visto la película "+titulo+".");
		else
			JOptionPane.showMessageDialog(this.vista.getFrmVideoClubCapitn(), "El socio "+tarjeta+", ha visto la película "+titulo+" en "+aux.toString()+".");
		
	}
	

	/**
	 * Método más MÁGICO Y DELICADO
	 * 
	 * Partiendo de la info extraída de la BD se confecciona una lista de socios
	 * con deudas y el día que alquilaron y falta añadir los días de retraso
	 */
	public void getDeudoresADevolverHoyRetrasos(Boolean quick)
	{
		
		this.listaBuenaGente.clear();
		this.listaDeudores.clear();

		ArrayList<String[]> historico = null;
		
		
//		Long start_time;
//		start_time = System.nanoTime();
		
		
		ArrayList<String> alquiladas = this.BD.getTitulosAlquilados();
		
//		Long diff_time = System.nanoTime() - start_time;
//		System.out.println("	E L A P S E D getTitulosAlquilados-> " + (double)diff_time / 1000000000.0);
		
//		start_time = System.nanoTime();
		if (quick)
			historico = this.BD.getHistoricosDBPerezQUICK();
		else			
			historico = this.BD.getHistoricosDBPerez();
		
		
//		diff_time = System.nanoTime() - start_time;
//		System.out.println("	E L A P S E D getHistoricosDBPerez-> " + (double)diff_time / 1000000000.0);
		
		
		Date fechaDevolucion = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		//Recorro las alquiladas = true y saco listas para mostrar
		for (String tuplaAlq : alquiladas)
		{
			
			//TARJETA  	NOMBRE  	ID  	TITULO  	FECHA  	PROMOVIERNES  
			//2778	DIONISIO DE HARO MORENO	260	SPIDERS	2014-07-25	TRUE		
			
			//Recorro el histórico de alquileres por ordenado por fecha desc (order by fecha desc)
			//Alquiler más reciente arriba del todo! (pero hay que ver que siga alquilada (siguiente if)
			for (String[] tuplaHist : historico)
			{			
				//If pelicula alquilada está en el histórico... 
				//procesamos y pasamos al siguiente título con alquilado = true  (hay un break al final de éste if)
				//para que no siga buscando una vez que encuentra el título alquilado más reciente en el 
				//histórico.
				if (tuplaAlq.equals(tuplaHist[3]))
				{
					//si promo viernes columna 5 de la tupla
					if (Boolean.valueOf(tuplaHist[5]))
					{
						try {
							c.setTime(sdf.parse(tuplaHist[4]));//la columna 4 tiene la fecha
							// La fecha será hasta el martes 00:00 para que tenga todo el 
							// lunes hasta las 23:59 para devolver por eso sumo 4
							c.add(Calendar.DATE, 4); 
							fechaDevolucion = sdf.parse(sdf.format(c.getTime()));
						} catch (ParseException e) {
							e.printStackTrace();
						}

						//ver si es retrasado
						if (fechaDevolucion.before(dameHoy()))
						{
							tuplaHist[6] = String.valueOf(this.getNumeroRetrasos(tuplaHist[4]));
							listaDeudores.add(tuplaHist);
						}
							
						else
							listaBuenaGente.add(tuplaHist);
					}//end if promo viernes
					
					
					else if (isSabado(tuplaHist[4]))
					{
						try {
							c.setTime(sdf.parse(tuplaHist[4]));
							// la fecha será el martes que sea a las 00:00 para dejarle devolver todo el lunes hasta las 23:59
							c.add(Calendar.DATE, 3);  
							fechaDevolucion = sdf.parse(sdf.format(c.getTime()));

						} catch (ParseException e) {
							e.printStackTrace();
						}

						//ver si es retrasado
						if (fechaDevolucion.before(dameHoy()))
						{
							tuplaHist[6] = String.valueOf(this.getNumeroRetrasos(tuplaHist[4]));
							listaDeudores.add(tuplaHist);
						}

						else
							listaBuenaGente.add(tuplaHist);
					}//fin else if es Sábado
					
					
					//Un día normal entiendo yo... puede que se escapen casos
					else
					{
						try {
							c.setTime(sdf.parse(tuplaHist[4]));
							c.add(Calendar.DATE, 2);  // number of days to add
							fechaDevolucion = sdf.parse(sdf.format(c.getTime()));

						} catch (ParseException e) {
							e.printStackTrace();
						}
						//es retrasado por la query
						if (fechaDevolucion.before(dameHoy()))
						{
							tuplaHist[6] = String.valueOf(this.getNumeroRetrasos(tuplaHist[4]));
							listaDeudores.add(tuplaHist);
						}
						else
							listaBuenaGente.add(tuplaHist);
					}
					
					
					//Acierto, pues rompo el bucle y paso a la siguiente película alquilada
					break;
				}//fin del if del acierto
			}//fin del for del histórico
		}//fin del for de alquiladas = true
		
		//Debugging... algun día lo borraré
//		for (String[] tupla : listaDeudores)
//		{
//			
//			System.out.println("--DEUDOR---->"+tupla[0]+"|"+tupla[1]+"|"+tupla[2]+"|"+tupla[3]+"|"+tupla[4]);
//		}

		System.out.println("[DEUDAS]--------> "+listaDeudores.size());
		System.out.println("[DEVOLVER]------> "+listaBuenaGente.size());
		

		this.vista.updateRetrasos(listaDeudores.size());
		this.vista.updateADevolverHoy(listaBuenaGente.size());	
	}
	
	private void cargaAlquiladasDelDia() 
	{

		File file = new File(filename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				try {
					this.alquiladasHoy = Integer.parseInt(text);
					this.vista.updateAlquiladasHoy(this.alquiladasHoy);
					System.out.println("Fichero encontrado para recuperar alquileres, hay: "+ text);
				} catch (NumberFormatException e) {
					this.alquiladasHoy = 0;
					this.vista.updateAlquiladasHoy(this.alquiladasHoy);
					System.out.println("[EXCEPTION] Fichero contiene un valor no numérico:["+ text +"]");
				}
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Fichero no encontrado para recuperar alquileres, al cerrar se creará con fecha: "+filename);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}


	public void cerrandoAplicacion() {
		BufferedWriter writer = null;
	    try {	    	
	        writer = new BufferedWriter(new FileWriter(filename));
	        writer.write(this.vista.gettAlquiladas().getText());
	        System.out.println(filename);
	    } catch (IOException e) {
	        System.err.println(e);
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	        }
	    }
		
	}

	/**
	 * Se llama desde la pestaña socios o películas al buscar un socio. 
	 * Sirve para pintar las dos tablas
	 * en la pestaña de los socios
	 * @param tarjeta
	 */
	public void buscaPendientesDeudasSocio(String tarjeta) 
	{
		ArrayList<String[]> tuplas = new ArrayList<String[]>();	
		Boolean quick = true;
		
		
		tuplas.addAll(this.compruebaPendientes(Integer.valueOf(tarjeta), quick));
		this.vista.setTablaPendientes(tuplas);

		tuplas.clear();

		//null, que es el título de la película para mirar los retrasos de la película,
		//Desde la pestaña socio no se que película se está alquilando (podría pero no lo han pedido)
		tuplas.addAll(this.compruebaRetrasos(null, Integer.valueOf(tarjeta), quick));
		this.vista.setTablaDeudas(tuplas);



	}

	public Boolean getHavistoAlgoParecido(String idPeli, String idSocio) {
		this.vista.setListaSimilares(this.getBD().listaSimilaresQueHaVisto(idPeli, Integer.parseInt(idSocio)));
		
		return true;
	}

	public int getIDPelicula(String columnaTitulo, String titulo) {
		return this.getBD().getPeliculaDB(columnaTitulo, titulo).getId();
		
	}
}

	
	
