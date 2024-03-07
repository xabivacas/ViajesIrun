package Controlador;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.*;
import Vista.*;

public class GestorApp {

	public static void main(String[] args) {
		run();
	}
	private static void run() {
		Scanner scan = new Scanner(System.in); 
		int select=0;
		GestorBBDD gs = new GestorBBDD();
		
		do {
			Visor.menu();
			select=Integer.parseInt(scan.nextLine());
			switch(select) {
			case Visor.INSERTARCLIENTE:
				gs.conectar();
				Visor.operacion(ClienteModelo.insertarCliente(Formulario.pedirDatosCliente(scan)));
				gs.cerrar();
				break;
				
			case Visor.VISUALIZARCLIENTES:
				gs.conectar();
				Visor.visuArray(ClienteModelo.getClientes());
				gs.cerrar();
				break;
				
			case Visor.VISUALIZARCLIENTE:
				gs.conectar();
				Visor.verUno(ClienteModelo.getCliente(Formulario.pedirDNI(scan)));
				gs.cerrar();
				break;
				
			case Visor.MODIFICARCLIENTE:
				gs.conectar();
				String DNI= Formulario.pedirDNI(scan);
				Visor.verUno(ClienteModelo.getCliente(DNI));
				Visor.operacion(ClienteModelo.modificarCliente(DNI, Formulario.pedirDatosCliente(scan)));
				break;
				
			case Visor.BORRARCLIENTE:
				gs.conectar();
				Visor.operacion(ClienteModelo.borrarCliente(Formulario.pedirDNI(scan)));
				gs.cerrar();
				break;
				
			case Visor.INSERTARHOTEL:
				gs.conectar();
				Hotel hotel = Formulario.pedirDatosHotel(scan);
				HotelModelo.insertarHotel(hotel);
				
				while(Formulario.quieresInsertarHabitacion(scan)) {
					Hotel h = HotelModelo.getHotel(hotel.getNombre());
					Visor.operacion(HabitacionModelo.insertarHabitacion(h,Formulario.pedirDatosHabitacion(scan, h)));
				}
				
				break;
			case Visor.VISUALIZARHOTELES:
				gs.conectar();
				ArrayList<Hotel> hoteles = HotelModelo.visualizarHoteles();
				int i =0;
				
				while(i<hoteles.size()) {
					Hotel h = HotelModelo.getHotel(hoteles.get(i).getNombre());
					
					visuHotelyHabitaciones(gs, h);
					
					i++;
					System.out.println("\n\n\n");
				}
				gs.cerrar();
				break;
			case Visor.VISUALIZARHOTEL:
				gs.conectar();
				Hotel h = HotelModelo.getHotel(Formulario.pedirNombre(scan));
				visuHotelyHabitaciones(gs, h);
				gs.cerrar();
				break;
			case Visor.MODIFICARHOTEL:
				gs.conectar();
				Hotel hot = HotelModelo.getHotel(Formulario.pedirNombre(scan));
				visuHotelyHabitaciones(gs, hot);
				Visor.operacion(HotelModelo.modHotel(hot.getId(), Formulario.pedirDatosHotel(scan)));
				break;
			case Visor.BORRARHOTEL:
				gs.conectar();
				Visor.operacion(HotelModelo.borrarHotel(HotelModelo.getHotel(Formulario.pedirNombre(scan))));
				gs.cerrar();
				break;
			case Visor.INSERTARRESERVA:
				insertarReserva(scan, gs);
				break;
			case Visor.VISUALIZARRESERVAS:
				//TODO
				gs.conectar();
				Visor.visuArray(ReservaModelo.getReservas());
				gs.cerrar();
				break;
			case Visor.VISUALIZARRESERVASDECLIENTE:
				gs.conectar();
				String dni = Formulario.pedirDNI(scan);
				Cliente cliente = ClienteModelo.getCliente(dni);
				getReservas(scan, cliente);
				gs.cerrar();
				break;
//			case Visor.VISUALIZARRESERVA:
//				//TODO
//				break;
//			case Visor.MODIFICARRESERVA:
//				//TODO
//				break;
			case Visor.BORRARRESERVA:
				//TODO
				dni = Formulario.pedirDNI(scan);
				cliente = ClienteModelo.getCliente(dni);
				Reserva r= new Reserva();
				
				getReservas(scan, cliente);
				try {
					r=Formulario.pedirDatosReservaBorrar(scan);
					ReservaModelo.deleteReserva(r);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case Visor.SALIR:
				//TODO
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
			}
		}while(select!=0);
	}
	private static void insertarReserva(Scanner scan, GestorBBDD gs) {
		gs.conectar();
		try {
			Reserva r = Formulario.pedirDatosReserva(scan, gs);
			if(r.validar()) {
				Visor.operacion(ReservaModelo.insertarReserva(r));
			}else {
				Visor.operacion(false);
			}
		} catch (ParseException e) {
			System.out.println("Ha habido un error con la reserva");
			e.printStackTrace();
		}
		gs.cerrar();
	}
	
	private static void visuHotelyHabitaciones(GestorBBDD gs, Hotel h) {
		Visor.verUno(h);
		Visor.visuArray(HabitacionModelo.buscarHabitaciones(h));
	}
	private static ArrayList<Reserva> getReservas (Scanner scan,Cliente cliente){
		
		ArrayList<Reserva> reservas = ReservaModelo.getReservasDeCliente(cliente);
		
		Visor.verUno(cliente);
		Visor.visuArrayReservasCliente(reservas);
		return reservas;
	}
}
