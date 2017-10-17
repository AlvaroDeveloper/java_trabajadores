package Trabajadores;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Trabajadores extends JFrame {

    //Instancia de formularios
	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	private JTable tblTrabajadores;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtMatricula;
	private JTextField txtSalario;
	private JTextField txtFecha;
	private DefaultTableModel modeloTabla;
	
	//Variables Globales de Conexion
	private Conexion claseConexion = new Conexion();
	private Connection conexion = claseConexion.obtenerConexion(); 
	private Statement statement;
	private ResultSet resultset;
	
	//Metodo principal
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trabajadores trabajadores = new Trabajadores();
					trabajadores.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creacion de JFrame Trabajadoress
	 */
	public Trabajadores() {
		setTitle("Página de Trabajadores");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 476);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, UIManager.getColor("CheckBox.foreground"), UIManager.getColor("Button.foreground"), null, null));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		tblTrabajadores = new JTable();
		tblTrabajadores.setModel(new DefaultTableModel(
			new Object[][] {
				{"DNI", "Nombre", "Apellido", "Matricula", "Sueldo", "Fecha"},
			},
			new String[] {
				"DNI", "Nombre", "Apellido", "Matricula", "Sueldo", "Fecha"
			}
		));
		tblTrabajadores.setBounds(391, 12, 313, 388);
		pnlPrincipal.add(tblTrabajadores);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarTablaTrabajadores();
			}
		});
		btnActualizar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnActualizar.setBounds(599, 412, 105, 22);
		pnlPrincipal.add(btnActualizar);
		
		JPanel pnlRegistro = new JPanel();
		pnlRegistro.setBorder(new TitledBorder(null, "Registro Trabajador", TitledBorder.LEADING, TitledBorder.TOP, null, UIManager.getColor("CheckBox.foreground")));
		pnlRegistro.setBackground(UIManager.getColor("Button.shadow"));
		pnlRegistro.setBounds(30, 12, 319, 388);
		pnlPrincipal.add(pnlRegistro);
		pnlRegistro.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(12, 34, 70, 15);
		pnlRegistro.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 89, 70, 15);
		pnlRegistro.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(12, 141, 70, 15);
		pnlRegistro.add(lblApellido);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(12, 188, 83, 15);
		pnlRegistro.add(lblMatricula);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 294, 70, 15);
		pnlRegistro.add(lblFecha);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setBounds(12, 236, 70, 15);
		pnlRegistro.add(lblSalario);
		
		txtDni = new JTextField();
		txtDni.setBounds(12, 50, 295, 27);
		pnlRegistro.add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(12, 102, 295, 27);
		pnlRegistro.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(12, 155, 295, 27);
		pnlRegistro.add(txtApellido);
		
		txtMatricula = new JTextField();
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(12, 201, 295, 27);
		pnlRegistro.add(txtMatricula);
		
		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(12, 252, 295, 27);
		pnlRegistro.add(txtSalario);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(UIManager.getColor("OptionPane.questionDialog.titlePane.background"));
		btnGuardar.setForeground(UIManager.getColor("List.dropLineColor"));
		btnGuardar.setBounds(215, 351, 92, 25);
		pnlRegistro.add(btnGuardar);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(12, 309, 295, 27);
		pnlRegistro.add(txtFecha);
		
		this.cargarTablaTrabajadores();
	}
	
	private void cargarTablaTrabajadores(){
		
		try {
			statement = conexion.createStatement();
			resultset = statement.executeQuery("SELECT * FROM trabajadores");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		modeloTabla = (DefaultTableModel) tblTrabajadores.getModel();
		
        try {
			while(resultset.next()){
			    String dni = resultset.getString("DNI");
			    String nombre = resultset.getString("Nombre");
			    String apellido = resultset.getString("Apellido");
			    String matricula = resultset.getString("Matricula");
			    String sueldo = resultset.getString("sueldo");
			    String fecha = resultset.getString("fecha");
			    
			    modeloTabla.addRow(new Object[]{dni,nombre,apellido,matricula,sueldo,fecha});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        tblTrabajadores.setModel(modeloTabla);
	}
}
