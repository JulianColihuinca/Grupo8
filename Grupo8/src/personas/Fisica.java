package personas;

import java.util.Iterator;
import estados.Moroso;
import estados.SinContratacion;
import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
import interfaces.I_Contratable;
import interfaces.I_Pago;
import interfaces.I_State;
/**
 * @author Grupo8
 * <br>
 * Clase que representa a una Persona Fisica
 */
public class Fisica extends Persona {
	private I_State estado;
	private int DNI;
	/**
	 * Constructor  con dos parametros para setear el nombre, el documento y el estado de la persona fisica
	 * <br>
	 * @param nombre : parametro de tipo String que representa el nombre de la persona fisica
	 * @param DNI : parametro de tipo int que representa el documento de la persona fisica
	 */
	public Fisica(String nombre,int DNI) {
		super(nombre);
		this.DNI=DNI;
		estado= new SinContratacion(this);
	}
	public Fisica() {}
	public int getDNI() {
		return DNI;
	}
	/**
	 * Setea el documento de la persona fisica
	 * @param dNI: parametro de tipo int, debe ser positivo
	 */
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	 /**
	 *Metodo para la clonacion de persona fisica, en este caso SIEMPRE SERA CLONABLE
	 */
	@Override
	 public Object clone() { 
	        Fisica clon = null;
	        try
	        {
	            clon = (Fisica) super.clone();
	        } catch (CloneNotSupportedException e)
	        {
	            //NUNCA entraremos a este bloque porque siempre sera clonable
	            e.printStackTrace();
	        }
	        Iterator<I_Contratable> contratables= clon.listaContrataciones.iterator();
	        clon.listaContrataciones.clear();
	        while (contratables.hasNext()) {
	        	clon.listaContrataciones.add(contratables.next());
	        }
	        return clon;
	 }
   /**
	*Metodo para setear el estado a Moroso
	*/
	public void setMoroso() {
		this.estado= new Moroso(this);
	}
	
	/**
	 *Realiza el calculo del total a abonar la persona fisica aplicando el tipo de pago correspondiente(DOUBLE DISPATCH)<br>
	 *<b>Pre: </b> tipo debe ser distinto de null y el total debe ser positivo <br>
	 * <b>Post: </b> sera calculado el total aplicando el tipo de pago
	 * @param tipo: medio de pago para aplicar el porcentaje correspondiente
	 * @param total: total de la factura a pagar para calcular su precio final aplicando el porcentaje
	 * @return devuelve el valor del total calculado con el porcentaje aplicado
	 */
	@Override
	public double aplicarPorcentaje(I_Pago tipo, double total) { 
		return total*tipo.porcentajeFisica();
	}
	/**
	 *@return devuelve la informacion detallada de la persona fisica
	 */
	@Override
	public String toString() {
		return "ABONADO de tipo persona Fisica-->Nombre= " + this.getNombre() + " DNI=" + DNI;
	}
	public I_State getEstado() {
		return estado;
	}
	public void setEstado(I_State estado) {
		this.estado = estado;
	}
	@Override
	public void pagar(I_Pago tipo, int mes) throws NoPuedePagarException {
			this.estado.pagarFactura(tipo, mes);
		
	}
	@Override
	public void agregarContratacion(I_Contratable iContratable) throws NoPuedeContratarException {
		this.estado.contratarNuevoServicio(iContratable);
		
	}
	@Override
	public void eliminarContratacion(String domicilio) throws NoPuedeDarDeBajaException {
		this.estado.darDeBajaServicio(domicilio);		
	}
	
	
}
