package excepciones;

import personas.Persona;
/**
 * @author Grupo8
 *<br>
 *Excepcion para el caso de no poder pagar alguna factura
 */
@SuppressWarnings("serial")
public class NoPuedePagarException extends Exception {

	private Persona persona;
	/**
	 * Metodo que se activa cuando se lanza la excepcion
	 * @param persona: parametro de tipo Persona que representa al abonado 
	 * @param mensaje: parametro de tipo String que representa el mensaje del error
	 */
	public NoPuedePagarException(Persona persona, String mensaje) {
		super(mensaje);
		this.persona= persona;
	}

	public Persona getPersona() {
		return persona;
	}

	
}
