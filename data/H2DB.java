package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import model.Pelicula;
import model.Socio;

import org.h2.jdbcx.JdbcConnectionPool;
public class H2DB {

	private JdbcConnectionPool cp 		= null;
	private String user = "ventrue";
	private String pass = "ventrue";
	private String cadena = "jdbc:h2:~/VIDEOCLUB;AUTO_SERVER=TRUE";
//	private String user = "sa";
//	private String pass = "";
//	private String cadena = "jdbc:h2:~/test2;AUTO_SERVER=TRUE";


	public H2DB() {
		super();
	}

	/**
	 * Te da los socios en un ArrayList de socios, luego lo uso para la Tier
	 * @return
	 */
	public ArrayList<Socio> getSociosDB()
	{
		ArrayList<Socio> aSocios = new ArrayList<Socio>();
		int id;
		String nombre;
		String apellido;
		int alquileres;
		String DNI;
		String dom;
		String tel;
		String email;
		String observaciones;
		Boolean avisar;

		try{
			this.cp = JdbcConnectionPool.create(cadena,user,pass);
			Connection con = this.cp.getConnection();
			Statement stmt = con.createStatement();

			try (ResultSet rs = stmt.executeQuery("SELECT * FROM SOCIOS")) {
				// do stuff with query results.
				//int i = 1;

				while (rs.next()) {
					id = rs.getInt(1);
//					if (id != i)
//					{
//						//System.out.println("-descuadre----->"+i);
//						//break;
//					}

					DNI = rs.getString(2);
					if (rs.wasNull())
						DNI = "";

					//byte ptext[] = rs.getString(3).getBytes(ISO_8859_1); 
					//nombre = new String(ptext, UTF_8); 
					//nombre = new String(rs.getString(3).getBytes(),"UTF-8");
					nombre = rs.getString(3);
					if (rs.wasNull())
						nombre = "";

					apellido = rs.getString(3);
					if (rs.wasNull())
						apellido = "";

					dom = rs.getString(4);
					if (rs.wasNull())
						dom = "";

					tel = rs.getString(5);
					if (rs.wasNull())
						tel = "";


					alquileres = rs.getInt(6);
					if (rs.wasNull())
						alquileres = 0;

					observaciones = rs.getString(7);
					if (rs.wasNull())
						observaciones = "";

					avisar = rs.getBoolean(8);

					email = rs.getString(9);
					if (rs.wasNull())
						email = "";

					aSocios.add(new Socio(id,
							nombre,
							apellido,
							alquileres,
							DNI,
							dom,
							tel,
							email,
							observaciones,
							avisar,
							null));

					//System.out.println(aSocios.get(i-2)+"------->"+i);
					//i++;	

				}
				System.out.println("--public ArrayList<Socio> getSociosDB():---->"+aSocios.size());
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<Socio> getSociosDB(): " + e.getMessage());
		}

		this.cp.dispose();
		return aSocios;

	}


	/**
	 * Te da las peliculas en un ArrayList de peliculas, luego lo uso para la Tier
	 * @return
	 */
	public ArrayList<Pelicula> getPeliculasDB()
	{
		ArrayList<Pelicula> aPeliculas = new ArrayList<Pelicula>();
		int id;
		String titulo;
		String notas;
		Date fecha;
		Boolean alquilada;
		int alquileres;
		int retrasos;
		int PVP;

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);
			Connection con = this.cp.getConnection();
			Statement stmt = con.createStatement();

			try (ResultSet rs = stmt.executeQuery("SELECT * FROM PELICULAS")) {
				// do stuff with query results.
				//int i = 2;

				while (rs.next()) {
					id = rs.getInt(1);
//					if (id != i)
//					{
//						//System.out.println("-descuadre----->"+i);
//						//break;
//					}

					titulo = rs.getString(2);
					if (rs.wasNull() || titulo.isEmpty()) {System.out.println("TITULO null ----->"+rs.getRow());continue;}
					//titulo = "";

					notas = rs.getString(3);
					if (rs.wasNull())
						notas = "";


					alquilada = rs.getBoolean(4);

					alquileres = rs.getInt(5);
					if (rs.wasNull())
						alquileres = 0;

					retrasos = rs.getInt(6);
					if (rs.wasNull())
						retrasos = 0;

					fecha = rs.getDate(7);
					if (rs.wasNull())
						fecha = null;

					PVP = rs.getInt(8);
					if (rs.wasNull())
						PVP = 0;

					aPeliculas.add(new Pelicula(id, titulo, alquilada, alquileres, retrasos, fecha, PVP, notas));


					//System.out.println(aPeliculas.get(i-2)+"------->"+i);
					//i++;	

				}
				System.out.println("--public ArrayList<Pelicula> getPeliculasDB()---->"+aPeliculas.size());
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<Pelicula> getPeliculasDB(): " + e.getMessage());
		}

		this.cp.dispose();
		return aPeliculas;

	}

