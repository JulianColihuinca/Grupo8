package modelo;

import java.util.Observable;

import ModeloMVC.SistemaContrataciones;
/**
 * @author Grupo8
 *<br>
 *Clase para el emulador de paso del tiempo
 */
public class EmuladorPasoTiempo extends Observable {
	
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
		this.setChanged();
		this.notifyObservers(SistemaContrataciones.getInstancia().getPersonas());
	}
	
}
