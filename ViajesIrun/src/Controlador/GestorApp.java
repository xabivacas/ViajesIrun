package Controlador;

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
				Hotel h = Formulario.pedirDatosHotel(scan);
				Visor.operacion();
				break;
			case Visor.VISUALIZARHOTELES:
				//TODO
				break;
			case Visor.VISUALIZARHOTEL:
				//TODO
				break;
			case Visor.MODIFICARHOTEL:
				//TODO
				break;
			case Visor.BORRARHOTEL:
				//TODO
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
}
