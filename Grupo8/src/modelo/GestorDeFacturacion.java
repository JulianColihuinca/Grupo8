package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import personas.Fisica;
import personas.Persona;
import servicios.Factura;
import servicios.I_Factura;
/**
 * @author Grupo8
 *<br>
 *Clase para el gestor de Facturacion e implementa Observer
 */
public class GestorDeFacturacion implements Observer,Serializable {

	protected ArrayList<EmuladorPasoTiempo> emPasoTiempo= new ArrayList<EmuladorPasoTiempo>();
	
	public GestorDeFacturacion() {}

	public ArrayList<EmuladorPasoTiempo> getEmPasoTiempo() {
		return emPasoTiempo;
	}

	public void setEmPasoTiempo(ArrayList<EmuladorPasoTiempo> emPasoTiempo) {
		this.emPasoTiempo = emPasoTiempo;
	}

	/**
	 * Agrega Observable a la lista de los Observers 
	 * <b>Pre: </b> ept debe ser distinto de 0<br>
	 * <b>Post: </b> agregado el Observable<br>
	 * @param ept: Parametro de tipo EmuladorPasoTiempo para setear el mismo
	 */
	public void agregarObservable(EmuladorPasoTiempo ept) {
		ept.addObserver(this);
		this.emPasoTiempo.add(ept);
	}
	
	/**
	 * Metodo UpDate que permite realizar las facturas con sus correspondientes detalles
	 */
	public void update(Observable o, Object arg1) {
		EmuladorPasoTiempo ept= (EmuladorPasoTiempo) o;
		if (this.emPasoTiempo.contains(ept) && arg1!=null) {
			Iterator<Persona> personas= (Iterator<Persona>) arg1;
			Persona actual= null;
			int mes= ept.getMesActual() - 1; //El Gestor trabaja con el mes anterior al que actualiza el EPT
			while (personas.hasNext()) {
				actual= personas.next();
				if (!actual.getListaContrataciones().isEmpty()) {
					I_Factura factura= new Factura();
					factura.setDetalles(actual.listarContrataciones(mes));
					factura.setTotalSinP(actual.precioOriginal());
					factura.setTotalConP(factura.getTotalSinP()); //se va a actualizar en el momento de pagar
					actual.getColeccionDeFacturas().agregarFactura(factura, mes);		
				}
				if (actual instanceof Fisica && actual.getColeccionDeFacturas().facturasSinPagar() > 1) {
					Fisica fisica= (Fisica) actual;
					fisica.setMoroso();
				}
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}	