	/**
	 * Te da los socios en un ArrayList de tuplas, para una película en concreto.
	 * luego lo uso para la Tier
	 * @return
	 */
	public ArrayList<String[]> get3SociosPeliculaDB(String clave, String titulo)
	{
		ArrayList<String[]> tupla = new ArrayList<String[]>();


		try{
			this.cp = JdbcConnectionPool.create(cadena,user,pass);
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("SELECT top 3 B.NOMBRE, A.TARJETA, A.FECHA FROM PELICULASOCIO A LEFT JOIN SOCIOS B ON A.TARJETA = B.ID WHERE A."+clave+" =? ORDER BY A.FECHA DESC;");
			stmt.setString(1, titulo);
			try (ResultSet rs = stmt.executeQuery()){

				// do stuff with query results.
				int i = 0;

				while (rs.next()) {
					String[] record  = new String[3];

					record[0] = rs.getString(1);
					if (rs.wasNull())
						record[0] = "";
					record[1] = rs.getString(2);
					if (rs.wasNull())
						record[1] = "";
					record[2] = rs.getString(3);
					if (rs.wasNull())
						record[2] = "";

					tupla.add(record);
					System.out.println("--public ArrayList<String[]> get3SociosPeliculaDB(String clave, String titulo)---->"+tupla.get(i)[0]+"|"+tupla.get(i)[1]+"|"+tupla.get(i)[2]);
					i++;
				}
				System.out.println("--public ArrayList<String[]> get3SociosPeliculaDB(String clave, String titulo)---->"+i);
				System.out.println("--public ArrayList<String[]> get3SociosPeliculaDB(String clave, String titulo)---->"+tupla.size());
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<String[]> get3SociosPeliculaDB(String clave, String titulo): " + e.getMessage());
		}

		this.cp.dispose();
		return tupla;

	}

	/**
	 * Te da las peliculas en un ArrayList de peliculas QUE TIENE UN SOCIO (PARA ALQUILADAS AFINAR)
	 * @return
	 */
	public ArrayList<String[]> getPeliculasSocioDB(int idSocio)
	{
		ArrayList<String[]> tupla = new ArrayList<String[]>();


		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("(SELECT FECHA, TITULO  FROM PELICULASOCIO WHERE TARJETA=? ORDER BY FECHA DESC )");
			stmt.setInt(1, idSocio);
			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				int i = 0;

				while (rs.next()) {

					String[] record  = new String[2];


					//record[0] = new String (rs.getString(1).getBytes(),"UTF-8" ) ;
					record[0] = new String (rs.getString(1));
					if (rs.wasNull())
						record[0] = "";

					record[1] = rs.getString(2);
					if (rs.wasNull())
						record[1] = "";

					tupla.add(record);
					System.out.println("--public ArrayList<String[]> getPeliculasSocioDB(int idSocio)---->"+tupla.get(i)[0]+"|"+tupla.get(i)[1]);
					i++;

				}
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<String[]> getPeliculasSocioDB(int idSocio): " + e.getMessage());
		}

		this.cp.dispose();
		return tupla;

	}


