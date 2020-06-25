
package servicios;

import java.util.Iterator;
/**
 *@author Grupo8
 *<br>
 *Interfaz para la coleccion de Facturas
 */
public interface I_ColeccionDeFacturas {

	void agregarFactura(I_Factura factura, int mes);
	
	//void pagarFactura(I_Factura factura);
	
	I_Factura buscarFactura(int mes);
	
	Iterator<I_Factura> getFacturaIterator();
	
	Iterator<Integer> getMesIterator();
	
	int facturasSinPagar();
	
	Object clone() throws CloneNotSupportedException;
	
}
