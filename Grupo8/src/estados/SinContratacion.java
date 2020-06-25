package estados;



import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;
import interfaces.I_Contratable;
import interfaces.I_Pago;
import interfaces.I_State;
import personas.Fisica;

/**
 * @author Grupo8
 *<br>
 *Estado de I_State para los abonados de tipo Fisica que no tienen ninguna contratacion
 */
public class SinContratacion implements I_State {

	private Fisica abonado;
	/**
	 * Constructor con un parametro para setear la persona fisica
	 * <br>
	 * @param abonado: parametro de tipo Fisica que representa el abonado de tipo Fisica
	 */
	public SinContratacion(Fisica abonado) {
		this.abonado = abonado;
	}
	/**
	 * Se lanza una excepcion ya que, no puede pagar 
	 * <b>Pre: </b> Tipo debe ser distinto de null y el mes debe ser mayor a 0 y menor que 13 <br>
	 * <b>Post: </b> Se lanza la excepcion para ser capturada por el controlador<br>
	 * @param tipo: Parametro para aplicar el porcentaje de descuento o recargo
	 * @param mes: Parametro para buscar la factura de acuerdo al mes correspondiente
	 */
	public void pagarFactura(I_Pago tipo, int mes) throws NoPuedePagarException  {
		throw new NoPuedePagarException(abonado, "El abonado no tiene contrataciones que pagar");		
	}
	/**
	 * Se agrega una nueva contratacion a la coleccion de contrataciones y directamente se cambia de estado a ConContratacion
	 * <b>Pre: </b> Contratacion debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega la contratacion en la colección de contrataciones <br>
	 * @param contratacion: Parametro para ser agregado en la coleccion
	 * @throws NoPuedeContratarException 
	 */
	public void contratarNuevoServicio(I_Contratable contratacion) {
		abonado.getListaContrataciones().add(contratacion);
		abonado.setEstado(new ConContratacion(abonado));
	}
	/**
	 * Se lanza una excepcion, ya que no puede eliminar
	 * <b>Pre: </b> Contratacion no debe ser null <br>
	 * <b>Post: </b> Se lanza la excepcion para ser capturada por el controlador <br>
	 * @param contratacion: Parametro para ser eliminada de la coleccion
	 */
	public void darDeBajaServicio(String domicilio) throws NoPuedeDarDeBajaException {
		throw new NoPuedeDarDeBajaException(abonado,"No puede dar de baja algún servicio aún");
	}


}