	/**
	 * Te devuelve una pelicula de la BD atendiendo a una consulta columna valor
	 * @param clave es el valor que toma la columna, puede ser TITULO o COD pelicula
	 * @param valor
	 * @return
	 */
	public Pelicula getPeliculaDB(String clave, String valor) 
	{
		Pelicula p = null;
		int id;
		String titulo;
		String notas;
		Date fecha;
		Boolean alquilada;
		int alquileres;
		int retrasos;
		int PVP = 0;


		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("SELECT * FROM PELICULAS WHERE "+clave+"=?");
			if (clave.equals("ID"))
				stmt.setInt(1, Integer.valueOf(valor));
			else if (clave.equals("TITULO"))
				stmt.setString(1, valor);


			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				while (rs.next()) {
					id = rs.getInt(1);

					titulo = rs.getString(2);
					if (rs.wasNull() || titulo.isEmpty()) {System.out.println("public Pelicula getPeliculaDB(String clave, String valor) NUUUUULL TITULO WTF----->"+rs.getRow());continue;}

					notas = rs.getString(3);
					if (rs.wasNull())
						notas = "";


					alquilada = rs.getBoolean(4);

					alquileres = rs.getInt(5);
					if (rs.wasNull())
						alquileres = 0;

					retrasos = rs.getInt(6);
					if (rs.wasNull())
						retrasos = 0;

					fecha = rs.getDate(7);
					if (rs.wasNull())
						fecha = null;

					PVP = rs.getInt(8);
					if (rs.wasNull())
						PVP = 0;

					p = new Pelicula(id, titulo, alquilada, alquileres, retrasos, fecha, PVP, notas);

					System.out.println("public Pelicula getPeliculaDB(String clave, String valor)------->"+rs.getRow()+" | "+p);

				}
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public Pelicula getPeliculaDB(String clave, String valor): " + e.getMessage());
		}

