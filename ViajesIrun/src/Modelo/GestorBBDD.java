package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorBBDD extends Conector{

	public ArrayList<Cliente> visualizarClientes(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM clientes";
		
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Cliente c = new Cliente();
				
				c.setDni(rs.getString("DNI"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellidos"));
				c.setDireccion(rs.getString("direccion"));
				c.setLocalidad(rs.getString("localidad"));
				
				clientes.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Ha fallado en visualizarClientes");
			e.printStackTrace();
		}
		return clientes;
	}
	public Cliente buscarCliente(String DNI) {
		Cliente c = new Cliente();
		String sql = "SELECT * FROM clientes WHERE DNI=?";
		
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, DNI);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			c.setDni(rs.getString("DNI"));
			c.setNombre(rs.getString("nombre"));
			c.setApellido(rs.getString("apellidos"));
			c.setDireccion(rs.getString("direccion"));
			c.setLocalidad(rs.getString("localidad"));

		} catch (SQLException e) {
			System.out.println("Ha fallado en buscarCliente");
			e.printStackTrace();
		}
		return c;
	}
	public boolean insertarCliente(Cliente c) {
		String sql = "INSERT INTO clientes VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, c.getDni());
			pst.setString(2, c.getNombre());
			pst.setString(3, c.getApellido());
			pst.setString(4, c.getDireccion());
			pst.setString(5, c.getLocalidad());
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Ha fallado en insertarCliente");
			e.printStackTrace();
		}
		
		return false;
	}
}
