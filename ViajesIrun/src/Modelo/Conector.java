package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	public static  Connection cn;
	private static final String HOST="localhost";
	private static final String BBDD="hotel";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public  void conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/biblioteca/";
			cn = DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BBDD,USER,PASSWORD);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error al importar Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error conexion");
			e.printStackTrace();
		}
	}
	
	public void cerrar() {
		try {
			cn.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}

	public Connection getCn() {
		return cn;
	}

	public void setCn(Connection cn) {
		this.cn = cn;
	}
}
