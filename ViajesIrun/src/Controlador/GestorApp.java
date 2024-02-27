package Controlador;

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
				Visor.operacion(gs.insertarCliente(Formulario.pedirDatosCliente(scan)));
				gs.cerrar();
				break;
				
			case Visor.VISUALIZARCLIENTES:
				gs.conectar();
				Visor.visuArray(gs.visualizarClientes());
				gs.cerrar();
				break;
				
			case Visor.VISUALIZARCLIENTE:
				gs.conectar();
				Visor.verUno(gs.buscarCliente(Formulario.pedirDNI(scan)));
				gs.cerrar();
				break;
				
			case Visor.MODIFICARCLIENTE:
				gs.conectar();
				String DNI= Formulario.pedirDNI(scan);
				Visor.verUno(gs.buscarCliente(DNI));
				Visor.operacion(gs.modificarCliente(DNI, Formulario.pedirDatosCliente(scan)));
				break;
				
			case Visor.BORRARCLIENTE:
				gs.conectar();
				Visor.operacion(gs.borrarCliente(Formulario.pedirDNI(scan)));
				gs.cerrar();
				break;
				
			case Visor.INSERTARHOTEL:
				gs.conectar();
				Hotel hotel = Formulario.pedirDatosHotel(scan);
				gs.insertarHotel(hotel);
				
				while(Formulario.quieresInsertarHabitacion(scan)) {
					Hotel h = gs.buscarHotel(hotel.getNombre());
					Visor.operacion(gs.insertarHabitacion(h,Formulario.pedirDatosHabitacion(scan, h)));
				}
				
				break;
			case Visor.VISUALIZARHOTELES:
				gs.conectar();
				ArrayList<Hotel> hoteles = gs.visualizarHoteles();
				int i =0;
				
				while(i<hoteles.size()) {
					Hotel h = gs.buscarHotel(hoteles.get(i).getNombre());
					
					visuHotelyHabitaciones(gs, h);
					
					i++;
					System.out.println("\n\n\n");
				}
				gs.cerrar();
				break;
			case Visor.VISUALIZARHOTEL:
				gs.conectar();
				Hotel h = gs.buscarHotel(Formulario.pedirNombre(scan));
				visuHotelyHabitaciones(gs, h);
				gs.cerrar();
				break;
			case Visor.MODIFICARHOTEL:
				//TODO
				break;
			case Visor.BORRARHOTEL:
				gs.conectar();
				Visor.operacion(gs.borrarHotel(gs.buscarHotel(Formulario.pedirNombre(scan))));
				gs.cerrar();
				break;
//			case Visor.INSERTARRESERVA:
//				//TODO
//				break;
//			case Visor.VISUALIZARRESERVAS:
//				//TODO
//				break;
//			case Visor.VISUALIZARRESERVA:
//				//TODO
//				break;
//			case Visor.MODIFICARRESERVA:
//				//TODO
//				break;
//			case Visor.BORRARRESERVA:
//				//TODO
//				break;
			case Visor.SALIR:
				//TODO
				break;
				
			default:
				System.out.println("Opcion no valida");
				break;
			}
		}while(select!=0);
	}
	
	private static void visuHotelyHabitaciones(GestorBBDD gs, Hotel h) {
		Visor.verUno(h);
		Visor.visuArray(gs.buscarHabitaciones(h));
	}
}
