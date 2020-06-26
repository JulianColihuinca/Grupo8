package modelo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;

import ModeloMVC.SistemaContrataciones;
import personas.Persona;
/**
 * @author Grupo8
 *<br>
 *Clase para el emulador de paso del tiempo
 */
public class EmuladorPasoTiempo extends Observable implements Serializable {
	
	private int mesActual;
	
	/**
	 * Constructor para setear el mes actual
	 * <br>
	 */
	public EmuladorPasoTiempo() {
		this.mesActual= 1;
	}
	
	public int getMesActual() {
		return mesActual;
	}
	/**
	 * Setea el mes actual
	 * @param mesActual: parametro de tipo int, debe ser mayor a 0 y menor que 13
	 */
	public void setMesActual(int mesActual) {
		this.mesActual = mesActual;
	}
	/**
	 * Metodo que incrementa el mes y notifica a los observers
	 */
	public void avanzarMes() {
		this.mesActual++;
		Iterator<Persona> personas= SistemaContrataciones.getInstancia().getPersonas();
		this.setChanged();
		this.notifyObservers(personas);
	}
	
	
	
}
