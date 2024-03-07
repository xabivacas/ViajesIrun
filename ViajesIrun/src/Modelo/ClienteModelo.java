package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteModelo extends Conector {
	public static ArrayList<Cliente> getClientes(){
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
			clientes.sort(new OrdenarClientes());
		} catch (SQLException e) {
			System.out.println("Ha fallado en visualizarClientes");
			e.printStackTrace();
		}
		return clientes;
	}
}
