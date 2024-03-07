package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HabitacionModelo extends Conector{

	public static boolean insertarHabitacion(Hotel hotel, Habitacion habitacion) {
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
	
	public static ArrayList<Habitacion> buscarHabitaciones(Hotel hotel){
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
	public static Habitacion getHabitacion(Hotel hotel,int numero){
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
	private static void buscarHabitacionenBD(Habitacion h, PreparedStatement pst) throws SQLException {
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		h.setId(rs.getInt("id"));
		h.setHotel(HotelModelo.getHotel(rs.getInt("id_hotel")));
		h.setNumero(rs.getInt("numero"));
		h.setDescripcion(rs.getString("descripcion"));
		h.setPrecio(rs.getInt("precio"));
	}
	public static Habitacion getHabitacion(int id){
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
	

}
