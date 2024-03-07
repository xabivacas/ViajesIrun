package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorBBDD extends Conector{

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
	
	public Hotel getHotel(String nombre) {
		String sql = "SELECT * FROM hoteles WHERE nombre=?";
		Hotel h = new Hotel();
		
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, nombre);
			
			buscarHotelenBD(h, pst);
			
		} catch (SQLException e) {
			System.out.println("Error en buscarHotel");
			e.printStackTrace();
		}
		return h;
	}
	public Hotel getHotel(int id) {
		String sql = "SELECT * FROM hoteles WHERE id=?";
		Hotel h = new Hotel();
		
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, id);
			
			buscarHotelenBD(h, pst);
			
		} catch (SQLException e) {
			System.out.println("Error en buscarHotel");
			e.printStackTrace();
		}
		return h;
	}
	private void buscarHotelenBD(Hotel h, PreparedStatement pst) throws SQLException {
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		h.setId(rs.getInt("id"));
		h.setCif(rs.getString("cif"));
		h.setNombre(rs.getString("nombre"));
		h.setEstrellas(rs.getInt("estrellas"));
		h.setGerente(rs.getString("gerente"));
		h.setCompania(rs.getString("compania"));
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
	
	public Habitacion getHabitacion(Hotel hotel,int numero){
		String sql = "SELECT * FROM habitaciones WHERE id_hotel=? AND numero=?";
		Habitacion h = new Habitacion();
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, hotel.getId());
			pst.setInt(2, numero);
			buscarHabitacionenBD(h, pst);
				
		} catch (SQLException e) {
			System.out.println("Error buscarhabitaciones");
			e.printStackTrace();
		}
		return h;
	}
	private void buscarHabitacionenBD(Habitacion h, PreparedStatement pst) throws SQLException {
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		h.setId(rs.getInt("id"));
		h.setHotel(getHotel(rs.getInt("id_hotel")));
		h.setNumero(rs.getInt("numero"));
		h.setDescripcion(rs.getString("descripcion"));
		h.setPrecio(rs.getInt("precio"));
	}
	public Habitacion getHabitacion(int id){
		String sql = "SELECT * FROM habitaciones WHERE id=?";
		Habitacion h = new Habitacion();
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, id);
			
			buscarHabitacionenBD(h, pst);
				
		} catch (SQLException e) {
			System.out.println("Error buscarhabitaciones");
			e.printStackTrace();
		}
		return h;
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
	public boolean modHotel(int id,Hotel h) {
		String sql = "UPDATE hoteles SET cif=?,nombre=?,gerente=?,estrellas=?,compania=? WHERE id=?";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, h.getCif());
			pst.setString(2, h.getNombre());
			pst.setString(3, h.getGerente());
			pst.setInt(4, h.getEstrellas());
			pst.setString(5, h.getCompania());
			pst.setInt(6, id);
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertarReserva(Reserva r) {
		String sql = "INSERT INTO reservas (id_habitacion,dni,desde,hasta) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setInt(1, r.getHabitacion().getId());
			pst.setString(2,r.getCliente().getDni());
			pst.setDate(3, new java.sql.Date(r.getDesde().getTime()));
			pst.setDate(4, new java.sql.Date(r.getHasta().getTime()));
			
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<Reserva> getReservas(){
		ArrayList<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM reservas";
		try {
			ResultSet rs = cn.createStatement().executeQuery(sql);
			
			rellenarArrarReservas(reservas, rs);
			reservas.sort(new OrdenarFechas());
		} catch (SQLException e) {
			System.out.println("Error getReservas");
			e.printStackTrace();
		}
		return reservas;
	}
	private void rellenarArrarReservas(ArrayList<Reserva> reservas, ResultSet rs) throws SQLException {
		while(rs.next()) {
			Reserva r = new Reserva();
			
			r.setId(rs.getInt("id"));
			r.setCliente(ClienteModelo.getCliente(rs.getString("dni")));
			r.setHabitacion(getHabitacion(rs.getInt("id_habitacion")));
			r.setDesde((java.util.Date)rs.getDate("desde"));
			r.setHasta((java.util.Date)rs.getDate("hasta"));
			
			reservas.add(r);
		}
	}
	
	public ArrayList<Reserva> getReservasDeCliente(Cliente c) {
		ArrayList<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM reservas WHERE dni=?";
		try {
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, c.getDni());
			
			ResultSet rs = pst.executeQuery();
			rellenarArrarReservas(reservas, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservas;
	}
}