		this.cp.dispose();
		return p;
	}


	public Boolean updatePeliculaDB(String cod, String tit, int numPases,
			int numPVP, int numRetrasos, Date fecha, String alquilada)
	{

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("UPDATE PELICULAS SET TITULO=?, FECHA=?, ALQUILADA=?, NUMALQUILERES=?, NUMRETRASOS=?, PVP=? WHERE ID=?");
			stmt.setString(1, tit);
			java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
			stmt.setDate(2, sqlDate);
			stmt.setBoolean(3, alquilada.equals("ALQUILADA") ? true : false);
			stmt.setInt(4, numPases);
			stmt.setInt(5, numRetrasos);
			stmt.setInt(6, numPVP);
			stmt.setInt(7, Integer.valueOf(cod));

			stmt.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public Boolean updatePeliculaDB(String cod, String tit, int numPases, int numPVP, int numRetrasos, Date fecha, String alquilada): " + e.getMessage());
		}

		this.cp.dispose();
		return false;
	}

	public Boolean updateSocioDB(String id, String dni, String nombre,
			String direccion, String telefono, int numalqui, String notas,
			Boolean avisar, String email) {
		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("UPDATE SOCIOS SET DNI=?, NOMBRE=?, DIRECCION=?, TELEFONO=?, NUMALQUI=?, OBSERVA=?, AVISAR=?, EMAIL=? WHERE ID=?");
			stmt.setString(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, direccion);
			stmt.setString(4, telefono);
			stmt.setInt(5, Integer.valueOf(numalqui));
			stmt.setString(6, notas);
			stmt.setBoolean(7, avisar);
			stmt.setString(8, email);

			stmt.setInt(9, Integer.valueOf(id));

			stmt.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public Boolean updateSocioDB(String id, String dni, String nombre, String direccion, String telefono, int numalqui, String notas, Boolean avisar, String email) {: " + e.getMessage());
		}

		this.cp.dispose();
		return false;
	}
	
	public Boolean updateNumAlquileresSocioDB(String id, int numalqui) {
		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("UPDATE SOCIOS SET NUMALQUI=? WHERE ID=?");
			
			
			stmt.setInt(1, Integer.valueOf(numalqui));
			
			stmt.setInt(2, Integer.valueOf(id));

			stmt.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public Boolean updateSocioDB(String id, String dni, String nombre, String direccion, String telefono, int numalqui, String notas, Boolean avisar, String email) {: " + e.getMessage());
		}

		this.cp.dispose();
		return false;
	}

	public Boolean devuelveAlquilaPeliculaDB(int cod, Boolean alquila, int numPases)
	{

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			if (!alquila) //devuelve por tanto no incremento los alquileres
			{
				PreparedStatement  stmt = con.prepareStatement("UPDATE PELICULAS SET ALQUILADA=? WHERE ID=?");
				stmt.setBoolean(1, alquila);
				stmt.setInt(2, cod);
				stmt.executeUpdate();
			}
			else // alquila, por tanto toco el número de pases
			{
				PreparedStatement  stmt = con.prepareStatement("UPDATE PELICULAS SET ALQUILADA=?, NUMALQUILERES=? WHERE ID=?");
				stmt.setBoolean(1, alquila);
				stmt.setInt(2, numPases);
				stmt.setInt(3, cod);
				stmt.executeUpdate();
			}

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public Boolean devuelveAlquilaPeliculaDB(int cod, Boolean alquila, int numPases): " + e.getMessage());
		}

		this.cp.dispose();


		return false;
	}


	/**
	 * Te busca por título exacto (da errores), esta deprecado encubierto por listaSimilaresQueHaVisto
	 * Este método no convence porque tienen varias copias de la misma película pero con distinto título,
	 * por ejemplo: Spiderman bluray, Spiderman 2 1, Spiderman 2 2 
	 * @return fecha en que la ha visto
	 */
	public Date cuandoHaVistoElSocioLaPeliculaDB(String clave, String titulo, int idSocio)
	{
		Date fecha = null;
		PreparedStatement  stmt = null;
		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			//Hay que buscar el título para el ID de la película en la tabla PELICULAS
			if (clave.equals("ID"))
			{
				//El top 1 es porque si la ha visto una vez me vale
				 stmt = con.prepareStatement("SELECT TITULO FROM PELICULAS WHERE ID=?");
				stmt.setString(1, titulo);
				try (ResultSet rs = stmt.executeQuery()) {
					// do stuff with query results.
					while (rs.next()) {		
						//Formato que viene
						titulo = rs.getString(1);				
						if (rs.wasNull())
							titulo = null;
						System.out.println("--public Date cuandoHaVistoElSocioLaPeliculaDB(clave id)---->"+titulo);
					}
				}
			}
			
			//El top 1 es porque si la ha visto una vez me vale
			stmt = con.prepareStatement("SELECT top 1 FECHA FROM PELICULASOCIO WHERE TARJETA=? AND TITULO=?");
			stmt.setInt(1, idSocio);
			stmt.setString(2, titulo);
			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				while (rs.next()) {		
					//Formato que viene
					fecha = rs.getTimestamp(1);				
					if (rs.wasNull())
						fecha = null;
					System.out.println("--public Date cuandoHaVistoElSocioLaPeliculaDB(clave titulo)---->"+fecha.toString());
				}
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public Time cuandoHaVistoElSocioLaPeliculaDB(String titulo, int idSocio): " + e.getMessage());
		}

		this.cp.dispose();

		return fecha;

	}
	
	/**
	 * Al loro la expresion regular que falla con pelis mal metidas: BLURAY SPIDERMA, o 300 el origen 
	 * (porque tiene un número al principio)
	 * Mejora para la versión 2.4.0 que salgan las que ha visto parecidas con el
	 * lio que tienen los títulos:
	 *  JUNTOS Y REVUELTOS
		JUNTOS Y REVUELTOS 2 BLURAY
		JUNTOS Y REVUELTOS 1 BLU RAY
		JUNTOS Y REVUELTOS 2
		LA ISLA MINIMA 2
		EL CORREDOR DEL LABERINTO 3
		EL CORREDOR DEL LABERINTO 2
		LA ISLA MINIMA 1
		LA ISLA MINIMA 2 BLU RAY
		LA ISLA MINIMA 1 BLU RAY
		OCHO APELLIDOS VASCOS BLU-RAY 1
		SPIDERMAN - 3
		OCHO APELLIDOS VASCOS BLU RAY 2
		VENGANZA 2 2
		LA GRAN REVANCHA
		PLAN EN LAS VEGAS
		DJANGO DESENCADENADO - BLU RAY 1
		OLD BOY
		EL GRAN GATSBY BLU RAY 3D
	 * 
	 * @return la lista de películas con título parecido
	 */
	public ArrayList<String> listaSimilaresQueHaVisto (String IdPeli, int idSocio)
	{
		PreparedStatement  stmt = null;
		String titulo = null;
		ArrayList<String> vistasParecidas = new ArrayList<String>();
		
		try {
			this.cp = JdbcConnectionPool.create(cadena, user, pass);
			Connection con = this.cp.getConnection();
			// Hay que buscar el título para el ID de la película en la tabla
			// PELICULAS

			stmt = con.prepareStatement("SELECT TITULO FROM PELICULAS WHERE ID=?");
			stmt.setString(1, IdPeli);
			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				while (rs.next()) {
					// Formato que viene
					titulo = rs.getString(1);
					if (rs.wasNull())
						titulo = null;
					System.out.println("--ArrayList<String> listaSimilaresQueHaVisto (String IdPeli, int idSocio)---->"+ titulo);
				}
			}
			
			//Para arreglar el pisto que tienen con los títulos
			stmt = con.prepareStatement("SELECT TITULO FROM PELICULASOCIO WHERE TARJETA=? AND TITULO LIKE ?");
			stmt.setInt(1, idSocio);
			
			stmt.setString(2, titulo.replaceAll("\\d(.*)|BLU(.*)", "") + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				while (rs.next()) {		
					vistasParecidas.add(rs.getString(1) + System.getProperty("line.separator"));
				}
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<String> listaSimilaresQueHaVisto(String IdPeli, int idSocio): " + e.getMessage());
		}

		this.cp.dispose();

		return vistasParecidas;

	}

	public Boolean devuelveAlquilaPeliculaHistorialDB(String tarjeta,
			String titulo, Timestamp fecha, Boolean promoViernes) 
	{

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("INSERT INTO PELICULASOCIO(TARJETA, TITULO, FECHA, PROMOVIERNES) VALUES (?,?,?,?)");
			stmt.setString(1, tarjeta);
			stmt.setString(2, titulo);

			//java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
			
			//stmt.setDate(3, sqlDate);
			stmt.setTimestamp(3, fecha);
			stmt.setBoolean(4, promoViernes);
			stmt.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public void devuelveAlquilaPeliculaHistorialDB(String tarjeta, String titulo, Date fecha, Boolean promoViernes): " + e.getMessage());
			return false;
		}
		System.out.println("devuelveAlquilaPeliculaHistorialDB: INSERT INTO PELICULASOCIO"+tarjeta+" | "+titulo);
		this.cp.dispose();
		
		return true;

	}

	public int altaSocioDB() 
	{
		int id = 0;

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);
			Connection con = this.cp.getConnection();
			Statement stmt = con.createStatement();
			try (ResultSet rs = stmt.executeQuery("select top 1 id from socios order by ID desc;")) {
				// do stuff with query results.
				while (rs.next()) {
					id = rs.getInt(1);
				}

			} catch (Exception e)
			{
				System.err.println("[EXCEPTION] public int altaSocioDB(): " + e.getMessage());
			}	

			PreparedStatement  stmt2 = con.prepareStatement("INSERT INTO SOCIOS(ID,AVISAR,EMAIL) VALUES(?,false,'email@mail.com');");
			stmt2.setInt(1, (id+1));
			stmt2.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public int altaSocioDB(): " + e.getMessage());
			return -1;
		}

		this.cp.dispose();
		return id + 1;
	}

	public int altaPeliculaDB()
	{

		int id = 0;

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);


			Connection con = this.cp.getConnection();
			Statement stmt = con.createStatement();
			try (ResultSet rs = stmt.executeQuery("select top 1 id from PELICULAS order by ID desc;")) {
				// do stuff with query results.
				while (rs.next()) {
					id = rs.getInt(1);
				}

			} catch (Exception e)
			{
				System.err.println("[EXCEPTION] public int altaPeliculaDB(): " + e.getMessage());
			}	

			PreparedStatement  stmt2 = con.prepareStatement("INSERT INTO PELICULAS(ID,ALQUILADA,FECHA) VALUES(?,FALSE,?);");
			//El id que le damos, es el último + 1
			stmt2.setInt(1, (id+1));
			//Cuando la da de alta le pongo la fecha de hoy, luego si quiere que lo cambie.
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date( date.getTime() );	
			stmt2.setDate(2, sqlDate);
			stmt2.executeUpdate();
			// do stuff with query results.

		} catch (Exception e)
		{
			System.err.println("[EXCEPTION] public int altaPeliculaDB(): " + e.getMessage());
			return -1;
		}

		this.cp.dispose();
		return id + 1;
	}

	public ArrayList<String> getTitulosAlquilados()
	{
		ArrayList<String> alquiladas = new ArrayList<String>();

		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			Statement  stmt = con.createStatement();

			try (ResultSet rs = stmt.executeQuery("select titulo from peliculas where alquilada = true;")) {
				while (rs.next()) {
					alquiladas.add(rs.getString(1));
				}
			}
		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<String> getTitulosAlquilados(): " + e.getMessage());
		}
		return alquiladas;
	}

	public ArrayList<String[]> getHistoricosDBPerez()
	{
		ArrayList<String[]> tupla = new ArrayList<String[]>();
		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();

			Statement  stmt = con.createStatement();


//TARJETA  	NOMBRE  	ID  	TITULO  	FECHA  	PROMOVIERNES  
//2778	DIONISIO DE HARO MORENO	260	SPIDERS	2014-07-25	TRUE
//56	CELESTINO ROSILLO	250	SPIDERMAN AMAZING 1	2014-07-25	TRUE
//56	CELESTINO ROSILLO	418	SPIDERMAN 2	2014-07-25	TRUE
//56	CELESTINO ROSILLO	450	SPIDERMAN DIBUJOS VOLUMEN 4	2014-07-25	TRUE
//11	LUIS ZAFRA RAMON	9	DREDD	2014-07-25	TRUE

			try (ResultSet rs = stmt.executeQuery("select top 2000 a.tarjeta,  b.nombre,  c.id, a.titulo, a.fecha, a.promoviernes from peliculasocio a left join socios b on a.tarjeta=b.id left join peliculas c on a.titulo = c.titulo order by a.fecha desc;")) 
			{
				// do stuff with query results.
				while (rs.next()) {
					//El truco final, meter una columna más para rellenar luego con el número de retrasos...
					String[] record  = new String[7];

					record[0] = String.valueOf(rs.getInt(1));
					if (rs.wasNull())
						record[0] = "";
					
					record[1] = rs.getString(2);
					if (rs.wasNull())
						record[1] = "";

					record[2] = String.valueOf(rs.getInt(3));
					if (rs.wasNull())
						record[2] = "";

					record[3] = rs.getString(4);
					if (rs.wasNull())
						record[3] = "";

					record[4] = rs.getDate(5).toString();
					if (rs.wasNull())
						record[4] = "";

					record[5] = String.valueOf(rs.getBoolean(6));
					if (rs.wasNull())
						record[5] = "";

					tupla.add(record);
					//System.out.println("--tupla---->"+record[0]+"|"+record[1]+"|"+record[2]+"|"+record[3]+"|"+record[4]+"|"+record[5]);
				}
				System.out.println("--public ArrayList<String[]> getHistoricosDBPerez():"+tupla.size());
			}

		} catch (Exception e){
			System.err.println("[EXCEPTION] public ArrayList<String[]> getHistoricosDBPerez(): " + e.getMessage());
		}

		this.cp.dispose();
		return tupla;

	}

	/**
	 * Te devuelve un socio de la BD dando un ID un NOMBRE, DNI o TELÉFONO
	 * @param valor
	 * @return
	 */
	public Socio getSocioDB(String valor)
	{
		Socio s = null;
		int id;
		String nombre;
		String apellido;
		int alquileres;
		String DNI;
		String dom;
		String tel;
		String email;
		String observaciones;
		Boolean avisar;
		try {
			this.cp = JdbcConnectionPool.create(cadena,user,pass);	
			Connection con = this.cp.getConnection();
			PreparedStatement  stmt = con.prepareStatement("SELECT * FROM SOCIOS WHERE ID=? OR NOMBRE=? OR DNI=? OR TELEFONO=?");
			
			
			try {
				stmt.setInt(1, Integer.valueOf(valor));
			} catch (Exception e) {
				stmt.setInt(1, -1);
			}
			
			stmt.setString(2, valor);
			stmt.setString(3, valor);
			stmt.setString(4, valor);

			
			
	
			try (ResultSet rs = stmt.executeQuery()) {
				// do stuff with query results.
				while (rs.next()) {
					id = rs.getInt(1);
	
					DNI = rs.getString(2);
					if (rs.wasNull())
						DNI = "";
	
					nombre = rs.getString(3);
					if (rs.wasNull())
						nombre = "";
	
					apellido = rs.getString(3);
					if (rs.wasNull())
						apellido = "";
	
					dom = rs.getString(4);
					if (rs.wasNull())
						dom = "";
	
					tel = rs.getString(5);
					if (rs.wasNull())
						tel = "";
	
	
					alquileres = rs.getInt(6);
					if (rs.wasNull())
						alquileres = 0;
	
					observaciones = rs.getString(7);
					if (rs.wasNull())
						observaciones = "";
	
					avisar = rs.getBoolean(8);
	
					email = rs.getString(9);
					if (rs.wasNull())
						email = "";
	
					if (rs.wasNull()) {
					}
	
					s = new Socio(id,
							nombre,
							apellido,
							alquileres,
							DNI,
							dom,
							tel,
							email,
							observaciones,
							avisar,
							null);
	
					System.out.println(s+"public Socio getSocioDB(String clave, String valor)------->"+rs.getRow());	
				}
			}
	
		} catch (Exception e){
			System.err.println("[EXCEPTION] public Socio getSocioDB(String clave, String valor): " + e.getMessage());
		}
	
		this.cp.dispose();
		return s;
	
	}

	/**
	 * Este método no hace 3 left joins, dado que no quiero sacar
	 * información del título de la película como hace el método
	 * getHistoricosDBPerez, si no que los datos de la película
	 * me dan igual, he pasado de 1.3s a 300ms
	 * @return
	 */
	public ArrayList<String[]> getHistoricosDBPerezQUICK()
		{
			ArrayList<String[]> tupla = new ArrayList<String[]>();
			try {
				this.cp = JdbcConnectionPool.create(cadena,user,pass);	
				Connection con = this.cp.getConnection();
	
				Statement  stmt = con.createStatement();
	
	
	//TARJETA  	NOMBRE  	ID  	TITULO  	FECHA  	PROMOVIERNES  
	//2778	DIONISIO DE HARO MORENO	260	SPIDERS	2014-07-25	TRUE
	//56	CELESTINO ROSILLO	250	SPIDERMAN AMAZING 1	2014-07-25	TRUE
	//56	CELESTINO ROSILLO	418	SPIDERMAN 2	2014-07-25	TRUE
	//56	CELESTINO ROSILLO	450	SPIDERMAN DIBUJOS VOLUMEN 4	2014-07-25	TRUE
	//11	LUIS ZAFRA RAMON	9	DREDD	2014-07-25	TRUE
	
				try (ResultSet rs = stmt.executeQuery("select top 2000 a.tarjeta,  b.nombre, a.id, a.titulo, a.fecha, a.promoviernes from peliculasocio a left join socios b on a.tarjeta=b.id order by a.fecha desc;")) 
				{
					// do stuff with query results.
					while (rs.next()) {
						//El truco final, meter una columna más para rellenar luego con el número de retrasos...
						String[] record  = new String[7];
	
						record[0] = String.valueOf(rs.getInt(1));
						if (rs.wasNull())
							record[0] = "";
						
						record[1] = rs.getString(2);
						if (rs.wasNull())
							record[1] = "";
	
						record[2] = String.valueOf(rs.getInt(3));
						if (rs.wasNull())
							record[2] = "";
	
						record[3] = rs.getString(4);
						if (rs.wasNull())
							record[3] = "";
	
						record[4] = rs.getDate(5).toString();
						if (rs.wasNull())
							record[4] = "";
	
						record[5] = String.valueOf(rs.getBoolean(6));
						if (rs.wasNull())
							record[5] = "";
	
						tupla.add(record);
						//System.out.println("--tupla---->"+record[0]+"|"+record[1]+"|"+record[2]+"|"+record[3]+"|"+record[4]+"|"+record[5]);
					}
					System.out.println("--public ArrayList<String[]> getHistoricosDBPerezQUICK():"+tupla.size());
				}
	
			} catch (Exception e){
				System.err.println("[EXCEPTION] public ArrayList<String[]> getHistoricosDBPerezQUICK(): " + e.getMessage());
			}
	
			this.cp.dispose();
			return tupla;
	
		}	
}
