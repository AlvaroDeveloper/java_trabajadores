package Trabajadores;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	private Connection conexion = null;
	
	private static String usuario = "root";
	private static String contrasenia = "malixtrix";
	private static String basededatos = "trabajo";
	private static String url = "jdbc:mysql://localhost/"+basededatos;
	
	
	public Conexion(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.conexion = DriverManager.getConnection(url,usuario,contrasenia);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection obtenerConexion(){
		if(conexion == null)
			JOptionPane.showMessageDialog(null, "No hay conexion");
		
		return conexion;
	}
	
	public void cerrarConexion() throws SQLException{
		this.conexion.close();
	}
}
