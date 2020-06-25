package estados;



import excepciones.NoPuedeContratarException;
import interfaces.I_Contratable;
import interfaces.I_Pago;

import interfaces.I_State;
import personas.Fisica;
import servicios.I_Factura;

/**
 * @author Grupo8
 *<br>
 *Estado de I_State para los abonados de tipo Fisica que ya tengan alguna/as contrataciones y sin deudas
 */
public class ConContratacion implements I_State  {

	private Fisica abonado;
	/**
	 * Constructor con un parametro para setear la persona fisica
	 * <br>
	 * @param abonado: parametro de tipo Fisica que representa el abonado de tipo Fisica
	 */
	public ConContratacion(Fisica abonado) {
		this.abonado = abonado;
	}
	/**
	 * Actualiza el estado Pagado de la factura y el precio final con descuento y/o recargo
	 * <b>Pre: </b> Tipo debe ser distinto de null y el mes debe ser mayor a 0 y menor que 13<br>
	 * <b>Post: </b> Se actualizan el estado Pagado de la factura y el precio Final con descuento o recargo<br>
	 * @param tipo: Parametro para aplicar el porcentaje de descuento o recargo
	 * @param mes: Parametro para buscar la factura de acuerdo al mes correspondiente
	 */
	public void pagarFactura(I_Pago tipo,int mes) {
		I_Factura factura=abonado.getColeccionDeFacturas().buscarFactura(mes);
		factura.setPagado(true);
		factura.setTotalConP(abonado.aplicarPorcentaje(tipo, factura.getTotalConP()));
	}
	/**
	 * Se agrega una nueva contratacion a la coleccion de contrataciones
	 * <b>Pre: </b> Contratacion debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega la contratacion en la colección de contrataciones <br>
	 * @param contratacion: Parametro para ser agregado en la coleccion
	 * @throws NoPuedeContratarException 
	 */
	public void contratarNuevoServicio(I_Contratable contratacion) {
		abonado.getListaContrataciones().add(contratacion);
	}
	/**
	 * Se elimina una contratacion de la coleccion de contrataciones y analiza si se quedo sin contrataciones, se cambia al nuevo estado de Sin Contratacion
	 * <b>Pre: </b> Contratacion debe ser distinto de null <br>
	 * <b>Post: </b> Se elimina la contratacion buscada en la coleccion de contrataciones <br>
	 * @param contratacion: Parametro para ser eliminada de la coleccion
	 */
	public void darDeBajaServicio(String domicilio) {
		abonado.getListaContrataciones().remove(abonado.buscaContratacion(domicilio));
		if(abonado.getListaContrataciones().size()==0) //no hay mas contrataciones, paso al estado Sin contratacion
			abonado.setEstado(new SinContratacion(abonado));
	}


}
