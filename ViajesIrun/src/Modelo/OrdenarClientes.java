package Modelo;

import java.util.*;

public class OrdenarClientes implements Comparator<Cliente>{

	@Override
	public int compare(Cliente o1, Cliente o2) {
//		return o1.getNombre().compareTo(o2.getNombre());
		return o2.getNombre().compareTo(o1.getNombre());
	}

}
