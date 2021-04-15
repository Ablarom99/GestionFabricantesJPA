package gui;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

public class PanelCliente extends JPanel{

	Cliente actual = new Cliente();
	private JTextField jtfid;
	private JTextField jtfnombre;
	private JTextField jtfapellidos;
	private JTextField jtflocalidad;
	private JTextField jtfdniNie;
	private JCheckBox jcbActivo;
	private JFormattedTextField jtfFechaNac;
	private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");


	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public PanelCliente() {
		super();
		initialize();
		this.actual = ControladorCliente.getInstance().findPrimero();
		cargarActualEnPantalla();
	}

	private void cargarActualEnPantalla() {
		if (this.actual != null) {
			this.jtfid.setText("" + this.actual.getId());
			this.jtfnombre.setText(this.actual.getNombre());
			this.jtfapellidos.setText(this.actual.getApellidos());
			this.jtflocalidad.setText(this.actual.getLocalidad());
			this.jtfFechaNac.setText(this.formatoFecha.format(this.actual.getFechaNac()));
			this.jtfdniNie.setText(this.actual.getDniNie());
			this.jcbActivo.setSelected(this.actual.isActivo());
		}
	}
	private void cargarActualDesdePantalla() {
		this.jtfid.setText("" + this.actual.getId());
		this.jtfnombre.setText(this.actual.getNombre());
		this.jtfapellidos.setText(this.actual.getApellidos());
		this.jtflocalidad.setText(this.actual.getLocalidad());
		this.jtfdniNie.setText(this.actual.getDniNie());
		try {
            this.actual.setFechaNac(this.formatoFecha.parse(jtfFechaNac.getText()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		this.actual.setActivo(jcbActivo.isSelected());
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblid = new JLabel("id");
		GridBagConstraints gbc_lblid = new GridBagConstraints();
		gbc_lblid.anchor = GridBagConstraints.EAST;
		gbc_lblid.insets = new Insets(0, 0, 5, 5);
		gbc_lblid.gridx = 0;
		gbc_lblid.gridy = 0;
		add(lblid, gbc_lblid);
		
		jtfid = new JTextField();
		GridBagConstraints gbc_jtfid = new GridBagConstraints();
		gbc_jtfid.insets = new Insets(0, 0, 5, 0);
		gbc_jtfid.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfid.gridx = 1;
		gbc_jtfid.gridy = 0;
		add(jtfid, gbc_jtfid);
		jtfid.setColumns(10);
		
		JLabel lblnombre = new JLabel("nombre");
		GridBagConstraints gbc_lblnombre = new GridBagConstraints();
		gbc_lblnombre.anchor = GridBagConstraints.EAST;
		gbc_lblnombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblnombre.gridx = 0;
		gbc_lblnombre.gridy = 1;
		add(lblnombre, gbc_lblnombre);
		
		jtfnombre = new JTextField();
		GridBagConstraints gbc_jtfnombre = new GridBagConstraints();
		gbc_jtfnombre.insets = new Insets(0, 0, 5, 0);
		gbc_jtfnombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfnombre.gridx = 1;
		gbc_jtfnombre.gridy = 1;
		add(jtfnombre, gbc_jtfnombre);
		jtfnombre.setColumns(10);
		
		JLabel lblapellidos = new JLabel("apellidos");
		GridBagConstraints gbc_lblapellidos = new GridBagConstraints();
		gbc_lblapellidos.anchor = GridBagConstraints.EAST;
		gbc_lblapellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblapellidos.gridx = 0;
		gbc_lblapellidos.gridy = 2;
		add(lblapellidos, gbc_lblapellidos);
		
		jtfapellidos = new JTextField();
		GridBagConstraints gbc_jtfapellidos = new GridBagConstraints();
		gbc_jtfapellidos.insets = new Insets(0, 0, 5, 0);
		gbc_jtfapellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfapellidos.gridx = 1;
		gbc_jtfapellidos.gridy = 2;
		add(jtfapellidos, gbc_jtfapellidos);
		jtfapellidos.setColumns(10);
		
		JLabel lbllocalidad = new JLabel("localidad");
		GridBagConstraints gbc_lbllocalidad = new GridBagConstraints();
		gbc_lbllocalidad.anchor = GridBagConstraints.EAST;
		gbc_lbllocalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lbllocalidad.gridx = 0;
		gbc_lbllocalidad.gridy = 3;
		add(lbllocalidad, gbc_lbllocalidad);
		
		jtflocalidad = new JTextField();
		GridBagConstraints gbc_jtflocalidad = new GridBagConstraints();
		gbc_jtflocalidad.insets = new Insets(0, 0, 5, 0);
		gbc_jtflocalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtflocalidad.gridx = 1;
		gbc_jtflocalidad.gridy = 3;
		add(jtflocalidad, gbc_jtflocalidad);
		jtflocalidad.setColumns(10);
		
		JLabel lbldniNie = new JLabel("dniNie");
		GridBagConstraints gbc_lbldniNie = new GridBagConstraints();
		gbc_lbldniNie.anchor = GridBagConstraints.EAST;
		gbc_lbldniNie.insets = new Insets(0, 0, 5, 5);
		gbc_lbldniNie.gridx = 0;
		gbc_lbldniNie.gridy = 4;
		add(lbldniNie, gbc_lbldniNie);
		
		jtfdniNie = new JTextField();
		GridBagConstraints gbc_jtfdniNie = new GridBagConstraints();
		gbc_jtfdniNie.insets = new Insets(0, 0, 5, 0);
		gbc_jtfdniNie.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfdniNie.gridx = 1;
		gbc_jtfdniNie.gridy = 4;
		add(jtfdniNie, gbc_jtfdniNie);
		jtfdniNie.setColumns(10);
		
		JLabel lblfechaNac = new JLabel("fechaNac");
		GridBagConstraints gbc_lblfechaNac = new GridBagConstraints();
		gbc_lblfechaNac.anchor = GridBagConstraints.EAST;
		gbc_lblfechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_lblfechaNac.gridx = 0;
		gbc_lblfechaNac.gridy = 5;
		add(lblfechaNac, gbc_lblfechaNac);
		
		jtfFechaNac = new JFormattedTextField();
		GridBagConstraints gbc_jtffechaNac = new GridBagConstraints();
		gbc_jtffechaNac.insets = new Insets(0, 0, 5, 0);
		gbc_jtffechaNac.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtffechaNac.gridx = 1;
		gbc_jtffechaNac.gridy = 5;
		add(jtfFechaNac, gbc_jtffechaNac);
		jtfFechaNac.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		jcbActivo = new JCheckBox("Activo");
		GridBagConstraints gbc_jcbActivo = new GridBagConstraints();
		gbc_jcbActivo.insets = new Insets(0, 0, 0, 5);
		gbc_jcbActivo.gridx = 1;
		gbc_jcbActivo.gridy = 0;
		panel.add(jcbActivo, gbc_jcbActivo);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 7;
		add(panel_1, gbc_panel_1);
		
		JButton btnPrimero = new JButton("<<");
		panel_1.add(btnPrimero);
		
		JButton btnAnterior = new JButton("<");
		panel_1.add(btnAnterior);
		
		JButton btnSiguiente = new JButton(">");
		panel_1.add(btnSiguiente);
		
		JButton btnUltimo = new JButton(">>");
		panel_1.add(btnUltimo);
		
		JButton btnGuardar = new JButton("Guardar");
		panel_1.add(btnGuardar);
		
		JButton btnNuevo = new JButton("Nuevo");
		panel_1.add(btnNuevo);
		
		JButton btnBorrar = new JButton("Borrar");
		panel_1.add(btnBorrar);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaciarCampos();
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorCliente.getInstance().findUltimo();
				cargarActualEnPantalla();
			}
		});
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorCliente.getInstance().findSiguiente(actual.getId());
				cargarActualEnPantalla();
			}
		});
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorCliente.getInstance().findAnterior(actual.getId());
				cargarActualEnPantalla();
			}
		});
		btnPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual = ControladorCliente.getInstance().findPrimero();
				cargarActualEnPantalla();
			}
		});
		
		this.actual = ControladorCliente.getInstance().findPrimero();
		cargarActualEnPantalla();
		
	}
	
	
	private void guardar () {
		cargarActualDesdePantalla();
		// Decido si se trata de guardar un registro existente o nuevo
		if (this.actual.getId() != 0) { // Modificacion
			int regsAfec = ControladorCliente.getInstance().modificar(this.actual);
			if (regsAfec > 0) {
				JOptionPane.showMessageDialog(null, "Registro modificado correctamente");
			}
		}
		else { // Alta -  nuevo
			int idNuevoReg = ControladorCliente.getInstance().nuevo(this.actual);
			if (idNuevoReg > 0) {
				this.jtfid.setText("" + idNuevoReg);
				JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
			}
		}
	}
	
	private void vaciarCampos() {
		this.jtfid.setText("0");
        this.jtfnombre.setText("");
        this.jtfapellidos.setText("");
        this.jtfdniNie.setText("");
        this.jtfFechaNac.setText("");
        this.jtflocalidad.setText("");
        this.jcbActivo.setSelected(false);

	}
	
	private void borrar() {
		String posiblesRespuestas[] = {"Si�","No"};
		// En esta opcion se utiliza un showOptionDialog en el que personalizo el icono mostrado
		int opcionElegida = JOptionPane.showOptionDialog(null, "�Desea eliminar?", "Gestion venta de coches", 
		        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, posiblesRespuestas, posiblesRespuestas[1]);
	    if(opcionElegida == 0) {
	    	int regsAfectados = ControladorCliente.getInstance().borrar(this.actual.getId());
	    	if (regsAfectados > 0) {
	    		vaciarCampos();
	    		JOptionPane.showMessageDialog(null, "Eliminado correctamente");
	    	}
	    }
	}

}
