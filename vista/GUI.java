package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import controller.Controller;
import data.Trie;
import model.Pelicula;
import model.Socio;

import javax.swing.JCheckBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.BoxLayout;



public class GUI {

	private JFrame frmVideoClubCapitn;

	private static final int alturaFila = 25;
	
	private static final int tamañoAviso = 9;//18;
	
	private static final int tamañoTexto = 9;//17;
	
	private DefaultTableCellRenderer centerRenderer = null;
	
	private static final String version = "VdeV2.4.2";

	private Controller controlador;
	private JTextField textSocios;


	private Trie tSocios = null;
	private Trie tPeliculas = null;

	private JComboBox<String> comboSocios;
	private JTextField sSoc;
	private JTextField sNom;
	private JTextField sTlf;
	private JTextField sDir;
	private JTextField sDNI;
	private JTextField textoPelicula;
	private JTextField pNom;
	private JTextField pCod;
	private JTextField pPVP;
	private JTextField pPases;
	private JTextField txtAlquilada;
	private JComboBox<String> comboHaVisto;
	private JComboBox<String> comboVincula;
	private JTextField textVincula;
	private JTextField sNum;
	private JTextField textHaVisto;
	private JComboBox<String> comboPelicula;
	private JTable tabla3Socios;
	private JTable tablaPendientes;
	private JTable tablaDeudas;
	private JTable tablaDeudores;
	private JTable tablaSocios;
	private JTable tablaPeliculas;
	private JTable tablaAlquileres;
	private JTextField pRetrasos;
	private JTextField sEmail;
	private JTextArea sNotas;

	private UtilDateModel modeloFecha;
	private JCheckBox chckbxNewCheckBox;
	private JTextPane txtpnAlquilamos;
	private JPanel pNotas;
	private JButton bAlquilar;
	private JTextField pNumSocio;
	private JTextField pNombreSocio;
	private JPanel panelAlquilarDevolver;
	private JTextField tAlquiladas;
	private JTextField tRetrasos;
	private JTextField tADevolver;



