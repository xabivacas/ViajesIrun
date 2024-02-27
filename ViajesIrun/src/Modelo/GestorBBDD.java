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
			e.printStackTrace();
			return false;
		}
		
		
	}
	public boolean borrarCliente(String DNI) {
		String sql = "DELETE FROM clientes WHERE DNI=?";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, DNI);
			pst.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean modificarCliente(String DNI, Cliente c) {
		String sql = "UPDATE clientes SET DNI=?, nombre=?, apellidos=?, direccion=?, localidad=? WHERE DNI = ?";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, c.getDni());
			pst.setString(2, c.getNombre());
			pst.setString(3, c.getApellido());
			pst.setString(4, c.getDireccion());
			pst.setString(5, c.getLocalidad());
			pst.setString(6, DNI);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean insertarHotel(Hotel h) {
		String sql = "INSERT INTO hoteles (cif,nombre,gerente,estrellas,compania) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, h.getCif());
			pst.setString(2, h.getNombre());
			pst.setString(3, h.getGerente());
			pst.setInt(4, h.getEstrellas());
			pst.setString(5, h.getCompania());
			 pst.execute();
			 
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Hotel buscarHotel(String nombre) {
		String sql = "SELECT * FROM hoteles WHERE nombre=?";
		Hotel h = new Hotel();
		
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, nombre);
			
			ResultSet rs = pst.executeQuery();
			rs.next();
			
			h.setId(rs.getInt("id"));
			h.setCif(rs.getString("cif"));
			h.setNombre(rs.getString("nombre"));
			h.setEstrellas(rs.getInt("estrellas"));
			h.setGerente(rs.getString("gerente"));
			h.setCompania(rs.getString("compania"));
			
		} catch (SQLException e) {
			System.out.println("Error en buscarHotel");
			e.printStackTrace();
		}
		return h;
	}
	public boolean insertarHabitacion(Hotel hotel, Habitacion habitacion) {
		String sql = "INSERT INTO habitaciones (id_hotel,numero,descripcion,precio) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, hotel.getId());
			pst.setInt(2, habitacion.getNumero());
			pst.setString(3, habitacion.getDescripcion());
			pst.setInt(4, habitacion.getPrecio());
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
	}
	public ArrayList<Habitacion> buscarHabitaciones(Hotel hotel){
		String sql = "SELECT * FROM habitaciones WHERE id_hotel=?";
		ArrayList<Habitacion> habitaciones = new ArrayList<>();
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, hotel.getId());
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Habitacion h = new Habitacion();
				
				h.setId(rs.getInt("id"));
				h.setHotel(hotel);
				h.setNumero(rs.getInt("numero"));
				h.setDescripcion(rs.getString("descripcion"));
				h.setPrecio(rs.getInt("precio"));
				
				habitaciones.add(h);
			}
		} catch (SQLException e) {
			System.out.println("Error buscarhabitaciones");
			e.printStackTrace();
		}
		return habitaciones;
	}
	public ArrayList<Hotel> visualizarHoteles(){
		String sql = "SELECT * FROM hoteles";
		ArrayList<Hotel> hoteles = new ArrayList<>();
		
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){
				Hotel h = new Hotel();
			
				h.setId(rs.getInt("id"));
				h.setCif(rs.getString("cif"));
				h.setNombre(rs.getString("nombre"));
				h.setGerente(rs.getString("gerente"));
				h.setEstrellas(rs.getInt("estrellas"));
				h.setCompania(rs.getString("compania"));
				
				hoteles.add(h);
			}
		} catch (SQLException e) {
			System.out.println("Error VisuHoteles");
			e.printStackTrace();
		}
	return hoteles;	
	}
	
	public boolean borrarHotel(Hotel h) {
		String sql = "DELETE FROM hoteles WHERE id=?";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, h.getId());
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
}
