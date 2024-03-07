package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservaModelo extends Conector {
	public static ArrayList<Reserva> getReservas(){
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
	private static void rellenarArrarReservas(ArrayList<Reserva> reservas, ResultSet rs) throws SQLException {
		while(rs.next()) {
			Reserva r = new Reserva();
			
			r.setId(rs.getInt("id"));
			r.setCliente(ClienteModelo.getCliente(rs.getString("dni")));
			r.setHabitacion(HabitacionModelo.getHabitacion(rs.getInt("id_habitacion")));
			r.setDesde((java.util.Date)rs.getDate("desde"));
			r.setHasta((java.util.Date)rs.getDate("hasta"));
			
			reservas.add(r);
		}
	}
	
	public static ArrayList<Reserva> getReservasDeCliente(Cliente c) {
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
	public static boolean insertarReserva(Reserva r) {
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
}
