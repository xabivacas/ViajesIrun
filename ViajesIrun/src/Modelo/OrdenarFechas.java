package Modelo;

import java.util.Comparator;

public class OrdenarFechas implements Comparator<Reserva>{

	@Override
	public int compare(Reserva o1, Reserva o2) {
		return o1.getDesde().compareTo(o2.getDesde());
		
//		return o2.getDesde().compareTo(o1.getDesde());
	}

}
