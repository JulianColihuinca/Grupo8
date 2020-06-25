package interfaces;

import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
import interfaces.I_Pago;
import personas.Persona;
import servicios.Domicilio;

/**
 * @author Grupo8
 * <br>
 * Interfaz para el sistema
 */
public interface I_Sistema {
	/**
	 * Metodo para agregar abonado.<br>	
	 */
	public void agregarAbonado(Persona persona);
	/**
	 * Metodo para agregar servicio.<br>	
	 * @throws NoPuedeContratarException 
	 */
	public void agregarServicio(String persona,String internet, int cantCel, int cantTel, int cantTV, Domicilio domicilio) throws NoPuedeContratarException;
	/**
	 * Metodo para modificar agregado.<br>	
	 */
	public void modificarAgregado(String persona,String direccion,String accion,String servicio);
	/**
	 * Metodo para abonar.<br>	
	 * @throws NoPuedePagarException 
	 */
	public void pagarFactura(String nombrePersona,I_Pago tipo,int mes) throws NoPuedePagarException;
	/**
	 * Metodo para duplicar facturas.<br>	
	 */
	public void duplicarFactura(String persona);
	/**
	 * Metodo para eliminar contratacion.<br>	
	 * @throws NoPuedeDarDeBajaException 
	 */
	public void eliminarContratacion(String nombrePersona,String domicilio) throws NoPuedeDarDeBajaException;
	/**
	 * Metodo para listar abonados.<br>	
	 */
	public String listarAbonados();
	/**
	 * Metodo para listar factura.<br>	
	 */
	public String listarFactura(String persona);


}