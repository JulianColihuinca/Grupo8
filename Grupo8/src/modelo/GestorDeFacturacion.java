package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import personas.Persona;
import servicios.Factura;
import servicios.I_Factura;
/**
 * @author Grupo8
 *<br>
 *Clase para el gestor de Facturacion e implementa Observer
 */
public class GestorDeFacturacion implements Observer {

	//private EmuladorPasoTiempo ept;
	protected ArrayList<EmuladorPasoTiempo> emPasoTiempo= new ArrayList<EmuladorPasoTiempo>();
	
	public GestorDeFacturacion() {}
	/**
	 * Agrega Observable a la lista de los Observers 
	 * <b>Pre: </b> ept debe ser distinto de null<br>
	 * <b>Post: </b> agregado el Observable<br>
	 * @param ept: Parametro de tipo EmuladorPasoTiempo para setear el mismo
	 */
	public void agregarObservable(EmuladorPasoTiempo ept) {
		ept.addObserver(this);
		//this.ept= ept;
		this.emPasoTiempo.add(ept);
	}

	/**
	 * Metodo UpDate que permite realizar las facturas con sus correspondientes detalles
	 */
	public void update(Observable o, Object arg1) {
		EmuladorPasoTiempo ept= (EmuladorPasoTiempo) o;
		System.out.println("Gestor de Facturacion");
		if (this.emPasoTiempo.contains(ept) && arg1 != null) {
			System.out.println("Entra if Gestor de Facturacion");
			Iterator<Persona> personas= (Iterator<Persona>) arg1;
			if (!personas.hasNext())
				System.out.println("El Gestor no recibio Personas");
			Persona actual= null;
			int mes= ept.getMesActual() - 1; //El Gestor trabaja con el mes anterior al que actualiza el EPT
			while (personas.hasNext()) {
				actual= personas.next();
				System.out.println("Analiza a " + actual.getNombre());
				if (!actual.getListaContrataciones().isEmpty()) {
					I_Factura factura= new Factura();
					factura.setDetalles(actual.listarContrataciones());
					factura.setTotalSinP(actual.precioOriginal());
					factura.setTotalConP(factura.getTotalSinP()); //se va a actualizar en el momento de pagar
					actual.getColeccionDeFacturas().agregarFactura(factura, mes);		
					System.out.println("Factura agregada a " + actual.getNombre());
				}
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}	

