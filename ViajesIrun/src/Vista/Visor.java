package Vista;

import java.text.SimpleDateFormat;
import java.util.*;

import Modelo.Reserva;

public class Visor {

	public static final int SALIR= 0;
	public static final int INSERTARCLIENTE= 11;
	public static final int VISUALIZARCLIENTES= 12;
	public static final int VISUALIZARCLIENTE= 13;
	public static final int MODIFICARCLIENTE= 14;
	public static final int BORRARCLIENTE= 15;
	public static final int INSERTARHOTEL= 21;
	public static final int VISUALIZARHOTELES= 22;
	public static final int VISUALIZARHOTEL= 23;
	public static final int MODIFICARHOTEL= 24;
	public static final int BORRARHOTEL= 25;
	public static final int INSERTARRESERVA= 31;
	public static final int VISUALIZARRESERVAS= 32;
	public static final int VISUALIZARRESERVASDECLIENTE= 33;
	public static final int MODIFICARRESERVA= 34;
	public static final int BORRARRESERVA= 35;
	
	public static void menu(){
		System.out.println("\n---Menu---");
		menuClientes();
		menuHoteles();
		System.out.println("\nGestionar Reservas");
		System.out.println("   "+INSERTARRESERVA+"-Insertar Reserva");
		System.out.println("   "+VISUALIZARRESERVAS+"-Ver Reservas");
		System.out.println("   "+VISUALIZARRESERVASDECLIENTE+"-Ver Reservas de un cliente");
//		System.out.println("   "+MODIFICARRESERVA+"-Modificar Reserva");
//		System.out.println("   "+BORRARRESERVA+"-Borrar Reserva");
//		
		System.out.println("\n"+SALIR+"-Salir");
	}

	private static void menuHoteles() {
		System.out.println("\nGestionar Hoteles");
		System.out.println("   "+INSERTARHOTEL+"-Insertar Hotel");
		System.out.println("   "+VISUALIZARHOTELES+"-Ver Hoteles");
		System.out.println("   "+VISUALIZARHOTEL+"-Buscar Hotel");
		System.out.println("   "+MODIFICARHOTEL+"-Modificar Hotel");
		System.out.println("   "+BORRARHOTEL+"-Borrar Hotel");
	}

	private static void menuClientes() {
		System.out.println("\nGestionar Clientes");
		System.out.println("   "+INSERTARCLIENTE+"-Insertar Cliente");
		System.out.println("   "+VISUALIZARCLIENTES+"-Ver Clientes");
		System.out.println("   "+VISUALIZARCLIENTE+"-Buscar Cliente");
		System.out.println("   "+MODIFICARCLIENTE+"-Modificar Cliente");
		System.out.println("   "+BORRARCLIENTE+"-Borrar Cliente");
	}
	
	public static <T> void visuArray(ArrayList<T> lista) {
		for(Object o : lista) {
			System.out.println(o);
		}
	}
	
	public static  <T> void verUno (T item) {
		System.out.println(item);
		System.out.println("-----------\n");
	}
	
	public static void operacion(Boolean b) {
		if(b) {
			System.out.println("Operacion ejecutada con exito");
		}else {
			System.out.println("No se ha podido ejecutar la operacion");
		}
	}
	public static void visuArrayReservasCliente(ArrayList<Reserva> reservas) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for(Reserva r : reservas) {
			System.out.println("Hotel: " + r.getHabitacion().getHotel().getNombre()+ " Desde: " +sdf.format(r.getDesde()) + " Hasta: "+sdf.format(r.getHasta()));
		}
	}
}
