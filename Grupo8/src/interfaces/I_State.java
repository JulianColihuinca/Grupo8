package interfaces;

import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
/**
 * @author Grupo8
 * <br>
 * Interfaz para el patron State
 */
public interface I_State {
	/**
	 * Metodo para pagar una factura.<br>	
	 *@param tipo: Parametro de tipo I_Pago que representa al tipo de pago que corresponda, debe ser distinto de null<br>
	 *@param mes: Parametro de tipo int para ubicarse en la factura correspondiente, debe ser mayor a 0 y menor que 13<br> 
	 */
	public void pagarFactura(I_Pago tipo,int mes) throws NoPuedePagarException;
	/**
	 * Metodo para contratar un servicio.<br>	 
	 * @param contratacion: Parametro de tipo I_Contratable que representa la contratacion a agregar, debe ser distinto de null<br>
	 */
	public void contratarNuevoServicio(I_Contratable contratacion) throws NoPuedeContratarException;
	/**
	 * Metodo para dar de baja algun servicio<br>	 
	 * @param domicilio: Parametro de tipo String que representa al domicilio del abonado,debe ser distinto de null<br>
	 */
	public void darDeBajaServicio(String domicilio) throws NoPuedeDarDeBajaException;


}
