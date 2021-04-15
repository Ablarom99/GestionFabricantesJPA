package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class PanelVenta extends JPanel{
	
	Venta actual = new Venta();

	private JTextField jtfid;
	private JTextField jtfFecha;
	private JTextField jtfprecioVenta;
	private JButton btnPrimero;
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private JButton btnUltimo;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnBorrar;
	private JComboBox<Cliente> jcbCliente;
	private JComboBox<Concesionario> jcbConcesionario;
	private JComboBox<Coche> jcbCoche;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	
	

	/**
	 * Create the application.
	 */
	public PanelVenta() {
		initialize();
		this.actual = ControladorVenta.getInstance().findPrimero();
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		

		btnPrimero = new JButton("<<");
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorVenta.getInstance().findPrimero();
				cargarActualEnPantalla();
			}
		});
		panel.add(btnPrimero);
		
		btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorVenta.getInstance().findAnterior(actual.getId());
				cargarActualEnPantalla();
			}
		});
		panel.add(btnAnterior);
		
		btnSiguiente = new JButton(">");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorVenta.getInstance().findSiguiente(actual.getId());
				cargarActualEnPantalla();
			}
		});
		panel.add(btnSiguiente);
		
		btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorVenta.getInstance().findUltimo();
				cargarActualEnPantalla();
			}
		});
		panel.add(btnUltimo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		panel.add(btnGuardar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarCampos();
			}
		});
		panel.add(btnNuevo);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		panel.add(btnBorrar);
		
	}
	
	private void cargarDatos() {
		List<Coche> coches = ControladorCoche.getInstance().findAll();
		List<Cliente> clientes = ControladorCliente.getInstance().findAll();
		List<Concesionario> concesionarios = ControladorConcesionario.getInstance().findAll();

		for (Coche f : coches) {
			this.jcbCoche.addItem(f);
		}
		for (Cliente c : clientes) {
			this.jcbCliente.addItem(c);
		}
		for (Concesionario c : concesionarios) {
			this.jcbConcesionario.addItem(c);
		}

	}

	/**
	 * 
	 */
	private void cargarActualEnPantalla() {
		if (this.actual != null) {
			this.jtfid.setText("" + this.actual.getId());
			this.jtfFecha.setText(this.formatoFecha.format(this.actual.getFecha()));
			this.jtfprecioVenta.setText("" + this.actual.getPrecioVenta());
			// cliente
			for (int j = 0; j < jcbCliente.getItemCount(); j++) {
				if (this.actual.getIdCliente() == this.jcbCliente.getItemAt(j).getId()) {
					this.jcbCliente.setSelectedIndex(j);
				}

			}

			// concesionario
			for (int j = 0; j < jcbConcesionario.getItemCount(); j++) {
				if (this.actual.getIdConcesionario() == this.jcbConcesionario.getItemAt(j).getId()) {
					this.jcbConcesionario.setSelectedIndex(j);
				}

			}
			// coche
			for (int j = 0; j < jcbCoche.getItemCount(); j++) {
				if (this.actual.getIdCoche() == this.jcbCoche.getItemAt(j).getId()) {
					this.jcbCoche.setSelectedIndex(j);
				}

			}

		}
	}
	
	private void borrar() {
		String posiblesRespuestas[] = {"Si�","No"};
		// En esta opcion se utiliza un showOptionDialog en el que personalizo el icono mostrado
		int opcionElegida = JOptionPane.showOptionDialog(null, "�Desea eliminar?", "Gestion venta de coches", 
		        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, posiblesRespuestas, posiblesRespuestas[1]);
	    if(opcionElegida == 0) {
	    	int regsAfectados = ControladorVenta.getInstance().borrar(this.actual.getId());
	    	if (regsAfectados > 0) {
	    		vaciarCampos();
	    		JOptionPane.showMessageDialog(null, "Eliminado correctamente");
	    	}
	    }
	}
	
	private void guardar () {
		cargarActualDesdePantalla();
		// Decido si se trata de guardar un registro existente o nuevo
		if (this.actual.getId() != 0) { // Modificacion
			int regsAfec = ControladorVenta.getInstance().modificar(this.actual);
			if (regsAfec > 0) {
				JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
			}
		}
		else { // Alta -  nuevo
			int idNuevoReg = ControladorVenta.getInstance().nuevo(this.actual);
			if (idNuevoReg > 0) {
				this.jtfid.setText("" + idNuevoReg);
				JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
			}
		}
	}
	
	
	
	

	/**
	 * 
	 */
	private void cargarActualDesdePantalla() {
		this.actual.setId(Integer.parseInt(jtfid.getText()));
		this.actual.setPrecioVenta(Integer.parseInt(jtfprecioVenta.getText()));
		try {
			this.actual.setFecha(this.formatoFecha.parse(jtfFecha.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.actual.setIdCliente(((Cliente)jcbCliente.getSelectedItem()).getId());
		this.actual.setIdConcesionario(((Concesionario)jcbConcesionario.getSelectedItem()).getId());
		this.actual.setIdCoche(((Coche)jcbCoche.getSelectedItem()).getId());
	}
	
	/**
	 * 
	 */
	private void vaciarCampos() {
		this.jtfid.setText("0");
		this.jcbCliente.setSelectedItem(null);
		this.jcbConcesionario.setSelectedItem(null);
		this.jcbCoche.setSelectedItem(null);
		this.jtfFecha.setText("");
		this.jtfprecioVenta.setText("");
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblid = new JLabel("id");
		GridBagConstraints gbc_lblid = new GridBagConstraints();
		gbc_lblid.anchor = GridBagConstraints.EAST;
		gbc_lblid.insets = new Insets(0, 0, 5, 5);
		gbc_lblid.gridx = 0;
		gbc_lblid.gridy = 0;
		add(lblid, gbc_lblid);
		
		jtfid = new JTextField();
		jtfid.setEnabled(false);
		GridBagConstraints gbc_jtfid = new GridBagConstraints();
		gbc_jtfid.insets = new Insets(0, 0, 5, 0);
		gbc_jtfid.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfid.gridx = 1;
		gbc_jtfid.gridy = 0;
		add(jtfid, gbc_jtfid);
		jtfid.setColumns(10);
		
		JLabel lblidCliente = new JLabel("idCliente");
		GridBagConstraints gbc_lblidCliente = new GridBagConstraints();
		gbc_lblidCliente.anchor = GridBagConstraints.EAST;
		gbc_lblidCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblidCliente.gridx = 0;
		gbc_lblidCliente.gridy = 1;
		add(lblidCliente, gbc_lblidCliente);
		
		jcbCliente = new JComboBox();
		GridBagConstraints gbc_jcbCliente = new GridBagConstraints();
		gbc_jcbCliente.insets = new Insets(0, 0, 5, 0);
		gbc_jcbCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCliente.gridx = 1;
		gbc_jcbCliente.gridy = 1;
		add(jcbCliente, gbc_jcbCliente);
		
		JLabel lblidConcesionario = new JLabel("idConcesionario");
		GridBagConstraints gbc_lblidConcesionario = new GridBagConstraints();
		gbc_lblidConcesionario.anchor = GridBagConstraints.EAST;
		gbc_lblidConcesionario.insets = new Insets(0, 0, 5, 5);
		gbc_lblidConcesionario.gridx = 0;
		gbc_lblidConcesionario.gridy = 2;
		add(lblidConcesionario, gbc_lblidConcesionario);
		
		jcbConcesionario = new JComboBox();
		GridBagConstraints gbc_jcbConcesionario = new GridBagConstraints();
		gbc_jcbConcesionario.insets = new Insets(0, 0, 5, 0);
		gbc_jcbConcesionario.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbConcesionario.gridx = 1;
		gbc_jcbConcesionario.gridy = 2;
		add(jcbConcesionario, gbc_jcbConcesionario);
		
		JLabel lblCoche = new JLabel("idCoche");
		GridBagConstraints gbc_lblCoche = new GridBagConstraints();
		gbc_lblCoche.anchor = GridBagConstraints.EAST;
		gbc_lblCoche.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoche.gridx = 0;
		gbc_lblCoche.gridy = 3;
		add(lblCoche, gbc_lblCoche);
		
		jcbCoche = new JComboBox();
		GridBagConstraints gbc_jcbCoche = new GridBagConstraints();
		gbc_jcbCoche.insets = new Insets(0, 0, 5, 0);
		gbc_jcbCoche.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcbCoche.gridx = 1;
		gbc_jcbCoche.gridy = 3;
		add(jcbCoche, gbc_jcbCoche);
		
		JLabel lblFecha = new JLabel("fecha");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 4;
		add(lblFecha, gbc_lblFecha);
		
		jtfFecha = new JTextField();
		GridBagConstraints gbc_jtfFecha = new GridBagConstraints();
		gbc_jtfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_jtfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFecha.gridx = 1;
		gbc_jtfFecha.gridy = 4;
		add(jtfFecha, gbc_jtfFecha);
		jtfFecha.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("precioVenta");
		GridBagConstraints gbc_lblPrecioVenta = new GridBagConstraints();
		gbc_lblPrecioVenta.anchor = GridBagConstraints.EAST;
		gbc_lblPrecioVenta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioVenta.gridx = 0;
		gbc_lblPrecioVenta.gridy = 5;
		add(lblPrecioVenta, gbc_lblPrecioVenta);
		
		jtfprecioVenta = new JTextField();
		GridBagConstraints gbc_jtfprecioVenta = new GridBagConstraints();
		gbc_jtfprecioVenta.insets = new Insets(0, 0, 5, 0);
		gbc_jtfprecioVenta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfprecioVenta.gridx = 1;
		gbc_jtfprecioVenta.gridy = 5;
		add(jtfprecioVenta, gbc_jtfprecioVenta);
		jtfprecioVenta.setColumns(10);
		
		
		cargarDatos();
		this.actual = ControladorVenta.getInstance().findPrimero();
		cargarActualEnPantalla();
	}
	
	
	

}
