package Vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Controlador.GestorApp;
import Modelo.*;
public class Formulario {

	public static String pedirDNI(Scanner scan) {
		System.out.println("Introduzca el DNI");
		return scan.nextLine();
	}
	public static String pedirNombre(Scanner scan) {
		System.out.println("Introduzca el nombre del hotel");
		return scan.nextLine();
	}
	public static Cliente pedirDatosCliente(Scanner scan) {
		Cliente c = new Cliente();
		
		c.setDni(pedirDNI(scan));
		System.out.println("Introduza el nombre");
		c.setNombre(scan.nextLine());
		
		System.out.println("Introduzca el apellido");
		c.setApellido(scan.nextLine());
		
		System.out.println("Introduzca la direccion");
		c.setDireccion(scan.nextLine());
		
		System.out.println("Introduzca la localidad");
		c.setLocalidad(scan.nextLine());
		
		return c;
	}
	public static Hotel pedirDatosHotel(Scanner scan) {
		Hotel h = new Hotel();
		
		System.out.println("Introduzca el CIF");
		h.setCif(scan.nextLine());
		
		System.out.println("Introduzca el nombre");
		h.setNombre(scan.nextLine());
		
		System.out.println("Introduzca el nombre del gerente");
		h.setGerente(scan.nextLine());
		
		System.out.println("Introduza el numero de estrellas");
		h.setEstrellas(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Introduzca el nombre de la compañia");
		h.setCompania(scan.nextLine());
		return h;
	}
	public static Habitacion pedirDatosHabitacion(Scanner scan, Hotel hotel) {
		Habitacion h = new Habitacion();
		
		System.out.println("Introduzca numero de habitacion");
		h.setNumero(Integer.parseInt(scan.nextLine()));
		if(h.getNumero()==0) {
			return h;
		}else {
		
		System.out.println("Introduzca descripcion");
		h.setDescripcion(scan.nextLine());
		
		System.out.println("Introduzca precio");
		h.setPrecio(Integer.parseInt(scan.nextLine()));
		
		h.setHotel(hotel);
		return h;
		}
	}
	public static boolean quieresInsertarHabitacion(Scanner scan) {
		System.out.println("¿Quiere insertar una habitacion? S/N");
		
		if(scan.nextLine().equalsIgnoreCase("S")) {
			return true;
		}else {
			return false;
		}
	}
	public static Reserva pedirDatosReserva(Scanner scan, GestorBBDD gs) throws ParseException {
		Reserva r = new Reserva();
		
		System.out.println("Inserte el Dni");
		r.setCliente(ClienteModelo.getCliente(scan.nextLine()));
		
		pedirDateyHotel(scan,r);
		
		return r;
	}
	public static Reserva pedirDatosReservaBorrar(Scanner scan) throws ParseException {
		Reserva r = new Reserva();
		pedirDateyHotel(scan, r);
		return r;
	}
	
	private static void pedirDateyHotel(Scanner scan,Reserva r) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Hotel h = HotelModelo.getHotel(Formulario.pedirNombre(scan));
		Visor.verUno(h);
		Visor.visuArray(HabitacionModelo.buscarHabitaciones(h));
		
		System.out.println("Seleccione la habitacion");
		r.setHabitacion(HabitacionModelo.getHabitacion(h, Integer.parseInt(scan.nextLine())));
		
		System.out.println("¿Desde que fecha (yyyy-MM-dd)?");
		r.setDesde(sdf.parse(scan.nextLine()));
			
		System.out.println("¿Hasta que dia (yyyy-MM-dd)");
		r.setHasta(sdf.parse(scan.nextLine()));;
	}
}
