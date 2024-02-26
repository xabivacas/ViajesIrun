package Vista;

public class Visor {

	public static final int INSERTARCLIENTE= 11;
	public static final int VISUALIZARCLIENTE= 12;
	public static final int MODIFICARCLIENTE= 13;
	public static final int BORRARCLIENTE= 14;
	public static final int INSERTARHOTEL= 21;
	public static final int VISUALIZARHOTEL= 22;
	public static final int MODIFICARHOTEL= 23;
	public static final int BORRARHOTEL= 24;
	public static final int INSERTARRESERVA= 31;
	public static final int VISUALIZARRESERVA= 32;
	public static final int MODIFICARRESERVA= 33;
	public static final int BORRARRESERVA= 34;
	
	public static void menu(){
		System.out.println("\n---Menu---");
		System.out.println("Gestionar Clientes");
		System.out.println("Gestionar Hoteles");
		System.out.println("Gestionar Reservas");
	}
}