	private DefaultTableModel modeloTablaAlquileres = new DefaultTableModel(){
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return String.class;
			}
		}
	};



	public DefaultTableModel getModeloTablaAlquileres() {
		return modeloTablaAlquileres;
	}

	private DefaultTableModel modeloTablaDeudores = new DefaultTableModel() {
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return Integer.class;
			case 3:
				return String.class;
			case 4:
				return String.class;
			default:
				return String.class;
			}
		}
	};
	
	public DefaultTableModel getModeloTablaRetrasados() {
		return modeloTablaDeudores;
	}

	public DefaultTableModel getModeloTabla3Socios() {
		return modeloTabla3Socios;
	}


	private JPanel pAlquilaSocio;
	private JPanel panel_8;
	private JPanel panel_23;



	private DefaultTableModel modeloTabla3Socios = new DefaultTableModel(){
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			case 2:
				return String.class;
			default:
				return String.class;
			}
		}
	};


	private JTextPane txtpnPendientes;

	private JTextPane txtpnRetrasos;

	private DefaultTableModel modeloTablaPendientes = new DefaultTableModel(){
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			default:
				return String.class;
			}
		}
	};


	private DefaultTableModel modeloTablaDeudas = new DefaultTableModel(){
		@Override
		public Class getColumnClass(int column) {
			switch (column) {
			case 0:
				return Integer.class;
			case 1:
				return String.class;
			default:
				return String.class;
			}
		}
	};
	private JTabbedPane tabbedPane;

	private JButton bImprimirDeudores;

	private JButton bImprimirSocios;

	private JButton bImprimirPeliculas;

	private JButton bImprimirAlquileres;

	private JPanel panelBotonAlquilar;
	private JPanel seekMovie;
	private JCheckBox chckbxPromoViernes;

	public ArrayList<String> listaSimilares;

	public ArrayList<String> getListaSimilares() {
		return listaSimilares;
	}

	public void setListaSimilares(ArrayList<String> listaSimilares) {
		this.listaSimilares = listaSimilares;
	}

	public JFrame getFrmVideoClubCapitn() {
		return frmVideoClubCapitn;
	}

	public void setFrmVideoClubCapitn(JFrame frmVideoClubCapitn) {
		this.frmVideoClubCapitn = frmVideoClubCapitn;
	}

	public Trie gettSocios() {
		return tSocios;
	}

	public void settSocios(Trie tSocios) {
		this.tSocios = tSocios;
	}

	public Trie gettPeliculas() {
		return tPeliculas;
	}

	public void settPeliculas(Trie tPeliculas) {
		this.tPeliculas = tPeliculas;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();

					window.frmVideoClubCapitn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	


	/**
	 * Constructor publico.
	 */
	public GUI() {
		this.tSocios = new Trie();
		this.tPeliculas = new Trie();
		this.controlador = new Controller();

		initialize();

	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		//FUENTE GLOBAL
		setUIFont (new javax.swing.plaf.FontUIResource(new Font("ARIAL",Font.PLAIN, tamañoTexto)));
		//Fuente para avisos de colores, de pendientes, retrasadas y devolver
		Font avisos = new Font("Arial", Font.PLAIN, tamañoAviso);
		this.centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
		
		frmVideoClubCapitn = new JFrame();
		
		//Al abrir y cerrar la aplicacion
		frmVideoClubCapitn.addWindowListener( new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				textoPelicula.requestFocus();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				controlador.cerrandoAplicacion();

			}
		}); 

		frmVideoClubCapitn.setTitle("Video club Capit\u00E1n - 2014 (c) - "+version);
		frmVideoClubCapitn.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmVideoClubCapitn.setBounds(100, 100, 1280, 1024);
		frmVideoClubCapitn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmVideoClubCapitn.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblNewLabel = new JLabel(createImageIcon("/images/Capture.PNG"));
		panel.add(lblNewLabel);


		JPanel panel_reloj = new ClockPane();
		panel_reloj.setBackground(Color.LIGHT_GRAY);


		JPanel panel_21 = new JPanel();
		panel_21.add(panel_reloj);
		panel.add(panel_21);

		JPanel panelAlquiladas = new JPanel();
		panelAlquiladas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alquiladas hoy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.add(panelAlquiladas);

		settAlquiladas(new JTextField());
		gettAlquiladas().setHorizontalAlignment(SwingConstants.CENTER);
		gettAlquiladas().setText("0");
		panelAlquiladas.add(gettAlquiladas());
		gettAlquiladas().setColumns(10);

		JPanel panelRetrasos = new JPanel();
		panelRetrasos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Deudas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.add(panelRetrasos);

		tRetrasos = new JTextField();
		tRetrasos.setHorizontalAlignment(SwingConstants.CENTER);
		tRetrasos.setText("0");
		panelRetrasos.add(tRetrasos);
		tRetrasos.setColumns(10);

		JPanel panelADevolver = new JPanel();
		panelADevolver.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "A devolver hoy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_21.add(panelADevolver);

		tADevolver = new JTextField();
		tADevolver.setText("0");
		tADevolver.setHorizontalAlignment(SwingConstants.CENTER);
		tADevolver.setColumns(10);
		panelADevolver.add(tADevolver);


		tabbedPane = new JTabbedPane(JTabbedPane.TOP);


		ImageIcon iconCustomer = createImageIcon("/images/customer.PNG");
		frmVideoClubCapitn.setIconImage(iconCustomer.getImage());
		ImageIcon iconMovie = createImageIcon("/images/movie.PNG");
		

		JPanel pBusqueda = new JPanel();

		tabbedPane.addTab("PELÍCULAS", iconMovie, pBusqueda, null);
		pBusqueda.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Puedes buscar introduciendo el n\u00FAmero o parte del t\u00EDtulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pBusqueda.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_22 = new JPanel();
		pBusqueda.add(panel_22);
		panel_22.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_24 = new JPanel();
		panel_24.setBorder(new TitledBorder(null, "Actualizar/Alta de películas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_8 = (FlowLayout) panel_24.getLayout();
		flowLayout_8.setHgap(0);
		flowLayout_8.setVgap(30);
		panel_22.add(panel_24);

		JButton btnUpdatePelicula = new JButton("Actualizar pel\u00EDcula");
		btnUpdatePelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				controlador.updatePelicula(pCod.getText(), 
						pNom.getText().toUpperCase(), 
						pPases.getText(), 
						pPVP.getText(), 
						pRetrasos.getText(), 
						modeloFecha.getValue(),
						txtAlquilada.getText());
				System.out.println("Actualizando pelicula..."+pNom.getText());
			}
		});
		panel_24.add(btnUpdatePelicula);

		JButton btnAltaDeNueva = new JButton("Alta nueva pel\u00EDcula");
		btnAltaDeNueva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				controlador.altaPelicula();
			}
		});
		panel_24.add(btnAltaDeNueva);

		seekMovie = new JPanel();
		panel_22.add(seekMovie);
		seekMovie.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Aqu\u00ED aparecen coincidencias con la pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		seekMovie.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

		comboPelicula = new JComboBox<String>();

		seekMovie.add(comboPelicula);
		comboPelicula.setModel(new DefaultComboBoxModel<String>(new String[] {"Aqu\u00ED aparece la PELICULA"}));
		comboPelicula.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		textoPelicula = new JTextField();
		textoPelicula.setText("Escribe una película");
		textoPelicula.selectAll();

		//BUSCAPELI
		//Desde el jComboBox
		comboPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object item = comboPelicula.getSelectedItem();
				textoPelicula.setText(item.toString());
				textoPelicula.requestFocus();
				System.out.println("[COMBO] busca EN LA BASE DE DATOS la peli "+textoPelicula.getText());
				controlador.buscaPelicula(textoPelicula.getText());
			}
		});
		//Desde el jTextField
		textoPelicula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("[ENTER] busca EN LA BASE DE DATOS la peli "+textoPelicula.getText());
					controlador.buscaPelicula(textoPelicula.getText());
					
					
				}

				Vector<String> v = new Vector<String>(tPeliculas.autoComplete(textoPelicula.getText().toUpperCase()));
				comboPelicula.setModel(new DefaultComboBoxModel<String>(v));
				//System.out.println(tPeliculas.autoComplete(textoPelicula.getText().toUpperCase()));	
			}
		});
		textoPelicula.setColumns(20);
		seekMovie.add(textoPelicula);
		
		chckbxPromoViernes = new JCheckBox("Promo viernes");
		chckbxPromoViernes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (seekMovie.getBackground() == Color.PINK)
					seekMovie.setBackground(UIManager.getColor ( "Panel.background" ));
				else
					seekMovie.setBackground(Color.PINK);
			}
		});
		seekMovie.add(chckbxPromoViernes);
		
		
		

		panelAlquilarDevolver = new JPanel();
		panelAlquilarDevolver.setBorder(new TitledBorder(null, "Alquilar o devolver películas, pendientes y retrasos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_22.add(panelAlquilarDevolver);

		JPanel pDatos = new JPanel();
		pDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos encontrados de la pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pBusqueda.add(pDatos);
		pDatos.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		pDatos.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 3, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "T\u00EDtulo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pNom = new JTextField();
		pNom.setHorizontalAlignment(SwingConstants.CENTER);
		pNom.setColumns(20);
		panel_10.add(pNom);

		JPanel panel_11 = new JPanel();
		panel_1.add(panel_11);
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "N\u00FAmero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pCod = new JTextField();
		pCod.setEnabled(false);
		pCod.setHorizontalAlignment(SwingConstants.CENTER);
		pCod.setColumns(20);
		panel_11.add(pCod);

		JPanel panel_12 = new JPanel();
		panel_1.add(panel_12);
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fecha de alta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.modeloFecha = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(modeloFecha);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		panel_12.add(datePicker);


		JPanel panel_13 = new JPanel();
		panel_1.add(panel_13);
		panel_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Precio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pPVP = new JTextField();
		pPVP.setHorizontalAlignment(SwingConstants.CENTER);
		pPVP.setColumns(20);
		panel_13.add(pPVP);

		JPanel panel_14 = new JPanel();
		panel_1.add(panel_14);
		panel_14.setBorder(new TitledBorder(null, "Pases y retrasos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pPases = new JTextField();
		pPases.setHorizontalAlignment(SwingConstants.CENTER);
		pPases.setColumns(10);
		panel_14.add(pPases);

		pRetrasos = new JTextField();
		panel_14.add(pRetrasos);
		pRetrasos.setHorizontalAlignment(SwingConstants.CENTER);
		pRetrasos.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		txtAlquilada = new JTextField();
		txtAlquilada.setEditable(false);
		//txtAlquilada.setBackground(Color.ORANGE);
		txtAlquilada.setHorizontalAlignment(SwingConstants.CENTER);
		txtAlquilada.setColumns(20);
		panel_3.add(txtAlquilada);

		JPanel panel_19 = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_19.getLayout();
		flowLayout_6.setVgap(0);
		flowLayout_6.setHgap(0);
		pDatos.add(panel_19);
		panel_19.setBorder(new TitledBorder(null, "\u00DAltimos 3 socios que la han alquilado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		tabla3Socios = new JTable();
		tabla3Socios.setRowHeight(alturaFila);

		JScrollPane scroll3Socios = new JScrollPane(tabla3Socios, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll3Socios.setPreferredSize(new Dimension(600, 100));

		panel_19.add(scroll3Socios);

		pAlquilaSocio = new JPanel();
		pAlquilaSocio.setBorder(new TitledBorder(null, "Buscar al socio para realizar un alquiler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pDatos.add(pAlquilaSocio);
		pAlquilaSocio.setLayout(new GridLayout(2, 0, 0, 0));

		panel_8 = new JPanel();
		pAlquilaSocio.add(panel_8);

		comboVincula = new JComboBox<String>();
		comboVincula.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXX");
		panel_8.add(comboVincula);
		comboVincula.setModel(new DefaultComboBoxModel<String>(new String[] {"Aqu\u00ED aparece el SOCIO"}));


		textVincula = new JTextField();
		textVincula.setColumns(20);
		panel_8.add(textVincula);

		//VINCULA SOCIO A PELICULA
		textVincula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("[ENTER] busca EN LA BASE DE DATOS al SOCIO para vincular pelicula");
					controlador.buscaSocio(textVincula.getText());
					

				}

				Vector<String> v = new Vector<String>(tSocios.autoComplete(textVincula.getText().toUpperCase()));
				comboVincula.setModel(new DefaultComboBoxModel<String>(v));
				//System.out.println(tSocios.autoComplete(textPeliculaSocios.getText().toUpperCase()));	
			}
		});


		comboVincula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object selected = comboVincula.getSelectedItem();
				textVincula.setText(selected.toString());
				System.out.println("[COMBO] busca EN LA BASE DE DATOS al SOCIO para vincular pelicula");
				controlador.buscaSocio(textVincula.getText());
			}
		});

		panel_23 = new JPanel();
		pAlquilaSocio.add(panel_23);

		JPanel panel_16 = new JPanel();
		panel_23.add(panel_16);
		panel_16.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "N\u00FAmero de socio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_16.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		pNumSocio = new JTextField();
		pNumSocio.setHorizontalAlignment(SwingConstants.CENTER);
		pNumSocio.setEnabled(false);
		pNumSocio.setColumns(20);
		panel_16.add(pNumSocio);

		JPanel panel_20 = new JPanel();
		panel_23.add(panel_20);
		panel_20.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nombre completo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		pNombreSocio = new JTextField();
		pNombreSocio.setEnabled(false);
		pNombreSocio.setHorizontalAlignment(SwingConstants.CENTER);
		pNombreSocio.setColumns(20);
		panel_20.add(pNombreSocio);
		panelAlquilarDevolver.setLayout(new GridLayout(4, 0, 0, 0));



		panelBotonAlquilar = new JPanel();
		panelAlquilarDevolver.add(panelBotonAlquilar);

		bAlquilar = new JButton("Alquilar");
		panelBotonAlquilar.add(bAlquilar);
		bAlquilar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Alquilando película "+pCod.getText());
				controlador.alquilaPeliculaSocio(sSoc.getText(),pNom.getText(),pCod.getText(),pPases.getText(), chckbxPromoViernes.isSelected());
			}
		});
		
		bAlquilar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if (arg0.getKeyChar() == KeyEvent.VK_SPACE || arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("Alquilando película "+pCod.getText());
					controlador.alquilaPeliculaSocio(sSoc.getText(),pNom.getText(),pCod.getText(), pPases.getText(), chckbxPromoViernes.isSelected());
				}
			}
		});
		
		bAlquilar.setVisible(false);

		JPanel panel_28 = new JPanel();
		panelAlquilarDevolver.add(panel_28);


		txtpnAlquilamos = new JTextPane();
		panel_28.add(txtpnAlquilamos);
		txtpnAlquilamos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_SPACE) {
					System.out.println("Devuelve película dando al ESPACIO");
					controlador.devuelvePeliculaSocio(pCod.getText());
				}
			}
		});
		txtpnAlquilamos.setBackground(Color.GREEN);
		txtpnAlquilamos.setEditable(false);
		txtpnAlquilamos.setFont(avisos);
		txtpnAlquilamos.setText("¿Desea devolver la película? (Pulse espacio para confirmar)");
		txtpnAlquilamos.setVisible(false);

		JPanel panel_29 = new JPanel();
		panelAlquilarDevolver.add(panel_29);
		txtpnPendientes = new JTextPane();
		panel_29.add(txtpnPendientes);
		txtpnPendientes.setBackground(Color.ORANGE);
		txtpnPendientes.setEditable(false);
		txtpnPendientes.setFont(avisos);
		txtpnPendientes.setText("Tiene pendientes");
		txtpnPendientes.setVisible(false);

		JPanel panel_30 = new JPanel();
		panelAlquilarDevolver.add(panel_30);
		txtpnRetrasos = new JTextPane();
		panel_30.add(txtpnRetrasos);
		txtpnRetrasos.setBackground(Color.RED);
		txtpnRetrasos.setEditable(false);
		txtpnRetrasos.setText("Tiene retrasos");
		txtpnRetrasos.setVisible(false);

		JPanel sBusqueda = new JPanel();
		tabbedPane.addTab("SOCIOS", iconCustomer, sBusqueda, null);
		sBusqueda.setBorder(new TitledBorder(null, "Puedes buscar introduciendo cualquiera de entre: DNI, nombre, apellidos o tel\u00E9fono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sBusqueda.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pBloqueIzquierdo = new JPanel();
		sBusqueda.add(pBloqueIzquierdo);
		pBloqueIzquierdo.setLayout(new BoxLayout(pBloqueIzquierdo, BoxLayout.Y_AXIS));

		JPanel panel_25 = new JPanel();
		FlowLayout flowLayout_11 = (FlowLayout) panel_25.getLayout();
		flowLayout_11.setVgap(30);
		panel_25.setBorder(new TitledBorder(null, "Actualizar/Alta de socios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pBloqueIzquierdo.add(panel_25);

		JButton btnUpdateSocio = new JButton("Actualizar socio");
		btnUpdateSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.updateSocio(sSoc.getText(), 
						sDNI.getText(), 
						sNom.getText().toUpperCase(), 
						sDir.getText(), 
						sTlf.getText(), 
						sNum.getText(),
						sNotas.getText(),
						chckbxNewCheckBox.isSelected(),
						sEmail.getText());

				System.out.println("Actualizando socio..."+sNom.getText());
			}
		});
		panel_25.add(btnUpdateSocio);

		JButton btnAltaDeNuevo = new JButton("Alta nuevo socio");
		btnAltaDeNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				controlador.altaSocio();
			}
		});
		panel_25.add(btnAltaDeNuevo);

		JPanel seekCustomer = new JPanel();
		FlowLayout flowLayout_10 = (FlowLayout) seekCustomer.getLayout();
		flowLayout_10.setHgap(0);
		flowLayout_10.setVgap(30);
		pBloqueIzquierdo.add(seekCustomer);
		seekCustomer.setBorder(new TitledBorder(null, "Aqu\u00ED aparecen coincidencias con el socio", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		comboSocios = new JComboBox<String>();
		comboSocios.setModel(new DefaultComboBoxModel<String>(new String[] {"Click aquí para SOCIO"}));
		comboSocios.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		seekCustomer.add(comboSocios);


		textSocios = new JTextField();
		textSocios.setText("Escribe un socio");
		textSocios.selectAll();
		seekCustomer.add(textSocios);

		//BUSCA SOCIO
		comboSocios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object selected = comboSocios.getSelectedItem();
				textSocios.setText(selected.toString());
				System.out.println("[COMBO] busca EN LA BASE DE DATOS al SOCIO "+textSocios.getText());
				controlador.buscaSocio(textSocios.getText());
				
				//Tiene que ejecutarse despues de buscaSocio, no cambiar de sitio para rellenar sSoc
				System.out.println("[COMBO] busca EN LA BASE DE DATOS buscaPendientesRetrasosSocio del socio: "+sSoc.getText());
				controlador.buscaPendientesRetrasosParaTablaSocio(sSoc.getText());
			}
		});


		textSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("[ENTER] busca EN LA BASE DE DATOS al SOCIO "+textSocios.getText());
					controlador.buscaSocio(textSocios.getText());
					
					//Tiene que ejecutarse despues de buscaSocio, no cambiar de sitio para rellenar sSoc
					System.out.println("[ENTER] busca EN LA BASE DE DATOS buscaPendientesRetrasosSocio del socio: "+sSoc.getText());
					controlador.buscaPendientesRetrasosParaTablaSocio(sSoc.getText());	
				}

				Vector<String> v = new Vector<String>(tSocios.autoComplete(textSocios.getText().toUpperCase()));
				comboSocios.setModel(new DefaultComboBoxModel<String>(v));
				//System.out.println(tSocios.autoComplete(textSocios.getText().toUpperCase()));	
			}
		});
		textSocios.setColumns(20);

		JPanel pAlquiladas = new JPanel();
		pAlquiladas.setBackground(UIManager.getColor ( "Panel.background" ));
		pAlquiladas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pendientes y Retrasos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pBloqueIzquierdo.add(pAlquiladas);


		tablaPendientes = new JTable();
		tablaPendientes.setRowHeight(alturaFila);
		tablaPendientes.setBackground(Color.ORANGE);
		tablaPendientes.setEnabled(false);

		JScrollPane scrollPendientes = new JScrollPane(tablaPendientes, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPendientes.setPreferredSize(new Dimension(600, 90));
		
		
		tablaDeudas = new JTable();
		tablaDeudas.setRowHeight(alturaFila);
		tablaDeudas.setBackground(Color.RED);
		tablaDeudas.setEnabled(false);

		JScrollPane scrollDeudas = new JScrollPane(tablaDeudas, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollDeudas.setPreferredSize(new Dimension(600, 90));
		pAlquiladas.setLayout(new BoxLayout(pAlquiladas, BoxLayout.Y_AXIS));
		
		pAlquiladas.add(scrollPendientes);
		pAlquiladas.add(scrollDeudas);
		

		JPanel pBloqueDerecho = new JPanel();
		sBusqueda.add(pBloqueDerecho);
		pBloqueDerecho.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos encontrados del socio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pBloqueDerecho.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panel_17 = new JPanel();
		pBloqueDerecho.add(panel_17);
		panel_17.setLayout(new GridLayout(3, 3, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_17.add(panel_2);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "N\u00FAmero de socio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		sSoc = new JTextField();
		sSoc.setEnabled(false);
		sSoc.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(sSoc);
		sSoc.setColumns(20);

		JPanel panel_4 = new JPanel();
		panel_17.add(panel_4);
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(0);
		flowLayout_1.setVgap(0);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nombre completo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		sNom = new JTextField();
		sNom.setHorizontalAlignment(SwingConstants.CENTER);
		sNom.setColumns(20);
		panel_4.add(sNom);

		JPanel panel_5 = new JPanel();
		panel_17.add(panel_5);
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setHgap(0);
		flowLayout_4.setVgap(0);
		panel_5.setBorder(new TitledBorder(null, "Email y tel\u00E9fono", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		sEmail = new JTextField();
		sEmail.setHorizontalAlignment(SwingConstants.CENTER);
		sEmail.setColumns(9);
		panel_5.add(sEmail);

		sTlf = new JTextField();
		sTlf.setHorizontalAlignment(SwingConstants.CENTER);
		sTlf.setColumns(9);
		panel_5.add(sTlf);

		JPanel panel_6 = new JPanel();
		panel_17.add(panel_6);
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setHgap(0);
		flowLayout_2.setVgap(0);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Direcci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		sDir = new JTextField();
		sDir.setHorizontalAlignment(SwingConstants.CENTER);
		sDir.setColumns(20);
		panel_6.add(sDir);

		JPanel panel_7 = new JPanel();
		panel_17.add(panel_7);
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setHgap(0);
		flowLayout_3.setVgap(0);
		panel_7.setBorder(new TitledBorder(null, "DNI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		sDNI = new JTextField();
		sDNI.setHorizontalAlignment(SwingConstants.CENTER);
		sDNI.setColumns(20);
		panel_7.add(sDNI);

		JPanel panel_15 = new JPanel();
		panel_17.add(panel_15);
		FlowLayout flowLayout = (FlowLayout) panel_15.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_15.setBorder(new TitledBorder(null, "Alquileres", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		sNum = new JTextField();
		sNum.setHorizontalAlignment(SwingConstants.CENTER);
		sNum.setColumns(10);
		panel_15.add(sNum);

		pNotas = new JPanel();
		pNotas.setBackground(UIManager.getColor ( "Panel.background" ));
		pNotas.setBorder(new TitledBorder(null, "Notas y observaciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pBloqueDerecho.add(pNotas);
		pNotas.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		chckbxNewCheckBox = new JCheckBox("AVISAR");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (pNotas.getBackground() == Color.RED)
					pNotas.setBackground(UIManager.getColor ( "Panel.background" ));
				else
					pNotas.setBackground(Color.RED);
			}
		});
		pNotas.add(chckbxNewCheckBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		pNotas.add(scrollPane);

		sNotas = new JTextArea();
		sNotas.setRows(5);
		sNotas.setColumns(20);
		sNotas.setWrapStyleWord(false);
		sNotas.setLineWrap(true);

		scrollPane.setViewportView(sNotas);

		JPanel panel_18 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_18.getLayout();
		flowLayout_5.setVgap(0);
		flowLayout_5.setHgap(0);
		panel_18.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Consulte si alquil\u00F3 una pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pBloqueDerecho.add(panel_18);

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Aqu\u00ED aparecen coincidencias con la pel\u00EDcula", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_18.add(panel_9);

		comboHaVisto = new JComboBox<String>();
		comboHaVisto.setModel(new DefaultComboBoxModel<String>(new String[] {"Aqu\u00ED aparece la PELICULA"}));
		comboHaVisto.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		panel_9.add(comboHaVisto);
		comboHaVisto.setEnabled(false);
		textHaVisto = new JTextField();
		textHaVisto.setEnabled(false);

		comboHaVisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object selected = comboHaVisto.getSelectedItem();
				textHaVisto.setText(selected.toString());
				System.out.println("[COMBO] busca EN LA BASE DE DATOS si el socio: "+sSoc.getText()+" alquiló la peli: "+textHaVisto.getText());
				controlador.cuandoHaVistoElSocioLaPeliculaDB(textHaVisto.getText(), sSoc.getText());
			}
		});


		textHaVisto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("[ENTER] busca EN LA BASE DE DATOS si el socio: "+sSoc.getText()+" alquiló la peli:"+textHaVisto.getText());
					controlador.cuandoHaVistoElSocioLaPeliculaDB(textHaVisto.getText(), sSoc.getText());
				}

				Vector<String> v = new Vector<String>(tPeliculas.autoComplete(textHaVisto.getText().toUpperCase()));
				comboHaVisto.setModel(new DefaultComboBoxModel<String>(v));
			}
		});

		textHaVisto.setColumns(20);
		panel_9.add(textHaVisto);



		//En algun momento poner en el centro el tabbed pane
		frmVideoClubCapitn.getContentPane().add(tabbedPane, BorderLayout.CENTER);



		/////////////// L I S T A D O S (TABLA DEUDAS, SOCIOS, PELICULAS, ALQUILERES

		this.generaListados();
////////////////////////////////////////

		
		//PIE de aplicación
		JPanel pie = new JPanel();
		pie.setBackground(Color.LIGHT_GRAY);
		frmVideoClubCapitn.getContentPane().add(pie, BorderLayout.SOUTH);
		JLabel labelPie = new JLabel("Video club Capit\u00E1n - 2014 (c) - "+version);
		pie.add(labelPie);








		//Artimaña sucia para ganar el foco en los jtextfield de socio y pelicula cuando cambia de pestaña
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				
				final JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				
				System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (sourceTabbedPane.getSelectedIndex()== 0){//PELICULAS
							if (txtpnAlquilamos.isVisible())
							{
								txtpnAlquilamos.requestFocusInWindow();
							}
							else if (bAlquilar.isVisible())
							{
								bAlquilar.requestFocusInWindow();
							}
							else
							{
								textoPelicula.requestFocusInWindow();
								textoPelicula.setText("Escribe una película");
								textoPelicula.selectAll();
							}
						}
						else if (sourceTabbedPane.getSelectedIndex()== 1){//SOCIOS

							textSocios.requestFocusInWindow();
							textSocios.setText("Escribe un socio");
							textSocios.selectAll();
						}
						else if(sourceTabbedPane.getSelectedIndex()== 2){
							listaDeudores(false);
							bImprimirDeudores.requestFocusInWindow();

						}
						else if(sourceTabbedPane.getSelectedIndex()== 3){
							listaSocios();
							bImprimirSocios.requestFocusInWindow();
						}
						else if(sourceTabbedPane.getSelectedIndex()== 4){
							listaPeliculas();
							bImprimirPeliculas.requestFocusInWindow();
						}
						else if(sourceTabbedPane.getSelectedIndex()== 5){
							listaAlquileres(false);
							bImprimirAlquileres.requestFocusInWindow();
						}

					}
				});
			}
		};

		//tabbedPane.setSelectedIndex(1);
		tabbedPane.addChangeListener(changeListener);

	}



	private void generaListados() {
	
	ImageIcon iconList = createImageIcon("/images/AG00041_.PNG");
	
	////////////LISTA DEUDORES
	JPanel listaRestrasos = new JPanel();
	listaRestrasos.setBorder(new TitledBorder(null, "Listado de socios que deben películas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	tabbedPane.addTab("LISTA DE DEUDAS", iconList, listaRestrasos, null);
	listaRestrasos.setLayout(new BoxLayout(listaRestrasos, BoxLayout.X_AXIS));

	tablaDeudores = new JTable();
	tablaDeudores.setRowHeight(alturaFila);
	tablaDeudores.setDefaultRenderer(String.class, centerRenderer);
	tablaDeudores.setDefaultRenderer(Integer.class, centerRenderer);
	JScrollPane scrollRetrasos = new JScrollPane(tablaDeudores, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollRetrasos.setPreferredSize(new Dimension(850, 600));
	listaRestrasos.add(scrollRetrasos);

	bImprimirDeudores = new JButton("Imprimir lista");
	bImprimirDeudores.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {

				tablaDeudores.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}

		}
	});
	bImprimirDeudores.addKeyListener(new KeyAdapter() {
		@Override
		
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
				try {
					tablaDeudores.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	listaRestrasos.add(bImprimirDeudores);


	////////////LISTA DE SOCIOS
	JPanel listaSocios = new JPanel();
	listaSocios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de socios ordenados por tarjeta, pulsa en las columnas para reordenar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	tabbedPane.addTab("LISTA DE SOCIOS", iconList, listaSocios, null);
	listaSocios.setLayout(new BoxLayout(listaSocios, BoxLayout.X_AXIS));

	tablaSocios = new JTable();
	tablaSocios.setRowHeight(alturaFila);
	tablaSocios.setDefaultRenderer(String.class, centerRenderer);
	tablaSocios.setDefaultRenderer(Integer.class, centerRenderer);
	JScrollPane scrollSocios = new JScrollPane(tablaSocios, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollSocios.setPreferredSize(new Dimension(850, 600));
	listaSocios.add(scrollSocios);

	bImprimirSocios = new JButton("Imprimir lista");
	bImprimirSocios.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				tablaSocios.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	});
	bImprimirSocios.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
				try {
					tablaSocios.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	listaSocios.add(bImprimirSocios);


	//LISTA PELICULAS
	JPanel listaPeliculas = new JPanel();
	listaPeliculas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de películas ordenadas por título, pulsa en las columnas para reordenar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	tabbedPane.addTab("LISTA DE PELICULAS", iconList, listaPeliculas, null);
	listaPeliculas.setLayout(new BoxLayout(listaPeliculas, BoxLayout.X_AXIS));

	tablaPeliculas = new JTable();
	tablaPeliculas.setRowHeight(alturaFila);
	tablaPeliculas.setDefaultRenderer(String.class, centerRenderer);
	tablaPeliculas.setDefaultRenderer(Integer.class, centerRenderer);
	JScrollPane scrollPeliculas = new JScrollPane(tablaPeliculas, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPeliculas.setPreferredSize(new Dimension(850, 600));
	listaPeliculas.add(scrollPeliculas);

	bImprimirPeliculas = new JButton("Imprimir lista");
	bImprimirPeliculas.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				tablaPeliculas.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	});
	bImprimirPeliculas.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
				try {
					tablaPeliculas.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	listaPeliculas.add(bImprimirPeliculas);


	//LISTA DE ALQUILERES
	JPanel listaAlquileres = new JPanel();
	listaAlquileres.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de alquileres. Alquiladas hoy y a devolver hoy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	tabbedPane.addTab("LISTA DE ALQUILERES", iconList, listaAlquileres, null);
	listaAlquileres.setLayout(new BoxLayout(listaAlquileres, BoxLayout.X_AXIS));

	tablaAlquileres = new JTable();
	tablaAlquileres.setRowHeight(alturaFila);
	tablaAlquileres.setDefaultRenderer(String.class, centerRenderer);
	tablaAlquileres.setDefaultRenderer(Integer.class, centerRenderer);
	JScrollPane scrollAlquiladas = new JScrollPane(tablaAlquileres, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	scrollAlquiladas.setPreferredSize(new Dimension(850, 600));

	listaAlquileres.add(scrollAlquiladas);

	bImprimirAlquileres = new JButton("Imprimir lista");
	bImprimirAlquileres.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				tablaAlquileres.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
	});
	bImprimirAlquileres.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
				try {
					tablaAlquileres.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
	listaAlquileres.add(bImprimirAlquileres);

		
	}

	
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	private static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = GUI.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	

	public class ClockPane extends JPanel {

		private static final long serialVersionUID = 1L;
		private JLabel clock;

		public ClockPane() {
			setLayout(new BorderLayout());
			clock = new JLabel();
			clock.setHorizontalAlignment(SwingConstants.CENTER);
			clock.setFont(UIManager.getFont("Label.font").deriveFont(Font.TRUETYPE_FONT, 28f));
			tickTock();
			add(clock);

			Timer timer = new Timer(500, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tickTock();
				}
			});
			timer.setRepeats(true);
			timer.setCoalesce(true);
			timer.setInitialDelay(0);
			timer.start();
		}

		public void tickTock() {
			clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
		}
	}

	private static void setUIFont(javax.swing.plaf.FontUIResource f)
	{
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
			{
				UIManager.put(key, f);
			}
		}
	}

	public void updateVistaSocio(Socio socio) {
		if (socio!=null){
			this.sDir.setText(socio.getDom());
			this.sDNI.setText(socio.getDNI());
			this.sEmail.setText(socio.getEmail());
			this.sNom.setText(socio.getNombre());
			this.sSoc.setText(String.valueOf(socio.getId()));
			this.sTlf.setText(socio.getTel());
			this.sNotas.setText(socio.getObservaciones());
			this.sNum.setText(String.valueOf(socio.getAlquileres()));
			this.chckbxNewCheckBox.setSelected(socio.getAvisar());
			
//			Añadido por versión 2.4.0 avisar de si la ha visto salvado el pisto de los títulos					
			System.out.println("[updateVistaSocio] parecidas para el socio: ["+sSoc.getText()+ "] de la película ["+pCod.getText()+"]");
			//Si hay alguna pelicula en pantalla
			if (!pCod.getText().isEmpty())
			{
				controlador.haVistoAlgoParecido(pCod.getText(), String.valueOf(socio.getId()));
			
				if (this.getListaSimilares().size() > 0)
				{
					JOptionPane.showMessageDialog(null, getListaSimilares(), "¡Ojo que ha visto parecidas!", JOptionPane.INFORMATION_MESSAGE);
				}
			}

			//Si hay que avisar o tiene observaciones
			//Si hay que avisar OJO! EN ROJO xD
			if ( socio.getAvisar() )
			{
				pNotas.setBackground(Color.RED);
				pAlquilaSocio.setBackground(Color.RED);
				panel_23.setBackground(Color.RED);
				panel_8.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "¡Ojo, este socio tiene AVISO!.");
			}
			//Si no esta vacío el campo observaciones se pone naranja
			else if (!socio.getObservaciones().isEmpty())
			{
				pNotas.setBackground(Color.ORANGE);
				pAlquilaSocio.setBackground(Color.ORANGE);
				panel_23.setBackground(Color.ORANGE);
				panel_8.setBackground(Color.ORANGE);
			}
			else
			{
				pNotas.setBackground(UIManager.getColor("Panel.background"));
				pAlquilaSocio.setBackground(UIManager.getColor("Panel.background"));
				panel_23.setBackground(UIManager.getColor("Panel.background"));
				panel_8.setBackground(UIManager.getColor("Panel.background"));
			}
			
			//Pendiente de revisión, es para "ha visto peli" que esta deprecado encubierto por haVistoParecidas
			this.pNombreSocio.setText(socio.getNombre());
			this.pNumSocio.setText(String.valueOf(socio.getId()));
			comboHaVisto.setEnabled(true);
			textHaVisto.setEnabled(true);


			if ( this.txtAlquilada.getText().equals("DISPONIBLE") )
				logicaBotonAlquilar();
			else if ( this.txtAlquilada.getText().equals("ALQUILADA") )
				logicaPanelDevolver();
			
			
			System.out.println("[updateVistaSocio] buscaPendientesRetrasosParaTablaSocio del socio: "+ String.valueOf(socio.getId()));
			controlador.buscaPendientesRetrasosParaTablaSocio(String.valueOf(socio.getId()));	
			

		}
		else
			JOptionPane.showMessageDialog(this.getFrmVideoClubCapitn(), "No se encuentran socios con esos datos.");

	}

	public void updateVistaPelicula(Pelicula pelicula) {
		if (pelicula!=null)
		{
			this.pCod.setText(String.valueOf(pelicula.getId()));
			this.pNom.setText(pelicula.getNombre());
			this.pPases.setText(String.valueOf(pelicula.getAlquileres()));
			this.pPVP.setText(String.valueOf(pelicula.getPVP()));
			this.pRetrasos.setText(String.valueOf(pelicula.getRetrasos()));
			this.modeloFecha.setValue(pelicula.getFechaAlta());
			this.modeloFecha.setSelected(true);
//			If inline, soy pro
			this.txtAlquilada.setText(pelicula.getAlquilada() ? "ALQUILADA" : "DISPONIBLE");
			this.txtAlquilada.setBackground(pelicula.getAlquilada() ? Color.ORANGE : Color.GREEN);
//			Cambio v2.4.2
			//Tiene que ejecutarse despues de buscaPelicula, no cambiar de sitio
			//De lo contrario el campo Titulo no se sabrá (busca pelicula tiene lógica
			//para saber si se buscar por titulo o numero de pelicula
			System.out.println("busca EN LA BASE DE DATOS los 3 ultimos clientes para peli:"+textoPelicula.getText());
			controlador.busca3SociosPelicula(pNom.getText());

			if ( this.txtAlquilada.getText().equals("DISPONIBLE") )
				logicaBotonAlquilar();
			else if ( this.txtAlquilada.getText().equals("ALQUILADA") )
				logicaPanelDevolver();
		}
		else
			JOptionPane.showMessageDialog(this.getFrmVideoClubCapitn(), "No se encuentran películas con esos datos.");

	}
	

	/**
	 * La película está disponible, vamos a alquilar
	 */
	public void logicaBotonAlquilar()
	{

		this.txtpnAlquilamos.setVisible(false);
		
		//si está disponible y hay codigo de socio relleno y código de película relleno
		if (!this.pNumSocio.getText().isEmpty() && 	!this.pCod.getText().isEmpty() )
		{
			System.out.println("[Listo para alquilar]");
			this.bAlquilar.setVisible(true);
			this.bAlquilar.setEnabled(true);
			this.panelAlquilarDevolver.setBackground(Color.GREEN);
			panelBotonAlquilar.setBackground(Color.GREEN);
			this.bAlquilar.requestFocusInWindow();

		}
		//Hay codigo de pelicula relleno pero aún falta el código del socio
		else if (!this.pCod.getText().isEmpty())
		{
			this.textVincula.setText("Escribe un socio para alquilar");
			this.textVincula.requestFocusInWindow();
			this.textVincula.selectAll();
		}
		//si no hay número de película o no hay número de socio
		else if (this.pNumSocio.getText().isEmpty() || this.pCod.getText().isEmpty() )
		{
			this.bAlquilar.setEnabled(false);
			this.bAlquilar.setVisible(false);
			this.panelAlquilarDevolver.setBackground(UIManager.getColor("Panel.background"));
		}

	}
	

	/**
	 * La película está alquilada, vamos a devolverla
	 */
	public void logicaPanelDevolver()
	{
	
		this.txtpnAlquilamos.setVisible(true);
		System.out.println("[logicaPanelDevolver]"+this.txtpnAlquilamos.getText());
		this.txtpnAlquilamos.requestFocusInWindow();
		//this.textVincula.setEnabled(false);
		this.bAlquilar.setVisible(false);
		this.panelBotonAlquilar.setBackground(UIManager.getColor("Panel.background"));

	}

	public void set3SociosPelicula(ArrayList<String[]> tupla) {	
		modeloTabla3Socios.setRowCount(0);
		modeloTabla3Socios.setColumnIdentifiers(new String[] {"Nombre", "Tarjeta", "Fecha"});
		for (String[] t : tupla)
			modeloTabla3Socios.addRow(new Object[] {t[0], Integer.valueOf(t[1]), t[2]});

		this.tabla3Socios.setModel(modeloTabla3Socios);
		this.tabla3Socios.getColumnModel().getColumn(0).setPreferredWidth(350);
		this.tabla3Socios.getColumnModel().getColumn(0).setMinWidth(20);
		this.tabla3Socios.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.tabla3Socios.getColumnModel().getColumn(1).setMinWidth(20);
		this.tabla3Socios.getColumnModel().getColumn(2).setPreferredWidth(150);
		this.tabla3Socios.getColumnModel().getColumn(2).setMinWidth(20);

	}



	public void altaSocio(String id) {
		this.sSoc.setText(id);	
	}

	public void altaPelicula(String id) {
		this.pCod.setText(id);

	}

	public void blanqueaSocio(boolean b){
		this.sDir.setText("");
		this.sDNI.setText("");
		this.sEmail.setText("");
		this.sNom.setText("");
		//deja la posibilidad de borrar el id o no
		if (b)
			this.sSoc.setText("");
		this.sTlf.setText("");
		this.sNotas.setText("");
		this.sNum.setText("");
		this.chckbxNewCheckBox.setSelected(false);
		this.pNotas.setBackground(UIManager.getColor("Panel.background"));
		this.pNombreSocio.setText("");
		this.pNumSocio.setText("");
		modeloTablaDeudas.setRowCount(0);
		modeloTablaPendientes.setRowCount(0);
		this.comboHaVisto.setEnabled(false);
		this.textHaVisto.setEnabled(false);
		//this.comboVincula.setEnabled(false);
		this.textVincula.setText("");



	}

	public void blanqueaPelicula(boolean b){

		this.pNom.setText("");
		this.pPases.setText("");
		this.pPVP.setText("");
		this.pRetrasos.setText("");
		this.modeloFecha.setValue(new Date());
		this.modeloFecha.setSelected(true);
		this.bAlquilar.setVisible(false);
		this.txtAlquilada.setBackground(UIManager.getColor("Panel.background"));
		this.pNotas.setBackground(UIManager.getColor("Panel.background"));
		this.pAlquilaSocio.setBackground(UIManager.getColor("Panel.background"));
		this.panel_23.setBackground(UIManager.getColor("Panel.background"));
		this.panel_8.setBackground(UIManager.getColor("Panel.background"));
		
		this.panelBotonAlquilar.setBackground(UIManager.getColor("Panel.background"));
		
		this.panelAlquilarDevolver.setBackground(UIManager.getColor("Panel.background"));
		this.txtAlquilada.setText("");
		this.txtpnAlquilamos.setVisible(false);
		this.txtpnPendientes.setVisible(false);
		this.txtpnRetrasos.setVisible(false);
		if (b){
			this.pCod.setText("");	
			this.textoPelicula.requestFocusInWindow();
			this.textoPelicula.selectAll();
		}
		this.modeloTabla3Socios.setRowCount(0);

	}

	public void updateRetrasos(int retrasos) 
	{
		this.tRetrasos.setText(String.valueOf(retrasos));

	}
	
	public void updateADevolverHoy(int aDevolverHoy) 
	{
		this.tADevolver.setText(String.valueOf(aDevolverHoy));

	}

	public void updateAlquiladasHoy(int alquiladasHoy) 
	{
		this.gettAlquiladas().setText(String.valueOf(alquiladasHoy));	
	}

	public JTextField gettAlquiladas() {
		return tAlquiladas;
	}

	public void settAlquiladas(JTextField tAlquiladas) {
		this.tAlquiladas = tAlquiladas;
	}

	public void listaDeudores(Boolean quick)
	{
		modeloTablaDeudores.setRowCount(0);
		//TARJETA  	NOMBRE  	ID  	TITULO  	FECHA  	PROMOVIERNES  
		//2778	DIONISIO DE HARO MORENO	260	SPIDERS	2014-07-25	TRUE	
		modeloTablaDeudores.setColumnIdentifiers(new String[] {"Tarjeta", "Nombre","Código","Título", "Fecha", "Retrasos"});
		ArrayList<String[]> tupla = controlador.getDeudores(quick);
		for (String[] t : tupla)
			modeloTablaDeudores.addRow(new Object[] {Integer.valueOf(t[0]), t[1], Integer.valueOf(t[2]), t[3], t[4], t[6]});

		tablaDeudores.setModel(modeloTablaDeudores);	
		tablaDeudores.getColumnModel().getColumn(0).setPreferredWidth(75);
		tablaDeudores.getColumnModel().getColumn(0).setMinWidth(20);
		tablaDeudores.getColumnModel().getColumn(1).setPreferredWidth(250);
		tablaDeudores.getColumnModel().getColumn(1).setMinWidth(20);
		tablaDeudores.getColumnModel().getColumn(2).setPreferredWidth(75);
		tablaDeudores.getColumnModel().getColumn(2).setMinWidth(20);
		tablaDeudores.getColumnModel().getColumn(3).setPreferredWidth(280);
		tablaDeudores.getColumnModel().getColumn(3).setMinWidth(20);
		tablaDeudores.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaDeudores.getColumnModel().getColumn(4).setMinWidth(20);
		tablaDeudores.getColumnModel().getColumn(5).setPreferredWidth(70);
		tablaDeudores.getColumnModel().getColumn(5).setMinWidth(20);

		tablaDeudores.setAutoCreateRowSorter(true);
		tablaDeudores.getRowSorter().toggleSortOrder(1);
	}

	public void listaAlquileres(Boolean quick)
	{
		modeloTablaAlquileres.setRowCount(0);
		//[56, CELESTINO ROSILLO, 418, SPIDERMAN 2, 2014-07-25, null]
		modeloTablaAlquileres.setColumnIdentifiers(new String[] {"Tarjeta", "Nombre","Código","Título", "Fecha"});
		ArrayList<String[]> tupla = controlador.getAlquileres(quick);	
		for (String[] t : tupla)
			modeloTablaAlquileres.addRow(new Object[] {Integer.valueOf(t[0]), t[1], Integer.valueOf(t[2]), t[3], t[4]});

		tablaAlquileres.setModel(modeloTablaAlquileres);
		tablaAlquileres.getColumnModel().getColumn(0).setPreferredWidth(75);
		tablaAlquileres.getColumnModel().getColumn(0).setMinWidth(20);
		tablaAlquileres.getColumnModel().getColumn(1).setPreferredWidth(300);
		tablaAlquileres.getColumnModel().getColumn(1).setMinWidth(20);
		tablaAlquileres.getColumnModel().getColumn(2).setPreferredWidth(75);
		tablaAlquileres.getColumnModel().getColumn(2).setMinWidth(20);
		tablaAlquileres.getColumnModel().getColumn(3).setPreferredWidth(300);
		tablaAlquileres.getColumnModel().getColumn(3).setMinWidth(20);
		tablaAlquileres.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaAlquileres.getColumnModel().getColumn(4).setMinWidth(20);

		tablaAlquileres.setAutoCreateRowSorter(true);
		tablaAlquileres.getRowSorter().toggleSortOrder(1);
	}

	private void listaSocios()
	{
		//LISTA SOCIOS){
		DefaultTableModel modeloTablaSocios = new DefaultTableModel(){
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		modeloTablaSocios.setColumnIdentifiers(new String[] {"Tarjeta", "Nombre", "Número de alquileres"});
		ArrayList<Socio> aux = controlador.getSocios();

		for (Socio s :aux)
			modeloTablaSocios.addRow(new Object[] { Integer.valueOf(s.getId()), s.getNombre(), s.getAlquileres()});

		tablaSocios.setModel(modeloTablaSocios);
		tablaSocios.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaSocios.getColumnModel().getColumn(0).setMinWidth(20);
		tablaSocios.getColumnModel().getColumn(1).setPreferredWidth(350);
		tablaSocios.getColumnModel().getColumn(1).setMinWidth(20);
		tablaSocios.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaSocios.getColumnModel().getColumn(2).setMinWidth(20);

		tablaSocios.setAutoCreateRowSorter(true);
		tablaSocios.getRowSorter().toggleSortOrder(0);
	}

	private void listaPeliculas()
	{
		//LISTA PELICULAS){
		DefaultTableModel modeloTablaPeliculas = new DefaultTableModel(){
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		modeloTablaPeliculas.setColumnIdentifiers(new String[] {"Código", "Título", "Fecha de alta", "Precio"});
		ArrayList<Pelicula> aux = controlador.getPeliculas();

		for (Pelicula p :aux)
			modeloTablaPeliculas.addRow(new Object[] {Integer.valueOf(p.getId()), p.getNombre(), p.getFechaAlta().toString(), Integer.valueOf(p.getPVP()),});

		tablaPeliculas.setModel(modeloTablaPeliculas);
		tablaPeliculas.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablaPeliculas.getColumnModel().getColumn(0).setMinWidth(20);
		tablaPeliculas.getColumnModel().getColumn(1).setPreferredWidth(500);
		tablaPeliculas.getColumnModel().getColumn(1).setMinWidth(20);
		tablaPeliculas.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablaPeliculas.getColumnModel().getColumn(2).setMinWidth(20);
		tablaPeliculas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaPeliculas.getColumnModel().getColumn(3).setMinWidth(20);

		tablaPeliculas.setAutoCreateRowSorter(true);
		tablaPeliculas.getRowSorter().toggleSortOrder(1);
	}

	public void cartelDeudas(int idSocio, int retrasos, boolean visible) {
		String text = new String();
		if (visible) {
			text = "El socio " + idSocio + " debe " + retrasos + " película(s)";
			this.txtpnRetrasos.setVisible(true);
			this.txtpnRetrasos.setText(text);					
		}
		else
			this.txtpnRetrasos.setVisible(false);

	}
	/**
	 * Cartel para pelis que se alquilaron hoy o se devuelven hoy,
	 * Las pendientes son las que alquilan sin problemas, es decir,
	 * no ocasionan molestia al videoclub. Los retrasos son cuando
	 * se han pasado el día de la devolución
	 * @param idSocio
	 * @param pendientes
	 * @param visible
	 */
	public void cartelPendientes(int idSocio, int pendientes, boolean visible) 
	{
		String text = new String();
		if (visible) {
			text = "El socio " + idSocio + " tiene " + pendientes
					+ " película(s) pendiente(s)";
			this.txtpnPendientes.setVisible(true);
			this.txtpnPendientes.setText(text);
		}
		else
			this.txtpnPendientes.setVisible(false);

	}


	public void cartelRetrasos(int p_idSocio, String valor, int retrasos, boolean visible) {
		if (visible)
		{
			this.txtpnAlquilamos.setText("        (Pulse espacio para devolver)\n"+retrasos+" retrasos en "+valor+".");
			this.txtpnAlquilamos.setBackground(Color.RED);
		}
		else
		{
			this.txtpnAlquilamos.setText("¿Desea devolver la película? (Pulse espacio para confirmar)");
			this.txtpnAlquilamos.setBackground(Color.GREEN);
		}
		
		
	}

	public void setTablaPendientes(ArrayList<String[]> tupla) {
		int id = 0;
		modeloTablaPendientes.setRowCount(0);
		modeloTablaPendientes.setColumnIdentifiers(new String[] {"Código","Título", "Fecha"});
		for (String[] t : tupla){
			//ñApa estelar
			//Edito a fuego la columna de los ids de la peli para no perder tiempo con 3 left joints
			id = controlador.getIDPelicula(t[3]);
			modeloTablaPendientes.addRow(new Object[] {id, t[3], t[4]});
		}
		this.tablaPendientes.setModel(modeloTablaPendientes);	
		this.tablaPendientes.getColumnModel().getColumn(0).setPreferredWidth(80);
		this.tablaPendientes.getColumnModel().getColumn(0).setMinWidth(20);
		this.tablaPendientes.getColumnModel().getColumn(1).setPreferredWidth(370);
		this.tablaPendientes.getColumnModel().getColumn(1).setMinWidth(20);
		this.tablaPendientes.getColumnModel().getColumn(2).setPreferredWidth(150);
		this.tablaPendientes.getColumnModel().getColumn(2).setMinWidth(20);

	}
	
	public void setTablaDeudas(ArrayList<String[]> tupla) {
		int id = 0;
		modeloTablaDeudas.setRowCount(0);
		modeloTablaDeudas.setColumnIdentifiers(new String[] {"Código","Título", "Fecha", "Retrasos"});
		for (String[] t : tupla){
			//ñApa estelar
			//Edito a fuego la columna de los ids de la peli para no perder tiempo con 3 left joints
			id = controlador.getIDPelicula(t[3]);
			modeloTablaDeudas.addRow(new Object[] {id, t[3], t[4], t[5]});
		}
		this.tablaDeudas.setModel(modeloTablaDeudas);		
		this.tablaDeudas.getColumnModel().getColumn(0).setPreferredWidth(80);
		this.tablaDeudas.getColumnModel().getColumn(0).setMinWidth(20);
		this.tablaDeudas.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.tablaDeudas.getColumnModel().getColumn(1).setMinWidth(20);
		this.tablaDeudas.getColumnModel().getColumn(2).setPreferredWidth(150);
		this.tablaDeudas.getColumnModel().getColumn(2).setMinWidth(20);
		this.tablaDeudas.getColumnModel().getColumn(3).setPreferredWidth(70);
		this.tablaDeudas.getColumnModel().getColumn(3).setMinWidth(20);

	}



}
