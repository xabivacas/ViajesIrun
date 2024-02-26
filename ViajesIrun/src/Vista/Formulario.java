package Vista;

import java.util.Scanner;
import Modelo.*;
public class Formulario {

	public static String pedirDNI(Scanner scan) {
		System.out.println("Introduzca el DNI");
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
		h.setEntrella(Integer.parseInt(scan.nextLine()));
		
		System.out.println("Introduzca el nombre de la compañia");
		h.setCompania(scan.nextLine());
		return h;
	}
}
