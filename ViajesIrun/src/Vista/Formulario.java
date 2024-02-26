package Vista;

import java.util.Scanner;
import Modelo.*;
public class Formulario {

	public static String pedirDNI(Scanner scan) {
		System.out.println("Introduzca el DNI");
		return scan.nextLine();
	}
	public static Cliente pedirDatos(Scanner scan) {
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
}
