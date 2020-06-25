package estados;



import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import interfaces.I_Contratable;
import interfaces.I_Pago;
import interfaces.I_State;
import personas.Fisica;
import servicios.I_Factura;

/**
 * @author Grupo8
 *<br>
 *Estado de I_State para los abonados de tipo Fisica que tienen dos o mas facturas consecutivas sin pagar
 */
public class Moroso implements I_State {

	private Fisica abonado;
	/**
	 * Constructor con un parametro para setear la persona fisica
	 * <br>
	 * @param abonado: parametro de tipo Fisica que representa el abonado de tipo Fisica
	 */
	public Moroso(Fisica abonado) {
		this.abonado = abonado;
	}
	/**
	 * Actualiza el estado Pagado de la factura y el precio final con descuento y/o recargo; se analiza si no tiene deudas, se pasa al nuevo estado de Con Contatacion
	 * <b>Pre: </b> Tipo debe ser distinto de null y el mes debe ser mayor a 0 y menor que 13 <br>
	 * <b>Post: </b> Se actualizan el estado Pagado de la factura y el precio Final con descuento o recargo<br>
	 * @param tipo: Parametro para aplicar el porcentaje de descuento o recargo
	 * @param mes: Parametro para buscar la factura de acuerdo al mes correspondiente
	 */
	public void pagarFactura(I_Pago tipo,int mes) { //SE SUPONE QUE ES FALSE PAGADO
		I_Factura factura=abonado.getColeccionDeFacturas().buscarFactura(mes);
		factura.pagar();
		factura.setTotalConP(abonado.aplicarPorcentaje(tipo, factura.getTotalConP()));
		factura.setTotalConP(factura.getTotalConP()*1.3); //le aplico el recargo
		factura.setTotalSinP(factura.getTotalSinP()*1.3);
		if(abonado.getColeccionDeFacturas().facturasSinPagar()<2) //si tiene menos de dos facturas sin pagar, voy al otro estado
			abonado.setEstado(new ConContratacion(abonado));
	}
	/**
	 * Se lanza una excepcion ya que, no puede contratar algun servicio
	 * <b>Pre: </b> Contratacion debe ser distinto de null <br>
	 * <b>Post: </b> Se lanza la excepcion para ser capturada por el controlador <br>
	 * @param contratacion: Parametro para ser agregado en la coleccion
	 */
	public void contratarNuevoServicio(I_Contratable contratacion) throws NoPuedeContratarException { 
		throw new NoPuedeContratarException(abonado,"Tiene deudas,no puede contratar algún servicio!");
	}
	/**
	 * Se lanza una excepcion ya que,no puede eliminar algun servicio
	 * <b>Pre: </b> Contratacion debe ser distinto de null <br>
	 * <b>Post: </b> Se lanza la excepcion para ser capturada por el controlador <br>
	 * @param contratacion: Parametro para ser eliminada de la coleccion
	 */
	public void darDeBajaServicio(String domicilio) throws NoPuedeDarDeBajaException {
		throw new NoPuedeDarDeBajaException(abonado,"Tiene deudas,no puede dar de baja algún servicio!");
	}

}
